package com.ptma.ui.appointmentlist

import co.zsmb.rainbowcake.withIOContext
import com.ptma.domain.appointment.AppointmentInteractor
import javax.inject.Inject

class AppointmentListPresenter @Inject constructor(
    private val appointmentInteractor: AppointmentInteractor
) {

    suspend fun getCachedAppointmentList(): List<AppointmentListDto> = withIOContext {
        appointmentInteractor.getCachedAppointmentList()
            .let(AppointmentListDtoMapper.INSTANCE::toDto)

    }

    suspend fun getAppointmentList(): List<AppointmentListDto> = withIOContext {
        appointmentInteractor.getAppointmentList().let(AppointmentListDtoMapper.INSTANCE::toDto)
    }

}