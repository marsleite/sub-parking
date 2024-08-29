package com.tech.parking.resource.repository.sql.spring

import com.tech.parking.model.Status
import com.tech.parking.resource.repository.entity.ParkingEntity
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface ParkingRepositorySpring: CoroutineCrudRepository<ParkingEntity, String> {

  suspend fun findAllByNumberPlateAndStatus(numberPlate: String, status: Status): List<ParkingEntity>
}