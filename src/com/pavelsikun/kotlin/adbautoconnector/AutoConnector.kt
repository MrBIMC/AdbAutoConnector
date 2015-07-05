package com.pavelsikun.kotlin.adbautoconnector

import com.pavelsikun.kotlin.adbautoconnector
import com.pavelsikun.kotlin.adbautoconnector.logic.*
import com.pavelsikun.kotlin.adbautoconnector.ui.Configurator
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
    tryConnect("192.168.0.39", "192.168.2.38")
//    System.exit(0)
//    printLogs()
//    tryConnect("192.168.1.39")
//    printLogs()
    Timer().schedule(object: TimerTask() { override fun run() { System.exit(0)} }, 5000L) //hack to force-stop. IDK why it doesn't stop itself
}