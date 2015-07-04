package com.pavelsikun.kotlin.adbautoconnector

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.InetAddress
import java.util.*
import java.util.regex.Pattern
import kotlin.properties.Delegates

/**
 * Created by mrbimc on 04.07.15.
 */
public fun main(vararg args: String) {
//    tryConnect("192.168.0.102", "192.168.2.103")
    tryConnect("192.168.1.39")
//    Timer().schedule(object: TimerTask() { override fun run() { System.exit(0)} }, 10000L) //hack to force-stop. IDK why it doesn't stop itself
    printAllLogs()
}