package pham.hien.assignment.ViewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody
import pham.hien.assignment.API.GetListAllManga
import pham.hien.assignment.Model.Manga
import pham.hien.assignment.Model.User
import pham.hien.assignment.Utils.SharedPrefUtils
import ppham.hien.assignment.Utils.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val TAG = "YingMing"
    val mListMangaLiveData = MutableLiveData<ArrayList<Manga>>()

    fun getListManga(context: Context) {
        viewModelScope.launch(
            onPreExecute = {},
            doInBackground = {
                val apiGetListManga = GetListAllManga.Factory.getInstance(context)
                apiGetListManga?.getListAllManga()?.enqueue(object : Callback<ResponseBody> {
                    override fun onResponse(
                        call: Call<ResponseBody>,
                        response: Response<ResponseBody>,
                    ) {
                        val type = object : TypeToken<ArrayList<Manga>>() {}.type
                        val gson = Gson()
                        val listManga: ArrayList<Manga> =
                            gson.fromJson(response.body()?.string(), type)
                        mListMangaLiveData.value = listManga
                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        t.printStackTrace()
                    }

                })

            },
            onPostExecute = {}
        )
    }
}