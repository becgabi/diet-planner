package com.ptma.domain.appointment

import java.time.LocalDateTime

class Appointment(
    var id: Long,
    var start: LocalDateTime,
    var end: LocalDateTime,
    var locationName: String,
    var trainerName: String,
    var status: AppointmentStatus
)

enum class AppointmentStatus { REQUESTED, ACCEPTED, CANCELED }