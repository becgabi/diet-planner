package com.ptma.ui.appointmentlist

import android.os.Bundle
import android.view.View
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import co.zsmb.rainbowcake.dagger.getViewModelFromFactory
import com.ptma.R

class AppointmentListFragment : RainbowCakeFragment<AppointmentListViewState, AppointmentListViewModel>() {

    override fun provideViewModel() = getViewModelFromFactory()
    override fun getViewResource() = R.layout.fragment_appointment_list

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO Setup views
    }

    override fun onStart() {
        super.onStart()

        // viewModel.load()
    }

    override fun render(viewState: AppointmentListViewState) {
        // TODO Render state
    }

}
