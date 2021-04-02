package com.ptma.data.network.appointment

import com.ptma.domain.appointment.Appointment
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppointmentNetworkDS @Inject constructor() {

    suspend fun getAppointmentList(): List<Appointment> {
        TODO("Call API")
    }

}