package com.tech.parking.gateway

import com.tech.parking.model.Parking
import kotlinx.coroutines.flow.Flow

interface ParkingRepositoryGateway {

  suspend fun parkingCheckin(parking: Parking): Parking

  suspend fun parkingCheckout(numberPlate: String): Parking?

  suspend fun findAllParking(numberPlate: String): Flow<Parking>?
}