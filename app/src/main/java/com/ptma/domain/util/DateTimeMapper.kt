package com.ptma.domain.util

import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers
import java.time.LocalDateTime
import java.time.OffsetDateTime

@Mapper
abstract class DateTimeMapper {

    fun toLocalDateTime(offsetDateTime: OffsetDateTime?): LocalDateTime? {
        return offsetDateTime?.toLocalDateTime()
    }

    companion object {
        @JvmField
        val INSTANCE: DateTimeMapper = Mappers.getMapper(DateTimeMapper::class.java)
    }
}