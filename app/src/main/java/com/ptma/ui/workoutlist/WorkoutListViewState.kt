package com.ptma.ui.workoutlist

sealed class WorkoutListViewState

object Loading : WorkoutListViewState()

data class WorkoutListReady(val data: String = "") : WorkoutListViewState()
