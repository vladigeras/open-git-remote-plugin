package ru.vladigeras.opengitremote

import com.intellij.ide.BrowserUtil
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import git4idea.repo.GitRepositoryManager

class OpenBrowserAction : AnAction() {

    override fun actionPerformed(e: AnActionEvent) {
        val remotes = e.project?.let { GitRepositoryManager.getInstance(it).repositories }
            ?.flatMap { it.remotes }
            ?.map { Pair(it.name, it.urls) }
            ?: return

        remotes.firstOrNull()?.let {
            BrowserUtil.browse(it.second.first())
        }
    }
}