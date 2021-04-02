package com.ptma.ui.workoutdetail

import co.zsmb.rainbowcake.withIOContext
import com.ptma.domain.workout.WorkoutInteractor
import javax.inject.Inject

class WorkoutDetailPresenter @Inject constructor(
    private val workoutInteractor: WorkoutInteractor
) {

    suspend fun getWorkoutDetail(id: Long): WorkoutDetailDto = withIOContext {
        WorkoutDetailDtoMapper.INSTANCE.toDto(
            workoutInteractor.getWorkout(id)
        )
    }

}