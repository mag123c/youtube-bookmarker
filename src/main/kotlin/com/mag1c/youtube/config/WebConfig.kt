package com.mag1c.youtube.config

import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.context.annotation.Configuration

@Configuration
class WebConfig(
    private val userIdArgumentResolver: UserIdArgumentResolver
) : WebMvcConfigurer {
    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        resolvers.add(userIdArgumentResolver)
    }
}
