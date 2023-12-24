package com.mohamedfathidev.accuweather.data.remote.model

enum class ServerErrorCode(val description: String) {

    DEFAULT("Unknown Error");

    companion object {
        fun parse(errorCode: String) =
            values().find { it.name == errorCode.trim().uppercase() } ?: DEFAULT
    }
}