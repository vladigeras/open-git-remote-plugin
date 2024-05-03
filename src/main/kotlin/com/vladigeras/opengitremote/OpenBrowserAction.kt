package com.vladigeras.opengitremote

import com.intellij.ide.BrowserUtil
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import git4idea.repo.GitRepositoryManager
import com.intellij.openapi.diagnostic.logger

private val LOG = logger<OpenBrowserAction>()

class OpenBrowserAction : AnAction() {

    override fun actionPerformed(e: AnActionEvent) {
        val remotes = e.project?.let { GitRepositoryManager.getInstance(it).repositories }
            ?.flatMap { it.remotes }
            ?.map { Pair(it.name, it.urls) }
            ?: return

        LOG.debug("Found ${remotes.size} remotes")
        remotes.forEach {
            LOG.info("Opening ${it.first} - ${it.second.first()} in browser")
            BrowserUtil.browse(it.second.first())
        }
    }
}