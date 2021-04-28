package com.example.studyonline

import android.content.SyncStatusObserver
import org.junit.Test

import org.junit.Assert.*
import java.sql.Timestamp

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testTimestamp() {
        val time = Timestamp.valueOf("2021-04-28 14:25:10")
        println(time.time)
    }
    // 1619591050000
    // 1619591110000
}