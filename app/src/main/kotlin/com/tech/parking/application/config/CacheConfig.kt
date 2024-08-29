package com.tech.parking.application.config

import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.CachingConfigurerSupport
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import java.time.Duration

@Configuration
@EnableCaching
class CacheConfig : CachingConfigurerSupport() {

  @Bean
  fun cacheManager(connectionFactory: RedisConnectionFactory): CacheManager {
    val cacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
      .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(GenericJackson2JsonRedisSerializer()))

    return RedisCacheManager.builder(connectionFactory)
      .cacheDefaults(cacheConfiguration.entryTtl(Duration.ofHours(2))) // Cache com tempo de vida de 2 horas
      .build()
  }
}