package com.tech.parking.service

import com.tech.parking.gateway.ParkingRepositoryGateway
import com.tech.parking.model.Parking
import org.springframework.stereotype.Service

@Service
class CheckinService(
    private val parkingRepositoryGateway: ParkingRepositoryGateway
) {

    suspend fun execute(parking: Parking): Parking {
        return parkingRepositoryGateway.parkingCheckin(parking)
    }
}