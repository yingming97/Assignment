package pham.hien.assignment.API

import android.content.Context
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import pham.hien.assignment.BuildConfig
import pham.hien.assignment.Utils.Constant
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface GetListAllManga {
    @GET("get_list_all_truyen_tranh.php")
    fun getListAllManga(): Call<ResponseBody>

    object Factory {
        private var api: GetListAllManga? = null
        fun getInstance(context: Context): GetListAllManga? {
            return if (api == null) {
                val builder: OkHttpClient.Builder = OkHttpClient().newBuilder()
                builder.readTimeout(15, TimeUnit.SECONDS)
                builder.connectTimeout(10, TimeUnit.SECONDS)
                builder.writeTimeout(10, TimeUnit.SECONDS)
                if (BuildConfig.DEBUG) {
                    val interceptor = HttpLoggingInterceptor()
                    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
                    builder.addInterceptor(interceptor)
                }
                val cacheSize = 50 * 1024 * 1024
                val cache = Cache(context.cacheDir, cacheSize.toLong())
                builder.cache(cache)
                val gson = GsonBuilder()
                    .setLenient()
                    .create()
                val retrofit = Retrofit.Builder()
                    .client(builder.build())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .baseUrl(Constant.URL.LOCAL)
                    .build()
                api = retrofit.create(
                    GetListAllManga::class.java)
                api
            } else {
                api
            }
        }
    }
}