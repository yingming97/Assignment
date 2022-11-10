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
class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>(ActivitySplashBinding::inflate) {

    override fun initListener() {

    }
    override fun getViewModel(): Class<SplashViewModel> {
        return SplashViewModel::class.java
    }

    override fun initDataDefault() {
        val animation = AnimationUtils.loadAnimation(this, R.anim.slide_in_right)
        binding.imvLogo.startAnimation(animation)
        viewModel.loadProgressBarSplashDefault()
    }

    override fun initObserver() {
        viewModel.loadingSplashDefaultLiveData.observe(this) {
            binding.progressBarSplashDefault.progress = it
            binding.tvProgressBarSplashDefault.text = "Loading ... ${(it * 100 / 200)}%"
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    override fun onClick(v: View?) {

    }
}