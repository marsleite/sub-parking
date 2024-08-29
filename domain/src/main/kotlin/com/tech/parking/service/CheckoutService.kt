package com.tech.parking.service

import com.tech.parking.gateway.ParkingCache
import com.tech.parking.gateway.ParkingRepositoryGateway
import com.tech.parking.model.Status
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class CheckoutService(
  private val cache: ParkingCache,
  private val parkingRepositoryGateway: ParkingRepositoryGateway,
  private val paymentParking: ProcessPaymentParking
) {

  suspend fun execute(placa: String): BigDecimal {
    val cachedParking = cache.getParkingCache(placa)
      .filter { parking -> parking.status == Status.ACTIVE }

    val getCacheParking = cachedParking.firstOrNull() ?: throw RuntimeException("Estacionamento não encontrado no cache")

    val checkout = parkingRepositoryGateway.parkingCheckout(getCacheParking.numberPlate)

    return paymentParking.execute(checkout)
  }
}