package com.ptma.data.network.security

import java.time.LocalDateTime

class JwtToken(
        val expirationTime: LocalDateTime? = null,
        val permissions: List<Permission>? = null
)