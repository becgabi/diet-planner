package com.ptma.domain.workout

import javax.inject.Inject

class WorkoutInteractor @Inject constructor() {

    suspend fun getWorkoutList(): MutableList<Workout> {
        return mutableListOf()
    }

    suspend fun getWorkout(id: Long): Workout? {
        return null
    }

}
