package com.ptma.data.disk.datasource

import com.ptma.data.disk.entity.RoomAppointment
import com.ptma.domain.appointment.Appointment
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

@Mapper
interface AppointmentMapper {

    fun fromRoomEntities(appointments: List<RoomAppointment>): List<Appointment>

    fun toRoomEntities(appointments: List<Appointment>): List<RoomAppointment>

    companion object {
        @JvmField
        val INSTANCE: AppointmentMapper = Mappers.getMapper(AppointmentMapper::class.java)
    }
}