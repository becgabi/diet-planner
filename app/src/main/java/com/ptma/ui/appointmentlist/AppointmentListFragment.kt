package com.ptma.ui.appointmentlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.zsmb.rainbowcake.dagger.getViewModelFromFactory
import com.ptma.databinding.FragmentAppointmentListBinding
import com.ptma.ui.util.PTMAFragment

class AppointmentListFragment :
    PTMAFragment<AppointmentListViewState, AppointmentListViewModel, FragmentAppointmentListBinding>() {

    private lateinit var adapter: AppointmentListAdapter

    override fun provideViewModel() = getViewModelFromFactory()

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAppointmentListBinding {
        return FragmentAppointmentListBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = AppointmentListAdapter(requireContext())
        binding.listItems.adapter = adapter

        binding.refreshLayout.setOnRefreshListener { viewModel.reload() }
    }

    override fun render(viewState: AppointmentListViewState) {
        when (viewState) {
            Loading -> showProgressBar()
            is ListReady -> {
                adapter.submitList(viewState.appointments)
                hideProgressBar()
                binding.refreshLayout.isRefreshing = false
                noElement.visibility =
                    if (viewState.appointments.isEmpty()) View.VISIBLE else View.GONE
            }
        }
    }
}
