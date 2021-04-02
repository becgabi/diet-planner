package com.ptma.ui.appointmentlist

sealed class AppointmentListViewState

object Loading : AppointmentListViewState()

data class AppointmentListReady(val appointments: List<AppointmentListDto>) :
    AppointmentListViewState()