package pham.hien.assignment.ViewModel

import android.app.ProgressDialog
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import pham.hien.assignment.API.LoginApi
import pham.hien.assignment.Model.User
import pham.hien.assignment.Utils.SharedPrefUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInViewModel : ViewModel() {

    private val TAG = "YingMing"
    var loginSuccess = MutableLiveData<Boolean>()
    fun handleSignInResult(
        context: Context,
        taskComplete: Task<GoogleSignInAccount>,
        auth: FirebaseAuth,
    ) {
        try {
            showProgressDialog(context)
            val acc: GoogleSignInAccount = taskComplete.getResult(ApiException::class.java)
            firebaseGoogleAuth(context, acc, auth)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun firebaseGoogleAuth(
        context: Context,
        acc: GoogleSignInAccount?,
        mFBAuth: FirebaseAuth,
    ) {
        val authCredential: AuthCredential = GoogleAuthProvider.getCredential(acc!!.idToken, null)
        mFBAuth.signInWithCredential(authCredential).addOnCompleteListener {
            if (it.isSuccessful) {
                val mFUser = mFBAuth.currentUser!!
                val useModel = User(
                    mFUser.displayName,
                    mFUser.email,
                    mFUser.photoUrl.toString(),
                    mFUser.uid)
                requestLogin(context, useModel)
            } else {
                hideProgressDialog()
                Toast.makeText(context, "Lỗi", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    private fun requestLogin(context: Context, userModel: User) {
        viewModelScope.launch(Dispatchers.IO) {
            val loginApi = LoginApi.Factory.getInstance(context)!!
            loginApi.login(userModel).enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>,
                ) {
                    hideProgressDialog()
                    val type = object : TypeToken<User>() {}.type
                    val gson = Gson()
                    val user: User = gson.fromJson(response.body()?.string(), type)
                    Log.d(TAG, "createNewAccount: $user")
                    Log.d(TAG, "createNewAccount: ${response.code()}")
                    SharedPrefUtils.setLoginInformationUser(context, user)
                    loginSuccess.value = true
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    hideProgressDialog()
                }
            })
        }
    }
    private lateinit var mProgressDialog: ProgressDialog
    fun showProgressDialog(context: Context) {
        mProgressDialog = ProgressDialog(context)
        mProgressDialog.setMessage("Vui lòng đợi một chút")
        mProgressDialog.isIndeterminate = true
        mProgressDialog.show()
    }

    fun hideProgressDialog() {
        if (mProgressDialog.isShowing) {
            mProgressDialog.dismiss()
        }
    }
}