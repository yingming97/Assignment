package pham.hien.assignment.Activity.Main

import android.content.Intent
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import pham.hien.assignment.R
import pham.hien.assignment.ViewModel.SignInViewModel
import pham.hien.assignment.databinding.ActivitySignInBinding

class SignInActivity :
    BaseActivity<ActivitySignInBinding, SignInViewModel>(ActivitySignInBinding::inflate) {

    override fun initListener() {
        binding.btnLogin.setOnClickListener(this)
    }

    override fun getViewModel(): Class<SignInViewModel> {
        return SignInViewModel::class.java
    }

    override fun initObserver() {
    }

    override fun initDataDefault() {
        val zenitsuAnim = AnimationUtils.loadAnimation(this, R.anim.amin_zenitsu_1)
        binding.imvZenitsu.animation = zenitsuAnim
    }

    override fun onClick(v: View) {
        when (v) {
            binding.btnLogin -> {
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
    }
}