package com.ptma.ui.appointmentlist

import co.zsmb.rainbowcake.withIOContext
import com.ptma.domain.appointment.AppointmentInteractor
import javax.inject.Inject

class AppointmentListPresenter @Inject constructor(
    private val appointmentInteractor: AppointmentInteractor
) {

    suspend fun getAppointmentList(): List<AppointmentListDto> = withIOContext {
        AppointmentListDtoMapper.INSTANCE.toDto(
            appointmentInteractor.getAppointmentList()
        )
    }

}