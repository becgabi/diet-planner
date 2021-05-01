package com.ptma.ui.workoutdetail

import android.os.Bundle
import android.transition.TransitionInflater.from
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.app.ActivityCompat
import androidx.navigation.fragment.navArgs
import co.zsmb.rainbowcake.dagger.getViewModelFromFactory
import com.google.android.material.chip.Chip
import com.ptma.R
import com.ptma.databinding.FragmentWorkoutDetailBinding
import com.ptma.ui.util.PTMAFragment

class WorkoutDetailFragment :
    PTMAFragment<WorkoutDetailViewState, WorkoutDetailViewModel, FragmentWorkoutDetailBinding>() {

    private val args: WorkoutDetailFragmentArgs by navArgs()

    private lateinit var adapter: ExerciseDetailAdapter

    override fun provideViewModel() = getViewModelFromFactory()

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentWorkoutDetailBinding {
        return FragmentWorkoutDetailBinding.inflate(inflater, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = from(context).inflateTransition(android.R.transition.move)
        sharedElementReturnTransition = from(context).inflateTransition(android.R.transition.move)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(false)

        adapter = ExerciseDetailAdapter(requireContext())
        binding.listItems.adapter = adapter

        viewModel.load(args.id)

        with(binding) {
            tvWorkoutName.transitionName = getString(R.string.workout_name_trans, args.id)
            tvDuration.transitionName = getString(R.string.duration_trans, args.id)
            cgMuscleGroups.transitionName = getString(R.string.muscle_groups_trans, args.id)
        }

        postponeEnterTransition()

        requireActivity().onBackPressedDispatcher.addCallback {
            ActivityCompat.finishAfterTransition(requireActivity())
        }
    }

    override fun render(viewState: WorkoutDetailViewState) {
        when (viewState) {
            Loading -> showProgressBar()
            is DataReady -> {
                binding.tvWorkoutName.text = viewState.workout.name
                binding.tvDuration.text =
                    requireContext().getString(R.string.duration, viewState.workout.sumDuration)

                viewState.workout.muscleGroups.forEach { muscleGroup ->
                    val chip = Chip(context)
                    chip.text = muscleGroup.name
                    binding.cgMuscleGroups.addView(chip)
                }

                startPostponedEnterTransition()

                adapter.submitList(viewState.workout.exercises)

                noElement.visibility =
                    if (viewState.workout.exercises.isEmpty()) View.VISIBLE else View.GONE

                hideProgressBar()
            }
        }
    }

}
