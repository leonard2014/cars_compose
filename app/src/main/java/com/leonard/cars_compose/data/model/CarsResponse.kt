package com.leonard.cars_compose.data

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

typealias CarsResponse = List<CarsResponseItem>

data class CarsResponseItem(

	@field:SerializedName("image")
	val image: String?,

	@field:SerializedName("color")
	val color: String,

	@field:SerializedName("year")
	val year: Int,

	@field:SerializedName("configuration")
	val configuration: Configuration,

	@field:SerializedName("mpg")
	val mpg: Int?,

	@field:SerializedName("price")
	val price: Int,

	@field:SerializedName("origin")
	val origin: String?,

	@field:SerializedName("make")
	val make: Make
)

data class Make(

	@field:SerializedName("model")
	val model: String,

	@field:SerializedName("manufacturer")
	val manufacturer: String
)

data class Configuration(

	@field:SerializedName("horsepower")
	val horsepower: Int?,

	@field:SerializedName("body")
	val body: String,

	@field:SerializedName("cylinders")
	val cylinders: Int?
)
