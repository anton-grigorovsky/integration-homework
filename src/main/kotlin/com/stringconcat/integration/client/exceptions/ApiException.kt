package com.stringconcat.integration.client.exceptions

class ApiException(override val message: String? = null) :
    RuntimeException(message)