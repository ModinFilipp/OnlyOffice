package com.example.onlyofficetest.error

interface ErrorMapper {
    fun mapErrorCode(code: Int): String
}