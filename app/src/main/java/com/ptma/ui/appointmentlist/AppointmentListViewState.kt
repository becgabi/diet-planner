package com.ptma.ui.appointmentlist

sealed class AppointmentListViewState

object Loading : AppointmentListViewState()

data class AppointmentListReady(val data: String = "") : AppointmentListViewState()
