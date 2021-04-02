package com.ptma.data.network.workout

import com.ptma.domain.workout.Workout
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WorkoutDataSource @Inject constructor() {

    suspend fun getWorkoutList(): List<Workout> {
        TODO("Call API")
    }

    suspend fun getWorkout(id: Long): Workout {
        TODO("Call API")
    }

}