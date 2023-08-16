package com.stringconcat.integration.controller

import com.stringconcat.integration.client.PaymentGateway
import com.stringconcat.integration.client.dto.Payment
import com.stringconcat.integration.controller.request.PaymentRequest
import com.stringconcat.integration.controller.response.SuccessResponse
import org.slf4j.LoggerFactory
import org.springframework.retry.annotation.Retryable
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.io.IOException

@RestController
@RequestMapping("payments")
class GatewayController(val gateway: PaymentGateway) {

    @PostMapping("/new")
    @Retryable(IOException::class)
    fun execute(@Validated @RequestBody request: PaymentRequest): SuccessResponse {
        LOGGER.info("Incoming request: {}", request)
        return SuccessResponse(gateway.pay(Payment(request.number,
            request.expirationDate,
            request.owner,
            request.cvv,
            request.amount)));
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(GatewayController::class.java)
    }
}