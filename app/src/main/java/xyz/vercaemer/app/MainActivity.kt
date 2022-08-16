package xyz.vercaemer.app

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import xyz.vercaemer.app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getSomeData()
    }


    private fun getSomeData() {
        CoroutineScope(Dispatchers.IO).launch {
            val service = getRetrofit()
            service.signIn(
                Credentials(
                    email = "test000@mail.com",
                    password = "Password66"
                )
            ).let {
                val status = it.code()
                Log.d("MainActivity", "Status: $status")
                if (!it.isSuccessful) {
                    val error = it.errorBody()?.string()
                    Log.d("MainActivity", "Error: ${error}")
                    return@launch
                }

                val token = it.body()!!
                Log.d("MainActivity", "Token: $token")
            }
        }
    }
}