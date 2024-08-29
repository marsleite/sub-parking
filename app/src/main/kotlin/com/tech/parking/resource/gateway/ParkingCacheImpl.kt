package com.tech.parking.resource.gateway

import com.tech.parking.gateway.ParkingCache
import com.tech.parking.model.Parking
import org.springframework.cache.CacheManager
import org.springframework.stereotype.Component

@Component
class ParkingCacheImpl(
  val cacheManager: CacheManager
): ParkingCache {
  companion object {
    private const val CACHE_NAME = "parkingCache"
  }

  override suspend fun getParkingCache(numberPlate: String): List<Parking> {
    val cache = cacheManager.getCache(CACHE_NAME)
    return cache?.get(numberPlate)?.get() as? List<Parking> ?: emptyList()
  }

  override suspend fun saveParkingCache(numberPlate: String, parking: Parking) {
    val cache = cacheManager.getCache(CACHE_NAME)
    cache?.put(numberPlate, parking)
  }
}