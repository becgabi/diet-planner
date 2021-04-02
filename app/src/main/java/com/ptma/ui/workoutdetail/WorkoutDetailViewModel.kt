package com.ptma.ui.workoutdetail

import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import javax.inject.Inject

class WorkoutDetailViewModel @Inject constructor(
    private val presenter: WorkoutDetailPresenter
) : RainbowCakeViewModel<WorkoutDetailViewState>(Loading) {

    fun load(id: Long) = execute {
        viewState = WorkoutDetailReady(presenter.getWorkoutDetail(id))
    }

}
