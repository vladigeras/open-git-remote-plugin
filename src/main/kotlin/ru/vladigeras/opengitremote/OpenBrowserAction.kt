package ru.vladigeras.opengitremote

import com.intellij.ide.BrowserUtil
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

class OpenBrowserAction: AnAction() {

    override fun actionPerformed(e: AnActionEvent) {
        println("On event: $e")
        BrowserUtil.browse("https://google.com")
    }
}