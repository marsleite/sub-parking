package com.tech.parking.application.routes

import com.tech.parking.application.handler.ParkingHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class ParkingController {

  @Bean
  fun parkingRoutes(parkingHandler: ParkingHandler) = coRouter {
    POST("/parking/checkin", parkingHandler::registerCheckin)
    PUT("/parking/checkout", parkingHandler::registerCheckout)
    GET("/transactions/{plate}", parkingHandler::getStatistics)
  }
}