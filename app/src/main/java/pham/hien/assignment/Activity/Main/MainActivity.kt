package pham.hien.assignment.Activity.Main

import android.content.Intent
import android.util.Log
import android.view.View
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody
import pham.hien.assignment.API.ApiGetList
import pham.hien.assignment.Activity.User.ProfileActivity
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
        binding.imvAvatar.setOnClickListener(this)
        binding.imvProfile.setOnClickListener(this)
        binding.imvFilter.setOnClickListener(this)
        binding.layoutReadContinue.setOnClickListener(this)
        binding.layoutToolBar.setOnClickListener(this)
    }

    override fun getViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun initObserver() {

    }

    override fun initDataDefault() {
    }

    override fun onClick(v: View) {
        when (v) {
            binding.imvAvatar, binding.imvProfile, binding.layoutToolBar -> {
                startActivity(Intent(this, ProfileActivity::class.java))
            }
            binding.imvFilter -> {}
            binding.layoutReadContinue -> {}
        }
    }
}