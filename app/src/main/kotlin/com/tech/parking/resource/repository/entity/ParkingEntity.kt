package com.tech.parking.resource.repository.entity

import com.tech.parking.model.Parking
import com.tech.parking.model.Status
import org.springframework.data.annotation.Id
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

data class ParkingEntity(
  @Id
  private var id: String? = UUID.randomUUID().toString(),
  val numberPlate: String,
  val entryTime: LocalDateTime,
  val exitTime: LocalDateTime?,
  val parkingDuration: LocalDateTime?,
  val price: BigDecimal?,
  val status: Status
) {

  constructor(parking: Parking): this(
    id = parking.id,
    numberPlate = parking.numberPlate,
    entryTime = parking.entryTime,
    exitTime = parking.exitTime,
    parkingDuration = parking.parkingDuration,
    price = parking.price,
    status = parking.status
  )

  fun toDomain() = Parking(
    id = id,
    numberPlate = numberPlate,
    entryTime = entryTime,
    exitTime = exitTime,
    parkingDuration = parkingDuration,
    price = price,
    status = status
  )
}
