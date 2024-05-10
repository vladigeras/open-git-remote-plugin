package com.vladigeras.opengitremote

import org.apache.commons.lang3.StringUtils.substringBetween

class SshUrlResolver(
    private val sshUrl: String
) {

    fun resolve(): String? {
        val cleared = sshUrl
            .removePrefix("ssh://")
            .removePrefix("git@")
            .substringAfter("@")
            .let {
                findPort(it)?.let { p -> it.replace(":$p", "") } ?: it
            }

        val parts = cleared.split(":")
        return when (parts.size) {
            1 -> "$DEFAULT_SCHEMA${parts[0]}"
            2 -> "$DEFAULT_SCHEMA${parts[0]}/${parts[1]}"
            else -> null
        }
    }

    private fun findPort(str: String) = substringBetween(str, ":", "/")?.toIntOrNull()
}