package xyz.vercaemer.app

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Query
import retrofit2.http.Url

data class Item(val content: String)

interface ApiService {
    @GET
    suspend fun getData(@Url url: String, @HeaderMap headers: Map<String, String>): Response<List<Item>>
}