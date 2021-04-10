package com.ptma.domain.appointment

import com.ptma.domain.util.DateTimeMapper
import com.ptma.model.AppointmentMinDto
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

@Mapper(uses = [DateTimeMapper::class])
interface AppointmentMapper {

    fun fromDto(appointments: List<AppointmentMinDto>?): List<Appointment>

    companion object {
        @JvmField
        val INSTANCE: AppointmentMapper = Mappers.getMapper(AppointmentMapper::class.java)
    }
}