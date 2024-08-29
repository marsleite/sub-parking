package com.tech.parking.service

import com.tech.parking.model.Parking
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.Duration
import java.time.LocalDateTime

@Service
class ProcessPaymentParking {

  fun execute(parking: Parking): BigDecimal {
    val hourIn: LocalDateTime = parking.entryTime
    val hourOut: LocalDateTime = parking.exitTime

    val hoursDiffer: Long = Duration.between(hourIn, hourOut).toHours()

    var valor = BigDecimal.valueOf(3.00)

    if (hoursDiffer > 2) {
      val hoursAdd = hoursDiffer - 2
      val priceAdd = BigDecimal.valueOf(1.50).multiply(BigDecimal.valueOf(hoursAdd))
      valor = valor.add(priceAdd)
    }

    return valor
  }
}