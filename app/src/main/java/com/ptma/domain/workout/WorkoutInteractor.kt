package com.ptma.domain.workout

import com.ptma.data.disk.datasource.WorkoutDiskDS
import com.ptma.data.network.datasources.WorkoutNetworkDS
import javax.inject.Inject

class WorkoutInteractor @Inject constructor(
    private val workoutNetworkDS: WorkoutNetworkDS,
    private val workoutDiskDS: WorkoutDiskDS
) {

    suspend fun getCachedWorkoutList(): List<Workout> {
        return workoutDiskDS.findAll()
    }

    suspend fun getWorkoutList(): List<Workout> {
        val workouts = workoutNetworkDS.getWorkoutList()
        if (workouts.isNotEmpty()) {
            workoutDiskDS.update(workouts)
        }
        return workouts
    }

    suspend fun getCachedWorkout(id: Long): Workout? {
        return workoutDiskDS.findById(id)
    }

    suspend fun getWorkout(id: Long): Workout? {
        val workout = workoutNetworkDS.getWorkout(id)
        if (workout != null) {
            workoutDiskDS.update(workout)
        }
        return workout
    }
}