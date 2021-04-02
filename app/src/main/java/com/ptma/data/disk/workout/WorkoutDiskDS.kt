package com.ptma.data.disk.workout

import com.ptma.domain.workout.Workout
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WorkoutDiskDS @Inject constructor() {

    suspend fun getWorkoutList(): List<Workout> {
        TODO("Call DB")
    }

    suspend fun getWorkout(id: Long): Workout {
        TODO("Call DB")
    }

}