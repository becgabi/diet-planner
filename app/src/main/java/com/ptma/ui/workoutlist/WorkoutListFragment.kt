package com.ptma.ui.workoutlist

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.view.doOnPreDraw
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import co.zsmb.rainbowcake.dagger.getViewModelFromFactory
import com.ptma.R
import com.ptma.databinding.FragmentWorkoutListBinding
import com.ptma.ui.util.PTMAFragment
import com.ptma.ui.util.safeContainsIgnoreCase

class WorkoutListFragment :
    PTMAFragment<WorkoutListViewState, WorkoutListViewModel, FragmentWorkoutListBinding>() {

    private lateinit var adapter: WorkoutListAdapter

    override fun provideViewModel() = getViewModelFromFactory()

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentWorkoutListBinding {
        return FragmentWorkoutListBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)

        setHasOptionsMenu(true)

        adapter = WorkoutListAdapter(requireContext())
        binding.listItems.adapter = adapter
        adapter.listener = WorkoutListAdapter.Listener { workoutListDto, position ->
            navigateToDetailView(workoutListDto.id, position)
        }

        binding.refreshLayout.setOnRefreshListener { viewModel.reload() }

        postponeEnterTransition()
        binding.listItems.doOnPreDraw { startPostponedEnterTransition() }
    }

    override fun render(viewState: WorkoutListViewState) {
        when (viewState) {
            Loading -> showProgressBar()
            is ListReady -> {
                adapter.submitList(viewState.workouts)
                hideProgressBar()
                binding.refreshLayout.isRefreshing = false
                noElement.visibility =
                    if (viewState.workouts.isEmpty()) View.VISIBLE else View.GONE
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

    private fun filterQuery(query: String?): List<WorkoutListDto> {
        viewModel.state.value?.let { state ->
            if (state is ListReady) {
                return state.workouts.filter { workout -> workout.name.safeContainsIgnoreCase(query) }
            }
        }
        return emptyList()
    }

    private fun navigateToDetailView(workoutId: Long, position: Int) {
        val itemBinding =
            (binding.listItems.findViewHolderForLayoutPosition(position) as WorkoutListAdapter.ViewHolder).binding

        val extras = FragmentNavigatorExtras(
            itemBinding.tvWorkoutName to getString(R.string.workout_name_trans, workoutId),
            itemBinding.cgMuscleGroups to getString(R.string.muscle_groups_trans, workoutId),
            itemBinding.tvDuration to getString(R.string.duration_trans, workoutId)
        )

        findNavController().navigate(
            WorkoutListFragmentDirections.actionWorkoutListToDetail(workoutId),
            extras
        )
    }

}