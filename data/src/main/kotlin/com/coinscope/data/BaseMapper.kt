package com.coinscope.data

interface BaseMapper<RESPONSE, DOMAIN> {

    fun mapFromDomain(domain: DOMAIN): RESPONSE

    fun mapToDomain(response: RESPONSE): DOMAIN
}