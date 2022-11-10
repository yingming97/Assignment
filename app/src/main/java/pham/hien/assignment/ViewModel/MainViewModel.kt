package pham.hien.assignment.ViewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import pham.hien.assignment.API.ApiGetList
import pham.hien.assignment.API.RetrofitHelper
import pham.hien.assignment.Model.User
import ppham.hien.assignment.Utils.executeAsyncTask
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create
import java.io.IOException

class MainViewModel : ViewModel() {

    private val TAG = "YingMing"
    val mListUserLiveData = MutableLiveData<ArrayList<User>>()

    fun getListUser() {

        viewModelScope.launch {
            val apiGetList = RetrofitHelper.getInstance().create<ApiGetList>()
            mListUserLiveData.postValue(apiGetList.getListUser())
        }

    }

}