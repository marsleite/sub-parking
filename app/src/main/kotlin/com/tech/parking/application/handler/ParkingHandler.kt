package com.tech.parking.application.handler

import com.tech.parking.application.dto.CheckinRequest
import com.tech.parking.service.CheckinService
import com.tech.parking.service.CheckoutService
import com.tech.parking.service.StatisticsService
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.awaitBody
import org.springframework.web.reactive.function.server.bodyValueAndAwait

@Component
class ParkingHandler(
  private val checkinService: CheckinService,
  private val checkoutService: CheckoutService,
  private val statisticsService: StatisticsService
) {

  suspend fun registerCheckin(request: ServerRequest): ServerResponse {
    val checkin = request.awaitBody<CheckinRequest>().toDomain()

    val parking = checkinService.execute(checkin)

    return ServerResponse.ok().bodyValueAndAwait(parking)
  }

  suspend fun registerCheckout(request: ServerRequest): ServerResponse {
    val checkin = request.awaitBody<CheckinRequest>().toDomain()

    val parking = checkoutService.execute(checkin.numberPlate)

    return ServerResponse.ok().bodyValueAndAwait(parking)
  }

  suspend fun getStatistics(request: ServerRequest): ServerResponse {
    val checkin = request.awaitBody<CheckinRequest>().toDomain()
    val statistics = statisticsService.execute(checkin.numberPlate)

    return ServerResponse.ok().bodyValueAndAwait(statistics)
  }
}