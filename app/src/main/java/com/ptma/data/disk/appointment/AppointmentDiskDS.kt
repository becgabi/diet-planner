package com.ptma.data.disk.appointment

import com.ptma.domain.appointment.Appointment
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppointmentDiskDS @Inject constructor() {

    suspend fun getAppointmentList(): List<Appointment> {
        TODO("Call DB")
    }

}