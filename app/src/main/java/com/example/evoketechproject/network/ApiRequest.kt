package com.example.evoketechproject.network

import com.example.evoketechproject.models.response.UserResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit
import javax.net.SocketFactory

/**
 * All Api request
 * @author Arnab
 */
interface ApiRequest {

    /**
     * Get users API
     */
    @GET(value = "users")
    suspend fun getUsers(): Response<ArrayList<UserResponse>>?


    companion object {
        operator fun invoke(networkConnectionInterceptor: NetworkConnectionInterceptor): ApiRequest {

            val logging = HttpLoggingInterceptor()
            // set your desired log level
            logging.level = HttpLoggingInterceptor.Level.BODY

            val httpClient = OkHttpClient.Builder()
            httpClient.readTimeout(3000, TimeUnit.MILLISECONDS)
            httpClient.connectTimeout(3000, TimeUnit.MILLISECONDS)

            // add your other interceptors …

            // add logging as last interceptor
            httpClient.addInterceptor(logging)  // <-- this is the important line!


            return Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .client(httpClient.build())  //  <-- for logging and request and response
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiRequest::class.java)
        }
    }
}