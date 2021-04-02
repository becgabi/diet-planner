package com.ptma.ui.workoutlist

import android.os.Bundle
import android.view.View
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import co.zsmb.rainbowcake.dagger.getViewModelFromFactory
import com.ptma.R

class WorkoutListFragment : RainbowCakeFragment<WorkoutListViewState, WorkoutListViewModel>() {

    override fun provideViewModel() = getViewModelFromFactory()
    override fun getViewResource() = R.layout.fragment_workout_list

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO Setup views
    }

    override fun onStart() {
        super.onStart()

        // viewModel.load()
    }

    override fun render(viewState: WorkoutListViewState) {
        // TODO Render state
    }

}