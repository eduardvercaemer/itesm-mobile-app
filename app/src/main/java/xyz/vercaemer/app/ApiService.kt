package xyz.vercaemer.app

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

private const val apikey =
    "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImFicHZwcGJibXJraXVjdWV2bndvIiwicm9sZSI6ImFub24iLCJpYXQiOjE2NjA2MDQ1ODcsImV4cCI6MTk3NjE4MDU4N30.gh3rZmC--6uX-ytmhacWM3vR_XtOtE-RcbSrMJW9Eiw"

private const val url = "https://abpvppbbmrkiucuevnwo.supabase.co/"

data class Token(
    val access_token: String,
    val token_type: String,
    val expires_in: Int,
    val refresh_token: String,
    val user: User
)

data class User(
    val id: String,
    val email: String
)

data class Credentials(
    val email: String,
    val password: String,
)

interface ApiService {
    @POST("auth/v1/token?grant_type=password")
    suspend fun signIn(@Body body: Credentials): Response<Token>
}

fun getRetrofit(): ApiService {
    val client = OkHttpClient.Builder()
        .addInterceptor(Interceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("apikey", apikey)
                .build()
            chain.proceed(request)
        }).build()

    return Retrofit.Builder()
        .client(client)
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)
}