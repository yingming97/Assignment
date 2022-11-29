package pham.hien.assignment.API

import android.content.Context
import androidx.viewbinding.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import pham.hien.assignment.Model.User
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface PostTest {
    @POST("login.php")
    suspend fun login(@Body user: User): User
//    @POST("login.php")
//    fun login(@Body user: User?): Call<ResponseBody?>
//
//    object Factory {
//        private var api: PostTest? = null
//        fun getInstance(context: Context): PostTest? {
//            return if (api == null) {
//                val builder: OkHttpClient.Builder = OkHttpClient().newBuilder()
//                builder.readTimeout(15, TimeUnit.SECONDS)
//                builder.connectTimeout(10, TimeUnit.SECONDS)
//                builder.writeTimeout(10, TimeUnit.SECONDS)
//                if (BuildConfig.DEBUG) {
//                    val interceptor = HttpLoggingInterceptor()
//                    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
//                    builder.addInterceptor(interceptor)
//                }
//                val cacheSize = 50 * 1024 * 1024
//                val cache = Cache(context.cacheDir, cacheSize.toLong())
//                builder.cache(cache)
//                val gson = GsonBuilder()
//                    .setLenient()
//                    .create()
//                val retrofit = Retrofit.Builder()
//                    .client(builder.build())
//                    .addConverterFactory(GsonConverterFactory.create(gson))
//                    .baseUrl("http://192.168.0.103/api/")
//                    .build()
//                api = retrofit.create(
//                    PostTest::class.java)
//                api
//            } else {
//                api
//            }
//        }
//    }
}