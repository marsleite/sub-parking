package com.tech.parking.gateway

import com.tech.parking.model.Parking

interface ParkingCache {

  suspend fun getParkingCache(numberPlate: String): List<Parking>
  suspend fun saveParkingCache(numberPlate: String, parking: Parking)
}