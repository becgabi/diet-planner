package com.ptma.domain.workout

import com.ptma.data.disk.workout.WorkoutDiskDS
import com.ptma.data.network.workout.WorkoutNetworkDS
import javax.inject.Inject

class WorkoutInteractor @Inject constructor(
    private val workoutNetworkDS: WorkoutNetworkDS,
    private val workoutDiskDS: WorkoutDiskDS
) {

    suspend fun getWorkoutList(): List<Workout> {
        // TODO: cache
        return workoutNetworkDS.getWorkoutList()
    }

    suspend fun getWorkout(id: Long): Workout {
        // TODO: cache
        return workoutNetworkDS.getWorkout(id)
    }

}