package pham.hien.assignment.API

import android.content.Context
import androidx.viewbinding.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import pham.hien.assignment.Model.User
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface ApiGetList {

    @GET("get_list_user.php")
   suspend fun getListUser(): ArrayList<User>

//    object Factory {
//        private var api: ApiGetList? = null
//        fun getInstance(context: Context): ApiGetList? {
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
//                    .baseUrl("https://yonmanga.000webhostapp.com/api/")
//                    .build()
//                api = retrofit.create(
//                    ApiGetList::class.java)
//                api
//            } else {
//                api
//            }
//        }
//    }
}