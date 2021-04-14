package com.ptma.data.disk.datasource

import com.ptma.data.disk.dao.WorkoutDao
import com.ptma.domain.workout.Workout
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WorkoutDiskDS @Inject constructor(
    private val workoutDao: WorkoutDao
) {

    suspend fun findAll(): List<Workout> {
        return workoutDao.findAll().let(WorkoutMapper.INSTANCE::fromRoomEntities)
    }

    suspend fun update(workout: Workout) {
        workout
            .let(WorkoutMapper.INSTANCE::toRoomEntity)
            .let { workoutDao.upsert(it) }
    }

    suspend fun findById(id: Long): Workout? {
        return workoutDao.findById(id)?.let(WorkoutMapper.INSTANCE::fromRoomEntity)
    }

    suspend fun update(workouts: List<Workout>) {
        deleteAll()
        insert(workouts)
    }

    private suspend fun insert(workouts: List<Workout>) {
        workouts
            .let(WorkoutMapper.INSTANCE::toRoomEntities)
            .let { roomAppointments -> workoutDao.insert(roomAppointments) }
    }

    private suspend fun deleteAll() {
        workoutDao.deleteAll()
    }
}