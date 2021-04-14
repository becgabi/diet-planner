package com.ptma.ui.workoutdetail

import co.zsmb.rainbowcake.base.OneShotEvent
import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import javax.inject.Inject

class WorkoutDetailViewModel @Inject constructor(
    private val presenter: WorkoutDetailPresenter
) : RainbowCakeViewModel<WorkoutDetailViewState>(Loading) {

    object WorkoutNotFountEvent : OneShotEvent

    fun load(id: Long) = execute {
        val workoutDetail = presenter.getWorkoutDetail(id)
        if (workoutDetail != null) {
            viewState = WorkoutDetailReady(workoutDetail)
        } else {
            postEvent(WorkoutNotFountEvent)
        }
    }

}