package com.stringconcat.integration.client

import com.stringconcat.integration.client.dto.Payment

interface PaymentGateway {

    fun pay(payment: Payment): String
}