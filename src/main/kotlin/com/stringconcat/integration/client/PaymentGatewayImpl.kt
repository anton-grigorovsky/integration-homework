package com.stringconcat.integration.client

import com.stringconcat.integration.client.dto.GatewayPayment
import com.stringconcat.integration.client.dto.Payment
import com.stringconcat.integration.client.exceptions.ApiException
import com.stringconcat.integration.client.exceptions.ConnectionException
import com.stringconcat.integration.client.exceptions.UnauthorizedException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.retry.annotation.Backoff
import org.springframework.retry.annotation.CircuitBreaker
import org.springframework.retry.annotation.Retryable
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import java.util.function.Supplier

@Component
class PaymentGatewayImpl(val restTemplate: RestTemplate) : PaymentGateway {

    private val URL = "/store"

    @CircuitBreaker(include = [ConnectionException::class])
    override fun pay(payment: Payment): String {
        return retryablePay(payment)
    }

    @Retryable(retryFor = [ConnectionException::class], maxAttemptsExpression = "\${retry.maxAttempts}",
        backoff = Backoff(maxDelayExpression = "\${retry.maxDelay}"))
    private fun retryablePay(payment: Payment): String {
        val request = GatewayPayment(payment);
        LOGGER.info("Sending request: {}", request)
        val result = wrap(Supplier { restTemplate.postForEntity(URL, request, String::class.java) })
        LOGGER.info("Got response: {}", result)
        return validate(result)
    }

    private fun wrap(supplier: Supplier<ResponseEntity<String>>): ResponseEntity<String> {
        try {
            return supplier.get();
        } catch (e: Exception) {
            LOGGER.error("Connection error: ", e)
            throw ConnectionException("Connection failed")
        }
    }

    private fun validate(result: ResponseEntity<String>): String {
        if (result.statusCode == HttpStatus.UNAUTHORIZED
            || result.statusCode == HttpStatus.FORBIDDEN
        )
            throw UnauthorizedException("Unauthorized request: ${result.statusCode.value()}")
        else if (result.statusCode.value() >= 500)
            throw ConnectionException("Connection error: ${result.statusCode.value()}")
        else if (result.statusCode.value() >= 400)
            throw ApiException("Payment server exception: ${result.statusCode.value()}")
        return result.body!!;
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(PaymentGatewayImpl::class.java)
    }
}