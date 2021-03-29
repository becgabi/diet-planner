package com.ptma.ui.workoutlist

import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import javax.inject.Inject

class WorkoutListViewModel @Inject constructor(
    private val loginPresenter: WorkoutListPresenter
) : RainbowCakeViewModel<WorkoutListViewState>(Loading) {

    fun load() = execute {
        viewState = WorkoutListReady(loginPresenter.getData())
    }

}
