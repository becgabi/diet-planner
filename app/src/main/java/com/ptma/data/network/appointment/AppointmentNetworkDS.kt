package com.ptma.data.network.appointment

import com.ptma.api.AppointmentApi
import com.ptma.model.AppointmentMinDto
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppointmentNetworkDS @Inject constructor(
    private val appointmentApi: AppointmentApi
) {

    suspend fun getAppointmentList(): List<AppointmentMinDto>? {
        return appointmentApi.findOwn().body()
    }

}