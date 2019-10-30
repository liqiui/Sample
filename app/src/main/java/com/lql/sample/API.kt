package com.lql.sample

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.lql.sample.data.Photo
import com.lql.sample.data.User
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL: String = "https://jsonplaceholder.typicode.com"

interface API {

    @GET("users")
    fun users(): Call<List<User>>

    @GET("photos")
    fun photos(@Query("albumId") albumId: Int? = 0): Call<List<Photo>>

    companion object {
        private fun makeJsonConverterFactory(): Converter.Factory {
            return JacksonConverterFactory.create(
                jacksonObjectMapper()
                    .enable(MapperFeature.USE_BASE_TYPE_AS_DEFAULT_IMPL)
                    .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                    .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE))
        }

        private fun buildClient(parameters: Map<String, Any>? = null): OkHttpClient {

            val clientBuilder = OkHttpClient().newBuilder()
            clientBuilder.addInterceptor(HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            })

            return clientBuilder.build()
        }

        fun create(parameters: Map<String, Any>? = null): API {
            val retrofitBuilder = Retrofit.Builder()
                .addConverterFactory( makeJsonConverterFactory())
                .baseUrl(BASE_URL)
            retrofitBuilder.client(buildClient(parameters))
            return retrofitBuilder.build().create(API::class.java)
        }
    }
}