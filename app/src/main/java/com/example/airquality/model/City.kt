package com.example.airquality.model


import com.google.gson.annotations.SerializedName

data class City(
  @SerializedName("commune")
  val commune: Commune,
  @SerializedName("id")
  val id: Int,
  @SerializedName("name")
  val name: String
)