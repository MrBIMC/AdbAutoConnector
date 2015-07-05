package com.pavelsikun.kotlin.adbautoconnector.logic

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.io.File
import java.util.*
import java.util.regex.Pattern

/**
 * Created by mrbimc on 05.07.15.
 */

data class Config(var mode: Int = 0,
                  var single: String = "192.168.1.100",
                  var rangeFrom: String = "192.168.0.0",
                  var rangeTo: String = "192.168.5.255")

fun openConfig(): Config {
    try { return jacksonObjectMapper().readValue(File(".adb-config.json"), javaClass<Config>()) }
    catch(e: Exception) {
        log("config doesn't exist! Creating new default config now!")
        val config = Config()
        saveConfig(config)
        return config
    }
}

fun saveConfig(config: Config) = jacksonObjectMapper().writeValue(File(".adb-config.json"), config)

fun log(msg: String) = File(".adb-logs.txt").appendText("${Date()}:  $msg\n")

fun printLogs() = File(".adb-logs.txt").forEachLine {  println(it) }

private val IPADDRESS_PATTERN =
"^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
"([01]?\\d\\d?|2[0-4]\\d|25[0-5])$"

fun String.isValidIp() = Pattern.compile(IPADDRESS_PATTERN).matcher(this).matches()

fun notify(msg: String) {
    log("success - $msg")
    fun notifyMac() {
        val command = """display notification "$msg" with title "ADB AutoConnecor" sound name "default" """
        val code = arrayOf("osascript", "-e", command)
        Runtime.getRuntime().exec(code)
    }

    fun notifyUnix() {
        val command = """notify-send "ADB AutoConnecor" "$msg" """
        Runtime.getRuntime().exec(command)
    }

    val os = System.getProperty("os.name").toLowerCase()
    val isWindows = os.indexOf("win") >= 0
    val isMac = os.indexOf("mac") >= 0
    val isLinux = os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0

    if(isMac) notifyMac()
    else if(isLinux) notifyUnix()
//    else if(isWindows) notfyWindows() <- no way :(
//    else idk what is this
}