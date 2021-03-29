package com.ptma.ui.appointmentlist

import co.zsmb.rainbowcake.withIOContext
import javax.inject.Inject

class AppointmentListPresenter @Inject constructor() {

    suspend fun getData(): String = withIOContext {
        ""
    }

}
