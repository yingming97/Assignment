package pham.hien.assignment.ViewModel

import android.annotation.SuppressLint
import android.os.Handler
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pham.hien.assignment.API.ApiGetList
import pham.hien.assignment.API.RetrofitHelper
import retrofit2.create
import java.util.*

class SplashViewModel : ViewModel() {

    private val TAG = "YingMing"
    private var timer: Timer? = null
    val loadingSplashDefaultLiveData = MutableLiveData<Int>()

    fun loadProgressBarSplashDefault() {
        val handler = Handler()
        val progress = intArrayOf(0)

        val update = Runnable {
            if (progress[0] < 200) {
                progress[0]++
                loadingSplashDefaultLiveData.value = progress[0]
            } else {
                timer?.cancel()

            }
        }
        timer?.cancel()
        timer = Timer()
        timer!!.schedule(object : TimerTask() {
            override fun run() {
                handler.post(update)
            }
        }, 300, 30)
    }

    fun getListUser() {
        viewModelScope.launch {
            val aipGetListUser = RetrofitHelper.getInstance().create<ApiGetList>()
            Log.d(TAG, "getListUser: ${aipGetListUser.getListUser()}")
        }
    }
}