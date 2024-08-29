package com.tech.parking.resource.repository.sql

import com.tech.parking.gateway.ParkingRepositoryGateway
import com.tech.parking.model.Parking
import com.tech.parking.model.Status
import com.tech.parking.resource.repository.entity.ParkingEntity
import com.tech.parking.resource.repository.sql.spring.ParkingRepositorySpring
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class ParkingRepositoryImpl(
  private val parkingRepositorySpring: ParkingRepositorySpring
): ParkingRepositoryGateway {
  override suspend fun parkingCheckin(parking: Parking): Parking {
    return parkingRepositorySpring.save(ParkingEntity(parking)).toDomain()
  }

  override suspend fun parkingCheckout(numberPlate: String): Parking? {
    val checkoutParking = parkingRepositorySpring.findAllByNumberPlateAndStatus(numberPlate, Status.ACTIVE).firstOrNull()

    return if (checkoutParking != null) {
      parkingRepositorySpring.save(
        checkoutParking.copy(exitTime = LocalDateTime.now(),
          status = Status.FINISHED)
      ).toDomain()
    } else {
      null
    }
  }

  override suspend fun findAllParking(numberPlate: String): Flow<Parking>? {
    return parkingRepositorySpring.findAll().map { it.toDomain() }
  }
}