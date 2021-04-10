package com.ptma.data.disk.datasource

import com.ptma.data.disk.dao.AppointmentDao
import com.ptma.domain.appointment.Appointment
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppointmentDiskDS @Inject constructor(
    private val appointmentDao: AppointmentDao
) {

    suspend fun findAll(): List<Appointment> {
        return appointmentDao.findAll().let(AppointmentMapper.INSTANCE::fromRoomEntities)
    }

    suspend fun update(appointments: List<Appointment>) {
        deleteAll()
        insert(appointments)
    }

    private suspend fun insert(appointments: List<Appointment>) {
        appointments
            .let(AppointmentMapper.INSTANCE::toRoomEntities)
            .let { roomAppointments -> appointmentDao.insert(roomAppointments) }
    }

    private suspend fun deleteAll() {
        appointmentDao.deleteAll()
    }
}