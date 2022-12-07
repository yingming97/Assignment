package pham.hien.assignment.Utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import pham.hien.assignment.Utils.NetworkUtils
import java.lang.Exception

object NetworkUtils {
    var TYPE_WIFI = 1
    var TYPE_MOBILE = 2
    var TYPE_NOT_CONNECTED = 0
    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    fun isNetworkOnline(context: Context): Boolean {
        var status = false
        try {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            var netInfo = cm.getNetworkInfo(0)
            if (netInfo != null && netInfo.state == NetworkInfo.State.CONNECTED) {
                status = true
            } else {
                netInfo = cm.getNetworkInfo(1)
                if (netInfo != null && netInfo.state == NetworkInfo.State.CONNECTED) status = true
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
        return status
    }

    fun getConnectivityStatus(context: Context): Int {
        val cm = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        if (null != activeNetwork) {
            if (activeNetwork.type == TYPE_WIFI) return TYPE_WIFI
            if (activeNetwork.type == ConnectivityManager.TYPE_MOBILE) return TYPE_MOBILE
        }
        return TYPE_NOT_CONNECTED
    }

    fun checkInternetConnecting(context: Context): Boolean {
        val connectivity =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val info = connectivity.allNetworkInfo
        for (networkInfo in info) {
            if (networkInfo.state == NetworkInfo.State.CONNECTED) return true
        }
        return false
    }
}