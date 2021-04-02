package com.ptma.ui.appointmentlist

import com.ptma.domain.appointment.Appointment
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

@Mapper
interface AppointmentListDtoMapper {

    fun toDto(appointments: List<Appointment>): List<AppointmentListDto>

    companion object {
        @JvmField
        val INSTANCE: AppointmentListDtoMapper =
            Mappers.getMapper(AppointmentListDtoMapper::class.java)
    }
}