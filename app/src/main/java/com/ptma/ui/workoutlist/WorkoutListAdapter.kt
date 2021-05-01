package com.ptma.ui.workoutlist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.ptma.R
import com.ptma.databinding.ItemWorkoutListBinding

class WorkoutListAdapter(
    val context: Context
) : ListAdapter<WorkoutListDto, WorkoutListAdapter.ViewHolder>(WorkoutListCallback) {

    var listener: Listener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemWorkoutListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        val binding: ItemWorkoutListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private var workoutListDto: WorkoutListDto? = null

        init {
            binding.root.setOnClickListener {
                workoutListDto?.let { listener?.onStockChangeClicked(it, layoutPosition) }
            }
        }

        fun bind(item: WorkoutListDto) {
            workoutListDto = item

            with(binding) {
                tvWorkoutName.text = item.name
                tvDuration.text = context.getString(R.string.duration, item.sumDuration)

                item.muscleGroups.forEach { muscleGroup ->
                    val chip = Chip(context)
                    chip.text = muscleGroup.name
                    cgMuscleGroups.addView(chip)
                }

                tvWorkoutName.transitionName =
                    context.getString(R.string.workout_name_trans, item.id)
                tvDuration.transitionName =
                    context.getString(R.string.duration_trans, item.id)
                cgMuscleGroups.transitionName =
                    context.getString(R.string.muscle_groups_trans, item.id)
            }
        }
    }

    fun interface Listener {
        fun onStockChangeClicked(stockChange: WorkoutListDto, layoutPosition: Int)
    }

}