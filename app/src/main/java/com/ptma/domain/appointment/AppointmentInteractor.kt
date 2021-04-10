package com.ptma.domain.appointment

import com.ptma.data.disk.appointment.AppointmentDiskDS
import com.ptma.data.network.appointment.AppointmentNetworkDS
import javax.inject.Inject

class AppointmentInteractor @Inject constructor(
    private val appointmentNetworkDS: AppointmentNetworkDS,
    private val appointmentDiskDS: AppointmentDiskDS
) {

    suspend fun getAppointmentList(): List<Appointment> {
        // TODO: cache
        val appointments = appointmentNetworkDS.getAppointmentList()

        return AppointmentMapper.INSTANCE.fromDto(appointments)
    }

}