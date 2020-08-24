package kz.tinker.pexel

import android.util.Log
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kz.tinker.pexel.data.api.PexelApi
import kz.tinker.pexel.data.repository.CuratedPhotosRepository
import kz.tinker.pexel.data.repository.PhotoRepository
import kz.tinker.pexel.ui.main.viewmodel.CuratedPhotosViewModel
import kz.tinker.pexel.ui.main.viewmodel.PhotoViewModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

const val API_KEY = "563492ad6f917000010000013305ed5e40cd4ea9964c37034d0762f3"

val viewModelModule = module {
    viewModel {
        PhotoViewModel(get())
    }
    viewModel {
        CuratedPhotosViewModel(get())
    }
}

val repositoryModule = module {
    single {
        PhotoRepository(get())
    }
    single {
        CuratedPhotosRepository(get())
    }
}

val apiModule = module {
    fun provideUseApi(retrofit: Retrofit): PexelApi {
        return retrofit.create(PexelApi::class.java)
    }

    single { provideUseApi(get()) }
}

val retrofitModule = module {

    fun provideGson(): Gson {
        return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
    }

    fun getLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor(logger = object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Log.d("OkHttp", message)
            }
        }).apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    fun getAuthInterceptor(): Interceptor {
        return object : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {
                val request: Request = chain.request().newBuilder()
                    .addHeader("Authorization", API_KEY).build()
                return chain.proceed(request)
            }
        }
    }

    fun provideHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
            .addInterceptor(getAuthInterceptor())
            .addInterceptor(getLoggingInterceptor())
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)

        return okHttpClientBuilder.build()
    }


    fun provideRetrofit(factory: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.pexels.com/")
            .addConverterFactory(GsonConverterFactory.create(factory))
            .client(client)
            .build()
    }

    single { provideGson() }
    single { provideHttpClient() }
    single { provideRetrofit(get(), get()) }
}

