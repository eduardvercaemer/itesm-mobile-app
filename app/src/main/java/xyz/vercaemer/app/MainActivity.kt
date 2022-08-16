package xyz.vercaemer.app

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import xyz.vercaemer.app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val apikey =
        "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImFicHZwcGJibXJraXVjdWV2bndvIiwicm9sZSI6ImFub24iLCJpYXQiOjE2NjA2MDQ1ODcsImV4cCI6MTk3NjE4MDU4N30.gh3rZmC--6uX-ytmhacWM3vR_XtOtE-RcbSrMJW9Eiw"

    private val url = "https://abpvppbbmrkiucuevnwo.supabase.co/rest/v1/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getSomeData()
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getSomeData() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit()
                .create(ApiService::class.java)
                .getData(
                    "todos",
                    mapOf(
                        "apikey" to apikey,
                        "Authorization" to "Bearer $apikey"
                    )
                )
            val data = call.body()
            if (call.isSuccessful) {
                Log.d("MainActivity", data.toString())
            } else {
                // log error
                call.errorBody()?.string()?.let {
                    Log.d("MainActivity", it)
                }
            }
        }
    }
}