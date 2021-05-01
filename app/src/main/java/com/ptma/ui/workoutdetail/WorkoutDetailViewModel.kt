package com.ptma.ui.workoutdetail

import co.zsmb.rainbowcake.base.OneShotEvent
import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import javax.inject.Inject

class WorkoutDetailViewModel @Inject constructor(
    private val presenter: WorkoutDetailPresenter
) : RainbowCakeViewModel<WorkoutDetailViewState>(Loading) {

    object WorkoutNotFountEvent : OneShotEvent

    fun load(id: Long) = execute {
        val workoutDetail = try {
            presenter.getWorkoutDetail(id)
        } catch (e: Exception) {
            presenter.getCachedWorkoutDetail(id)
        }

        if (workoutDetail != null) {
            viewState = DataReady(workoutDetail)
        } else {
            postEvent(WorkoutNotFountEvent)
        }
    }

}