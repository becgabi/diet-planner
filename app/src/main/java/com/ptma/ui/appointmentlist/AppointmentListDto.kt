package com.ptma.ui.appointmentlist

import com.ptma.domain.appointment.AppointmentStatus
import java.time.LocalDateTime

class AppointmentListDto(
    val id: Long,
    val start: LocalDateTime,
    val end: LocalDateTime,
    val locationName: String,
    val trainerName: String,
    val status: AppointmentStatus
)