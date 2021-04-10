package com.ptma.data.network.datasources

import com.ptma.api.AppointmentApi
import com.ptma.domain.appointment.Appointment
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppointmentNetworkDS @Inject constructor(
    private val appointmentApi: AppointmentApi
) {

    suspend fun getAppointmentList(): List<Appointment> {
        val appointments = appointmentApi.findOwn().body() ?: emptyList()
        return appointments.let(AppointmentMapper.INSTANCE::fromDto)
    }
}