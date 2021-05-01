package com.ptma.ui.workoutlist

import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import javax.inject.Inject

class WorkoutListViewModel @Inject constructor(
    private val presenter: WorkoutListPresenter
) : RainbowCakeViewModel<WorkoutListViewState>(Loading) {

    init {
        load()
    }

    fun reload() = execute {
        viewState = Loading
        load()
    }

    private fun load() = execute {
        val workouts = try {
            presenter.getWorkoutList()
        } catch (e: Exception) {
            presenter.getCachedWorkoutList()
        }
        viewState = ListReady(workouts)
    }

}
