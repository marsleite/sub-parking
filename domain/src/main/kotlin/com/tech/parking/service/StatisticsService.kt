package com.tech.parking.service

import com.tech.parking.gateway.ParkingRepositoryGateway
import com.tech.parking.model.Parking
import kotlinx.coroutines.flow.Flow
import org.springframework.stereotype.Service

@Service
class StatisticsService(
    private val parkingRepositoryGateway: ParkingRepositoryGateway
) {
    suspend fun execute(numberPlate: String): Flow<Parking> {
        return parkingRepositoryGateway.findAllParking(numberPlate)
    }
}