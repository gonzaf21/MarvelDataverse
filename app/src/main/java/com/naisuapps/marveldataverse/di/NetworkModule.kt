package com.naisuapps.marveldataverse.di

import com.naisuapps.marveldataverse.BuildConfig
import com.naisuapps.marveldataverse.data.model.enums.Scope
import com.naisuapps.marveldataverse.data.network.CharacterApiClient
import com.naisuapps.marveldataverse.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        // Initialize interceptor with apikey and for logging.
        val client = OkHttpClient().newBuilder()
            .addInterceptor(ApiKeyInterceptor())
            .addInterceptor(HttpLoggingInterceptor().apply {
                level =
                    if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            })
            .build()

        return Retrofit.Builder()
            .baseUrl(Scope.getApiBaseUrlByScope(Constants.currentScope))
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideCharacterApiClient(retrofit: Retrofit): CharacterApiClient {
        return retrofit.create(CharacterApiClient::class.java)
    }
}

/**
 * Class needed to pass query parameters on calls and make it only here.
 */
class ApiKeyInterceptor(private val apiKey: String = Constants.API_KEY) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val url = request.url.newBuilder()
            .addQueryParameter("apikey", apiKey)
            .addQueryParameter("hash", Constants.hash())
            .addQueryParameter("ts", Constants.ts)
            .build()

        val newRequest = request.newBuilder()
            .url(url)
            .build()

        return chain.proceed(newRequest)
    }
}