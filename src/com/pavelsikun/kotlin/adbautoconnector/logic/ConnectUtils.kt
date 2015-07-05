package com.pavelsikun.kotlin.adbautoconnector.logic

import com.pavelsikun.kotlin.adbautoconnector
import com.pavelsikun.kotlin.adbautoconnector.logic.log
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.InetAddress
import java.util.*

/**
 * Created by mrbimc on 04.07.15.
 */

/**
 * Tries to connect to all of the ips from the same upnp zone, where computer is
 */
fun tryConnect() {
    log("checking all ips in current upnp zone:")
    val ipBase = InetAddress.getLocalHost().getHostAddress()
    val lastDot = ipBase.lastIndexOf('.')
    (0..255).forEach { tryConnect("${ipBase.substring(0, lastDot)}.$it", 50) }
}

/**
 * Tries to connect to each ip from a given range of ips
 */
fun tryConnect(from: String, to: String) {
    log("checking ips in range ($from..$to)")
    val froms = ArrayList<Int>()
    val tos = ArrayList<Int>()

    from.split('.').forEach { it.toInt().let { froms.add(it) } }
    to.split('.').forEach { it.toInt().let { tos.add(it) } }

    val validIps =
            if(froms[0] == tos[0] && froms[1] == tos[1] && froms[2] <= tos[2]) true
            else false

    if(validIps) {
        if(froms[2] == tos[2] && froms[3] <= tos[3] ) {
            (froms[3]..tos[3]).forEach {
                tryConnect("${froms[0]}.${froms[1]}.${froms[2]}.$it", waitBeforeStart = 50)
            }
        }
        else if(froms[2] < tos[2]) {
            val diff = tos[2] - froms[2]
            (0..diff).forEach { block2diff ->

                if(block2diff == 0) (froms[3]..255).forEach {
                        tryConnect("${froms[0]}.${froms[1]}.${froms[2]}.$it", waitBeforeStart = 50)
                    }

                else if(block2diff == diff) (0..tos[3]).forEach {
                        tryConnect("${froms[0]}.${froms[1]}.${tos[2]}.$it", waitBeforeStart = 50)
                    }

                else (0..255).forEach {
                        tryConnect("${froms[0]}.${froms[1]}.${froms[2]+block2diff}.$it", waitBeforeStart = 50)
                    }
            }
        }
        else log("invalid ips: $from, $to")
    }
    else log("invalid ips: $from, $to")
    log("finished checking ips in range of($from..$to)")
}

/**
 *  Tries to connect to a given ip
 *  @param waitBeforeStart is optional and tells how much time to wait before starting.
 *  Used when trying too many ips simultaneously.
 */
fun tryConnect(ip: String, waitBeforeStart: Long = 0L) {
    if(waitBeforeStart != 0L) Thread.sleep(waitBeforeStart)

    object: TimeoutTask<String?>(2000) {
        override fun doInBackground(): String? {
            val process = Runtime.getRuntime().exec("adb connect $ip")
            val inputStream = BufferedReader(InputStreamReader(process.getInputStream()))
            return inputStream.readLine()
        }
        override fun onComplete(result: String?) {
            if(result != null && !result.startsWith("unable")) {
                notify(result)
            }
        }
    }.start()
}