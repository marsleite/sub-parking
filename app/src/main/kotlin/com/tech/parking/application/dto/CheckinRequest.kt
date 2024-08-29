package com.tech.parking.application.dto

import com.tech.parking.model.Parking
import com.tech.parking.model.Status
import java.math.BigDecimal
import java.time.LocalDateTime

data class CheckinRequest(
  val numberPlate: String
) {

  fun toDomain() = Parking(
    id = "",
    numberPlate = numberPlate,
    entryTime = LocalDateTime.now(),
    exitTime = LocalDateTime.now(),
    parkingDuration = LocalDateTime.now(),
    price = BigDecimal.ZERO,
    status = Status.ACTIVE
  )
}