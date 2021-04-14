package com.ptma.data.disk.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ptma.data.disk.entity.RoomWorkout

@Dao
interface WorkoutDao {

    @Query("SELECT * FROM workout")
    suspend fun findAll(): List<RoomWorkout>

    @Query("SELECT * FROM workout WHERE id = :id")
    suspend fun findById(id: Long): RoomWorkout?

    @Insert
    suspend fun insert(workouts: List<RoomWorkout>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(workout: RoomWorkout)

    @Query("DELETE FROM workout")
    suspend fun deleteAll()

}