package com.vladigeras.opengitremote

import com.intellij.notification.NotificationGroupManager
import com.intellij.notification.NotificationType.INFORMATION
import com.intellij.openapi.project.Project
import com.vladigeras.opengitremote.Icons.TOOL

class Notifier {
    private val notificationGroup = NotificationGroupManager.getInstance()
        .getNotificationGroup("OpenGitRemotesNotificationGroup")

    fun showNotificationAboutNoActiveProject(project: Project?) = notificationGroup
        .createNotification("Active project is absent", "Open the project first", INFORMATION)
        .apply { icon = TOOL }
        .notify(project)

    fun showNotificationAboutEmptyRemotes(project: Project) = notificationGroup
        .createNotification("Remotes are empty", "Add remotes first", INFORMATION)
        .apply { icon = TOOL }
        .notify(project)
}