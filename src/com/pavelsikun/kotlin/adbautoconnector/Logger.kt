package com.pavelsikun.kotlin.adbautoconnector

import java.io.File
import java.util.*

/**
 * Created by mrbimc on 04.07.15.
 */
fun log(msg: String) = File("logs.txt").appendText("${Date()}:  $msg\n")

fun printAllLogs() = File("logs.txt").forEachLine {
    println(it)
}
