package com.ptma.ui.workoutlist

sealed class WorkoutListViewState

object Loading : WorkoutListViewState()

data class ListReady(val workouts: List<WorkoutListDto>) : WorkoutListViewState()
