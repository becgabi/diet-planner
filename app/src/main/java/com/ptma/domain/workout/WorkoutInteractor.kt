package com.ptma.domain.workout

import com.ptma.data.disk.workout.WorkoutDiskDS
import com.ptma.data.network.datasources.WorkoutNetworkDS
import javax.inject.Inject

class WorkoutInteractor @Inject constructor(
    private val workoutNetworkDS: WorkoutNetworkDS,
    private val workoutDiskDS: WorkoutDiskDS
) {

    suspend fun getWorkoutList(): List<Workout> {
        // TODO: cache
        val workouts = workoutNetworkDS.getWorkoutList()

        return WorkoutMapper.INSTANCE.fromDto(workouts)
    }

    suspend fun getWorkout(id: Long): Workout? {
        // TODO: cache
        val workout = workoutNetworkDS.getWorkout(id)

        return WorkoutMapper.INSTANCE.fromDto(workout)
    }
}