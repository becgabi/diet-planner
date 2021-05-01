package com.ptma.ui.workoutdetail

sealed class WorkoutDetailViewState

object Loading : WorkoutDetailViewState()

data class DataReady(val workout: WorkoutDetailDto) : WorkoutDetailViewState()
