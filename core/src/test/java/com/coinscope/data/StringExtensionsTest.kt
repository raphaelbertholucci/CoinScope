package com.coinscope.data

import com.coinscope.core.extensions.formatDate
import kotlinx.coroutines.runBlocking
import org.junit.Test

class StringExtensionsTest {

    @Test
    fun `formatDate() emits correct date when passing right patterns`() = runBlocking {
        val date = "2025-03-04"
        assert(date.formatDate() == "04 Mar 2025")
    }

    @Test
    fun `formatDate() emits empty string when passing pattern not known`() = runBlocking {
        val date = "04/03/2025"
        assert(date.formatDate() == "")
    }
}