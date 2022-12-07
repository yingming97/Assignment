package pham.hien.assignment.Activity.Main

import android.content.Intent
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import pham.hien.assignment.R
import pham.hien.assignment.Utils.Constant
import pham.hien.assignment.Utils.NetworkUtils
import pham.hien.assignment.ViewModel.SignInViewModel
import pham.hien.assignment.databinding.ActivitySignInBinding

class SignInActivity :
    BaseActivity<ActivitySignInBinding, SignInViewModel>(ActivitySignInBinding::inflate) {

    private lateinit var gso: GoogleSignInOptions
    private lateinit var gsc: GoogleSignInClient
    private lateinit var mFBAuth: FirebaseAuth
    override fun initListener() {
        binding.btnLogin.setOnClickListener(this)
        binding.tvTitle.setOnClickListener(this)
    }

    override fun getViewModel(): Class<SignInViewModel> {
        return SignInViewModel::class.java
    }

    override fun initObserver() {
        viewModel.loginSuccess.observe(this) {
            if (it) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
    }

    override fun initDataDefault() {
        val zenitsuAnim = AnimationUtils.loadAnimation(this, R.anim.amin_zenitsu_1)
        binding.imvZenitsu.animation = zenitsuAnim

        gso =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
        gsc = GoogleSignIn.getClient(this, gso)
        mFBAuth = FirebaseAuth.getInstance()
    }

    override fun onClick(v: View) {
        when (v) {
            binding.btnLogin -> {
                if (NetworkUtils.isNetworkOnline(this)) {
                    signInGoogle()
                } else {
                    Toast.makeText(this, "Bạn chưa bật kết nối Internet", Toast.LENGTH_LONG).show()
                }
            }
            binding.tvTitle -> {
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
    }

    private fun signInGoogle() {
        val intent = gsc.signInIntent
        startActivityForResult(intent, Constant.CODE.SIGN_GOOGLE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            Constant.CODE.SIGN_GOOGLE -> {
                val task: Task<GoogleSignInAccount> =
                    GoogleSignIn.getSignedInAccountFromIntent(data)
                viewModel.handleSignInResult(this, task, mFBAuth)
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}