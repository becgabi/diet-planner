package com.ptma.domain.workout

import com.ptma.data.network.workout.WorkoutDataSource
import javax.inject.Inject

class WorkoutInteractor @Inject constructor(
    private val workoutDataSource: WorkoutDataSource
) {

    suspend fun getWorkoutList(): List<Workout> {
        return workoutDataSource.getWorkoutList()
    }

    suspend fun getWorkout(id: Long): Workout {
        return workoutDataSource.getWorkout(id)
    }

}
