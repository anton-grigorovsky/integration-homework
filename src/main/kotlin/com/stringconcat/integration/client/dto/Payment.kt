package com.stringconcat.integration.client.dto

import java.math.BigDecimal
import java.time.OffsetDateTime

data class Payment(
    val number: String,
    val expirationDate: OffsetDateTime,
    val owner: String,
    val cvv: Int,
    val amount: BigDecimal
)