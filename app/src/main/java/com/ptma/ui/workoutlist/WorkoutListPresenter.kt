package com.ptma.ui.workoutlist

import co.zsmb.rainbowcake.withIOContext
import com.ptma.domain.workout.WorkoutInteractor
import javax.inject.Inject

class WorkoutListPresenter @Inject constructor(
    private val workoutInteractor: WorkoutInteractor
) {

    suspend fun getCachedWorkoutList(): List<WorkoutListDto> = withIOContext {
        workoutInteractor.getCachedWorkoutList().let(WorkoutListDtoMapper.INSTANCE::toDto)
    }

    suspend fun getWorkoutList(): List<WorkoutListDto> = withIOContext {
        workoutInteractor.getWorkoutList().let(WorkoutListDtoMapper.INSTANCE::toDto)
    }
}