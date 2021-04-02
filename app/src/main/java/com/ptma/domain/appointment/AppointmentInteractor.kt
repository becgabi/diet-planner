package com.ptma.domain.appointment

import javax.inject.Inject

class AppointmentInteractor @Inject constructor() {

    suspend fun getAppointmentList(): MutableList<Appointment> {
        return mutableListOf()
    }

}
