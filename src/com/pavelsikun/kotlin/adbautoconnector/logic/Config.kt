package com.pavelsikun.kotlin.adbautoconnector.logic

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.io.File

/**
 * Created by mrbimc on 07.07.15.
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