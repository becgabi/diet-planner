package com.ptma.data.disk.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ptma.data.disk.entity.RoomAppointment

@Dao
interface AppointmentDao {

    @Query("SELECT * FROM appointment")
    suspend fun findAll(): List<RoomAppointment>

    @Insert
    suspend fun insert(appointments: List<RoomAppointment>)

    @Query("DELETE FROM appointment")
    suspend fun deleteAll()

}