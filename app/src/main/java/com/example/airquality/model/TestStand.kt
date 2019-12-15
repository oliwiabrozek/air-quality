package com.example.airquality.model


import com.google.gson.annotations.SerializedName

// http://api.gios.gov.pl/pjp-api/rest/station/findAll

data class TestStand(
  @SerializedName("addressStreet")
  val addressStreet: Any,
  @SerializedName("city")
  val city: City,
  @SerializedName("gegrLat")
  val gegrLat: String,
  @SerializedName("gegrLon")
  val gegrLon: String,
  @SerializedName("id")
  val id: Int,
  @SerializedName("stationName")
  val stationName: String
)