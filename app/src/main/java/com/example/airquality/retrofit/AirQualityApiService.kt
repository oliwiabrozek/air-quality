package com.example.airquality.retrofit

import com.example.airquality.model.TestStand
import retrofit2.Call
import retrofit2.http.GET

interface AirQualityApiService {
  @GET("findAll")
  fun findAll(): Call<List<TestStand>>

}