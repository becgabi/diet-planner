package com.ptma.ui.appointmentlist

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import co.zsmb.rainbowcake.dagger.getViewModelFromFactory
import com.ptma.R
import com.ptma.databinding.FragmentAppointmentListBinding
import com.ptma.ui.util.PTMAFragment
import com.ptma.ui.util.safeContainsIgnoreCase

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

        setHasOptionsMenu(true)

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        requireActivity().menuInflater.inflate(R.menu.menu_search, menu)
        setSearchView(menu)
    }

    private fun setSearchView(menu: Menu) {
        val searchView: SearchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.submitList(filterQuery(newText))
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
        })
    }

    private fun filterQuery(query: String?): List<AppointmentListDto> {
        viewModel.state.value?.let { state ->
            if (state is ListReady) {
                return state.appointments
                    .filter { appointment ->
                        listOf(
                            appointment.locationName,
                            appointment.trainerName,
                            appointment.status.name
                        )
                            .any { it.safeContainsIgnoreCase(query) }
                    }
            }
        }
        return emptyList()
    }

}
