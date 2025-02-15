package com.mag1c.youtube

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class Controller {
    @GetMapping
    fun health(): ResponseEntity<String> {
        return ResponseEntity.ok("UP");
    }
}