package com.ptma.data.network.datasources

import com.ptma.api.WorkoutApi
import com.ptma.model.WorkoutDto
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WorkoutNetworkDS @Inject constructor(
    private val workoutApi: WorkoutApi
) {

    suspend fun getWorkoutList(): List<WorkoutDto>? {
        return workoutApi.findAll().body()
    }

    suspend fun getWorkout(id: Long): WorkoutDto? {
        return workoutApi.getOne(id).body()
    }

}