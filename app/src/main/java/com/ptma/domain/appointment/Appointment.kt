package com.ptma.domain.appointment

import java.time.LocalDateTime

class Appointment(
    val id: Long,
    val start: LocalDateTime,
    val end: LocalDateTime,
    val locationName: String,
    val trainerName: String,
    val status: AppointmentStatus
)

enum class AppointmentStatus { REQUESTED, ACCEPTED, CANCELED }