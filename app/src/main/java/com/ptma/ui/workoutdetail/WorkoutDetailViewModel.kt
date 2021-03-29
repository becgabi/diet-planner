package com.ptma.ui.workoutdetail

import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import javax.inject.Inject

class WorkoutDetailViewModel @Inject constructor(
    private val loginPresenter: WorkoutDetailPresenter
) : RainbowCakeViewModel<WorkoutDetailViewState>(Loading) {

    fun load() = execute {
        viewState = WorkoutDetailReady(loginPresenter.getData())
    }

}
