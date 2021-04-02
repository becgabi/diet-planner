package com.ptma.ui.workoutdetail

sealed class WorkoutDetailViewState

object Loading : WorkoutDetailViewState()

data class WorkoutDetailReady(val workout: WorkoutDetailDto) : WorkoutDetailViewState()
