package com.mag1c.youtube.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
class WebConfig(
    private val userIdArgumentResolver: UserIdArgumentResolver
) : WebMvcConfigurer {
    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        resolvers.add(userIdArgumentResolver)
    }

    @Bean
    fun corsFilter(): CorsFilter {
        val source = UrlBasedCorsConfigurationSource()
        val config = CorsConfiguration()

        config.allowedOrigins = mutableListOf("http://localhost:5173", "http://127.0.0.1:5173")
        config.allowedMethods = mutableListOf("GET", "POST", "PUT", "DELETE", "OPTIONS")
        config.allowedHeaders = mutableListOf("*")
        config.allowCredentials = true
        source.registerCorsConfiguration("/**", config)

        return CorsFilter(source)
    }
}
