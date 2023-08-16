package com.stringconcat.integration.client.exceptions

class ConnectionException(override val message: String? = null) :
    RuntimeException(message)