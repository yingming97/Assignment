package pham.hien.assignment.API

import pham.hien.assignment.Utils.Constant
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

   private val baseUrl = "http://192.168.1.210/api/"
    private val baseUrlLocal = "http://192.168.0.104/api/"

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(Constant.URL.LOCAL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}