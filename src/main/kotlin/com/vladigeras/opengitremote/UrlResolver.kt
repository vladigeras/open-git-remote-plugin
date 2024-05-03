package com.vladigeras.opengitremote

const val DEFAULT_SCHEMA = "https://"

class UrlResolver(
    private val url: String
) {

    fun resolve(): String? = when {
        url.startsWith("http://") || url.startsWith(DEFAULT_SCHEMA) -> url
        url.startsWith("ssh://") || url.startsWith("git@") -> SshUrlResolver(url).resolve()
        else -> null
    }
}