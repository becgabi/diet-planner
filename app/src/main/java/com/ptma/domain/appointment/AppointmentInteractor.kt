package com.ptma.domain.appointment

import com.ptma.data.disk.datasource.AppointmentDiskDS
import com.ptma.data.network.datasources.AppointmentNetworkDS
import javax.inject.Inject

class AppointmentInteractor @Inject constructor(
    private val appointmentNetworkDS: AppointmentNetworkDS,
    private val appointmentDiskDS: AppointmentDiskDS
) {

    suspend fun getCachedAppointmentList(): List<Appointment> {
        return appointmentDiskDS.findAll()
    }

    suspend fun getAppointmentList(): List<Appointment> {
        val appointments = appointmentNetworkDS.getAppointmentList()
        if (appointments.isNotEmpty()) {
            appointmentDiskDS.update(appointments)
        }
        return appointments
    }
}