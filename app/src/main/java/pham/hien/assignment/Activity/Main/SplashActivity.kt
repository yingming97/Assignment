package pham.hien.assignment.Activity.Main

import android.annotation.SuppressLint
import android.content.Intent
import android.view.View
import android.view.animation.AnimationUtils
import androidx.lifecycle.ViewModelProvider
import pham.hien.assignment.R
import pham.hien.assignment.ViewModel.SplashViewModel
import pham.hien.assignment.databinding.ActivitySplashBinding

@SuppressLint("CustomSplashScreen")
class SplashActivity :
    BaseActivity<ActivitySplashBinding, SplashViewModel>(ActivitySplashBinding::inflate) {

    override fun initListener() {

    }

    override fun getViewModel(): Class<SplashViewModel> {
        return SplashViewModel::class.java
    }

    override fun initDataDefault() {
        viewModel.loadProgressBarSplashDefault()
    }

    @SuppressLint("SetTextI18n")
    override fun initObserver() {
        viewModel.loadingSplashDefaultLiveData.observe(this) {
            binding.progressBarSplashDefault.progress = it
            binding.tvProgressBarSplashDefault.text = "Loading ... ${(it * 100 / 200)}%"
            if (it == binding.progressBarSplashDefault.max) {
                startActivity(Intent(this, SignInActivity::class.java))
                finish()
            }
        }
    }

    override fun onClick(v: View) {

    }
}