package com.stringconcat.integration.client.exceptions

class UnauthorizedException(override val message: String? = null) :
    RuntimeException(message)