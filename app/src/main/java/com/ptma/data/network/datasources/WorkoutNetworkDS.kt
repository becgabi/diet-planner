package com.ptma.data.network.datasources

import com.ptma.api.WorkoutApi
import com.ptma.domain.workout.Workout
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WorkoutNetworkDS @Inject constructor(
    private val workoutApi: WorkoutApi
) {

    suspend fun getWorkoutList(): List<Workout> {
        val workouts = workoutApi.findAll().body() ?: emptyList()
        return workouts.let(WorkoutMapper.INSTANCE::fromDto)
    }

    suspend fun getWorkout(id: Long): Workout? {
        return workoutApi.getOne(id).body()?.let(WorkoutMapper.INSTANCE::fromDto)
    }

}