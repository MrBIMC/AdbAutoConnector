package com.pavelsikun.kotlin.adbautoconnector.logic

/**
 * Created by mrbimc on 07.07.15.
 */
fun notify(msg: String) {
    fun notifyMac() {
        val command = """display notification "$msg" with title "ADB AutoConnecor" sound name "default" """
        val code = arrayOf("osascript", "-e", command)
        Runtime.getRuntime().exec(code)
    }

    fun notifyUnix() {
        val command = arrayOf("notify-send", "ADB AutoConnecor", "$msg")
        Runtime.getRuntime().exec(command)
    }

    val os = System.getProperty("os.name").toLowerCase()
    val isWindows = os.indexOf("win") >= 0
    val isMac = os.indexOf("mac") >= 0
    val isLinux = os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0

    when {
        isMac -> notifyMac()
        isLinux -> notifyUnix()
        isWindows -> log("Windows notifications are not supported")
    }
}