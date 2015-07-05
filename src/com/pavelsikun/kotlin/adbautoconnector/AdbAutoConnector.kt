package com.pavelsikun.kotlin.adbautoconnector

import com.pavelsikun.kotlin.adbautoconnector
import com.pavelsikun.kotlin.adbautoconnector.logic.*
import com.pavelsikun.kotlin.adbautoconnector.ui.Configurator
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.InetAddress
import java.util.*
import java.util.regex.Pattern
import kotlin.platform.platformStatic
import kotlin.properties.Delegates

/**
 * Created by mrbimc on 04.07.15.
 */

object ConfiguratorStarter {
    @platformStatic
    public fun main(vararg args: String) {
        Configurator()
    }
}

object ScriptStarter {
    @platformStatic
    public fun main(vararg args: String) {
        var config = openConfig()
        when(config.mode) {
            0 -> tryConnect()
            1 -> tryConnect(config.single)
            2 -> tryConnect(config.rangeFrom, config.rangeTo)
        }
        Timer().schedule(object: TimerTask() { override fun run() {
            log("finished executing")
            System.exit(0)} }, 5000L) //hack to force-stop.
    }
}

