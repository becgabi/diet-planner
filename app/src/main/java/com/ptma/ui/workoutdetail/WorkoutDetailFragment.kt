package com.ptma.ui.workoutdetail

import android.os.Bundle
import android.view.View
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import co.zsmb.rainbowcake.dagger.getViewModelFromFactory
import com.ptma.R

class WorkoutDetailFragment :
    RainbowCakeFragment<WorkoutDetailViewState, WorkoutDetailViewModel>() {

    override fun provideViewModel() = getViewModelFromFactory()
    override fun getViewResource() = R.layout.fragment_workout_detail

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO Setup views
    }

    override fun onStart() {
        super.onStart()

        // TODO: set id from safe args
        // viewModel.load(1L)
    }

    override fun render(viewState: WorkoutDetailViewState) {
        // TODO Render state
    }

}
