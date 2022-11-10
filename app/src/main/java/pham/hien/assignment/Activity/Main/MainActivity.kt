package pham.hien.assignment.Activity.Main

import android.util.Log
import android.view.View
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody
import pham.hien.assignment.API.ApiGetList
import pham.hien.assignment.Model.User
import pham.hien.assignment.ViewModel.MainViewModel
import pham.hien.assignment.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class MainActivity :
    BaseActivity<ActivityMainBinding, MainViewModel>(ActivityMainBinding::inflate) {

    private val TAG = "YingMing"

    override fun initListener() {

    }

    override fun getViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun initObserver() {
        viewModel.mListUserLiveData.observe(this) {
            Log.d(TAG, "initObserver: $it")
            binding.tv.text = it.toString()
        }
    }

    override fun initDataDefault() {
        viewModel.getListUser()

    }

    override fun onClick(v: View?) {

    }


}