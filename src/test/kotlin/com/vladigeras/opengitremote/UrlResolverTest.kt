package com.vladigeras.opengitremote

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

class UrlResolverTest {

    @Test
    fun `test resolve for http, https urls`() {
        assertEquals(
            "https://host.xz/path/to/repo.git",
            UrlResolver("https://host.xz/path/to/repo.git").resolve()
        )
        assertEquals(
            "http://host.xz/path/to/repo.git",
            UrlResolver("http://host.xz/path/to/repo.git").resolve()
        )
    }

    @Test
    fun `test resolve ssh urls`() {
        assertEquals(
            "https://host.xz/path/to/repo.git",
            UrlResolver("ssh://host.xz/path/to/repo.git").resolve()
        )
        assertEquals(
            "https://host.xz/path/to/repo.git",
            UrlResolver("git@host.xz:path/to/repo.git").resolve()
        )
        assertEquals(
            "https://host.xz/path/to/repo.git",
            UrlResolver("ssh://user@host.xz/path/to/repo.git").resolve()
        )
        assertEquals(
            "https://host.xz:9999/path/to/repo.git",
            UrlResolver("ssh://host.xz:9999/path/to/repo.git").resolve()
        )
        assertEquals(
            "https://host.xz:9999/path/to/repo.git",
            UrlResolver("ssh://user@host.xz:9999/path/to/repo.git").resolve()
        )
    }

    @Test
    fun `test resolve unknown or invalid urls`() {
        assertNull(UrlResolver("host.xz:path/to/repo.git").resolve())
        assertNull(UrlResolver("host.xz:~user/path/to/repo.git").resolve())
        assertNull(UrlResolver("file:///path/to/repo.git").resolve())
        assertNull(UrlResolver("rsync://host.xz/path/to/repo.git").resolve())
    }
}