package com.ptma.ui.workoutdetail

import co.zsmb.rainbowcake.withIOContext
import com.ptma.domain.workout.WorkoutInteractor
import javax.inject.Inject

class WorkoutDetailPresenter @Inject constructor(
    private val workoutInteractor: WorkoutInteractor
) {

    suspend fun getCachedWorkoutDetail(id: Long): WorkoutDetailDto? = withIOContext {
        workoutInteractor.getCachedWorkout(id)?.let(WorkoutDetailDtoMapper.INSTANCE::toDto)
    }

    suspend fun getWorkoutDetail(id: Long): WorkoutDetailDto? = withIOContext {
        workoutInteractor.getWorkout(id)?.let(WorkoutDetailDtoMapper.INSTANCE::toDto)
    }

}