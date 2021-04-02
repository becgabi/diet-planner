package com.ptma.domain.appointment

import com.ptma.data.network.appointment.AppointmentDataSource
import javax.inject.Inject

class AppointmentInteractor @Inject constructor(
    private val appointmentDataSource: AppointmentDataSource
) {

    suspend fun getAppointmentList(): List<Appointment> {
        return appointmentDataSource.getAppointmentList()
    }

}
