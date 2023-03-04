package uz.leerybit.task.model.di

import com.google.gson.Gson
import com.pluto.plugins.network.PlutoInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.leerybit.task.BuildConfig
import uz.leerybit.task.utils.extentions.logd
import uz.usoft.merchapp.utils.Constants
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @[Provides Singleton]
    fun provideRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        client: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .addConverterFactory(gsonConverterFactory)
        .client(client)
        .baseUrl("https://www.kattabozor.uz/hh/test/api/v1/")
        .build()

    @[Provides Singleton]
    fun provideClient(
        loggingInterceptor: HttpLoggingInterceptor,
        plutoInterceptor: PlutoInterceptor
    ) = OkHttpClient.Builder()
        .readTimeout(Constants.WRITE_TIME_OUT, TimeUnit.SECONDS)
        .writeTimeout(Constants.WRITE_TIME_OUT, TimeUnit.SECONDS)
        .addInterceptor(loggingInterceptor)
        .addInterceptor(plutoInterceptor)
        .build()

    @[Provides Singleton]
    fun providePlutoInterceptor() = PlutoInterceptor()



    @[Provides Singleton]
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor {
            logd(it)
        }
        interceptor.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
        else HttpLoggingInterceptor.Level.NONE
        return interceptor
    }

    @[Provides Singleton]
    fun provideGsonConverter(gson: Gson): GsonConverterFactory = GsonConverterFactory.create(gson)

    @[Provides Singleton]
    fun provideGson(): Gson = Gson()
}