package com.stringconcat.integration.client.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

class GatewayPayment(payment: Payment) {
    @field:JsonProperty
    val data = "card:${payment.number};" +
            "cvv:${payment.cvv};" +
            "expiration_date:${payment.expirationDate};" +
            "amount:${payment.amount}:" +
            "payment_id:${UUID.randomUUID()}"
}
