package com.pavelsikun.kotlin.adbautoconnector.logic

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.io.File
import java.util.*
import java.util.regex.Pattern

/**
 * Created by mrbimc on 05.07.15.
 */
fun log(msg: String) = File(".adb-logs").appendText("${Date()}:  $msg\n")

private val IPADDRESS_PATTERN =
"^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
"([01]?\\d\\d?|2[0-4]\\d|25[0-5])$"

fun String.isValidIp() = Pattern.compile(IPADDRESS_PATTERN).matcher(this).matches()