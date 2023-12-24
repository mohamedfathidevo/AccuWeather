package com.mohamedfathidev.accuweather.data.remote

class ApiException(
    val code: String? = null,
    message: String? = null,
    localMessage: String? = null
) : RuntimeException(message)