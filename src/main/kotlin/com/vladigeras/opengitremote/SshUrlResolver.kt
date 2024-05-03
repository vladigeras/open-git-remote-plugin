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

        val port = substringBetween(cleared, ":", "/")?.toIntOrNull()

        val parts = cleared.split(":")
        return when (parts.size) {
            1 -> "$DEFAULT_SCHEMA${parts[0]}"

            2 -> {
                val portPart = port
                    ?.let { ":$it" }
                    ?: ""
                val repo = port?.let { parts[1].removePrefix("$it/") } ?: parts[1]
                "$DEFAULT_SCHEMA${parts[0]}$portPart/$repo"
            }
            else -> null
        }
    }
}