package com.vladigeras.opengitremote

import com.intellij.notification.NotificationGroupManager
import com.intellij.notification.NotificationType.INFORMATION
import com.intellij.openapi.project.Project
import com.vladigeras.opengitremote.Icons.TOOL

class Notifier {
    private val notificationGroup = NotificationGroupManager.getInstance()
        .getNotificationGroup("OpenGitRemotesNotificationGroup")

    fun showNotificationAboutNoActiveProject(project: Project?) = notificationGroup
        .createNotification("Active project is absent", "You need to open a project", INFORMATION)
        .apply { icon = TOOL }
        .notify(project)

    fun showNotificationAboutNoGitRepo(project: Project) = notificationGroup
        .createNotification("Git repository is absent", "You need to create a repository", INFORMATION)
        .apply { icon = TOOL }
        .notify(project)

    fun showNotificationAboutEmptyRemotes(project: Project) = notificationGroup
        .createNotification("Remotes are empty", "You need to add a remote", INFORMATION)
        .apply { icon = TOOL }
        .notify(project)
}