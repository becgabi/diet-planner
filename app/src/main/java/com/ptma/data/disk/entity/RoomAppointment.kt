package com.ptma.data.disk.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ptma.model.AppointmentStatus
import java.time.LocalDateTime

@Entity(tableName = "appointment")
class RoomAppointment(
    @PrimaryKey
    val id: Long,
    val start: LocalDateTime,
    val end: LocalDateTime,
    val locationName: String,
    val trainerName: String,
    val status: AppointmentStatus
)