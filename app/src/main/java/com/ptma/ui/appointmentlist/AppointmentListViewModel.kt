package com.ptma.ui.appointmentlist

import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import javax.inject.Inject

class AppointmentListViewModel @Inject constructor(
    private val presenter: AppointmentListPresenter
) : RainbowCakeViewModel<AppointmentListViewState>(Loading) {

    init {
        load()
    }

    fun reload() = execute {
        viewState = Loading
        load()
    }

    private fun load() = execute {
        val appointments = try {
            presenter.getAppointmentList()
        } catch (e: Exception) {
            presenter.getCachedAppointmentList()
        }
        viewState = ListReady(appointments)
    }

}
