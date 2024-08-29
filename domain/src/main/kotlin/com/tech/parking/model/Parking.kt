package com.tech.parking.model

import java.math.BigDecimal
import java.time.LocalDateTime

data class Parking(
  val id: String?,
  val numberPlate: String,
  val entryTime: LocalDateTime,
  val exitTime: LocalDateTime?,
  val parkingDuration: LocalDateTime?,
  val price: BigDecimal?,
  val status: Status
)
