package com.example.test_android.data

import com.example.test_android.domain.utils.Constants
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET(Constants.API_PRODUCTS)
    suspend fun getProducts(): Response<ArrayList<ApiItem>>
}