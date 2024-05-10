package com.vladigeras.opengitremote

import com.intellij.ide.BrowserUtil
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.diagnostic.logger
import git4idea.repo.GitRepositoryManager

private val LOG = logger<OpenBrowserAction>()

class OpenBrowserAction : AnAction() {
    private val notifier = Notifier()

    override fun actionPerformed(e: AnActionEvent) {
        e.project ?: run {
            LOG.info("There is no active project")
            notifier.showNotificationAboutNoActiveProject(e.project)
            return
        }
        val repository = e.project!!.let { GitRepositoryManager.getInstance(it).repositories }.firstOrNull()
            ?: run {
                LOG.info("Git repository was not found")
                notifier.showNotificationAboutNoGitRepo(e.project!!)
                return
            }

        val remotes = repository.remotes
            .map { Pair(it.name, it.urls.firstOrNull()) }
            .filterNot { it.second == null }

        if (remotes.isEmpty()) {
            LOG.info("There are empty remotes for repo")
            notifier.showNotificationAboutEmptyRemotes(e.project!!)
            return
        }

        LOG.debug("Found ${remotes.size} remotes")
        remotes.forEach {
            val rawUrl = it.second!!
            val resolvedUrl = UrlResolver(rawUrl).resolve()
            if (resolvedUrl == null) {
                LOG.warn("Unable to resolve url: $rawUrl")
                return
            }
            LOG.info("Opening [${it.first}] $resolvedUrl in browser")
            BrowserUtil.browse(resolvedUrl)
        }
    }
}