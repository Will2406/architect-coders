package architec.coders.pokedex.data

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit


object ApiConfig {

    private const val TIMEOUT_READ = 60L
    private const val TIMEOUT_WRITE = 60L
    private const val TIMEOUT_CONNECT = 60L

    inline fun <reified T> createInstance(): T {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(createClient())
            .build()
            .create(T::class.java)
    }

    fun createClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_WRITE, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT_CONNECT, TimeUnit.SECONDS)
            .build()
    }

}