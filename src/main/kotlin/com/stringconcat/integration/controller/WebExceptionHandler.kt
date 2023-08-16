package com.stringconcat.integration.controller

import com.stringconcat.integration.client.exceptions.ApiException
import com.stringconcat.integration.client.exceptions.UnauthorizedException
import com.stringconcat.integration.controller.response.FailResponse
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class WebExceptionHandler {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(WebExceptionHandler::class.java)
    }

    @ExceptionHandler(ApiException::class)
    fun handleApiException(e: ApiException): Any? {
        LOGGER.error("Gateway error: {}", e.message)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body<Any>(FailResponse(e.message))
    }

    @ExceptionHandler(ApiException::class)
    fun handleConnectionExceptions(e: ApiException): Any? {
        LOGGER.error("Connection error: {}", e.message)
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body<Any>(FailResponse(e.message))
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ApiException::class)
    fun handleAuthExceptions(e: UnauthorizedException): Any? {
        LOGGER.error("Authorizing error: {}", e.message)
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body<Any>(FailResponse(e.message))
    }
}