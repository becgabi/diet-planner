package com.ptma.ui.appointmentlist

sealed class AppointmentListViewState

object Loading : AppointmentListViewState()

data class ListReady(val appointments: List<AppointmentListDto>) : AppointmentListViewState()