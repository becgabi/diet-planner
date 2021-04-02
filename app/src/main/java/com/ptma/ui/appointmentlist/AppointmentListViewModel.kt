package com.ptma.ui.appointmentlist

import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import javax.inject.Inject

class AppointmentListViewModel @Inject constructor(
    private val presenter: AppointmentListPresenter
) : RainbowCakeViewModel<AppointmentListViewState>(Loading) {

    fun load() = execute {
        viewState = AppointmentListReady(presenter.getAppointmentList())
    }

}
