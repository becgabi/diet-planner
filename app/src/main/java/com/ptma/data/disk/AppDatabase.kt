package com.ptma.data.disk

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ptma.data.disk.dao.AppointmentDao
import com.ptma.data.disk.dao.WorkoutDao
import com.ptma.data.disk.entity.RoomAppointment
import com.ptma.data.disk.entity.RoomWorkout

@Database(
    entities = [RoomAppointment::class, RoomWorkout::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun appointmentDao(): AppointmentDao

    abstract fun workoutDao(): WorkoutDao
}