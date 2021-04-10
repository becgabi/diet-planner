package com.ptma.data.network.security

import com.ptma.BuildConfig
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import timber.log.Timber
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class JWTParser @Inject constructor() {

    fun parseToken(token: String): JwtToken {
        return try {
            val claims = getClaims(token)
            JwtToken(
                expirationTime = getExpirationTime(claims),
                permissions = getPermissions(claims),
            )
        } catch (e: ExpiredJwtException) {
            Timber.e(e, "Token has been expired.")
            JwtToken()
        }
    }

    private fun getClaims(token: String): Claims {
        val key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(BuildConfig.PTMA_SECRET_KEY))
        return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .body
    }

    @Suppress("unchecked_cast")
    private fun getPermissions(claims: Claims): List<Permission> {
        return Optional.ofNullable(claims["permissions"] as ArrayList<String>?)
            .orElse(ArrayList())
            .map { name: String -> Permission.valueOf(name) }
            .toList()
    }

    private fun getExpirationTime(claims: Claims): LocalDateTime? {
        return Optional.ofNullable(claims.expiration)
            .map { obj: Date -> obj.toInstant() }
            .map { instant: Instant -> instant.atZone(ZoneId.systemDefault()) }
            .map { obj: ZonedDateTime -> obj.toLocalDateTime() }
            .orElse(null)
    }
}