package com.ptma.ui.workoutdetail

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ptma.R
import com.ptma.databinding.ItemExerciseListBinding

class ExerciseDetailAdapter(
    val context: Context
) : ListAdapter<ExerciseDetailDto, ExerciseDetailAdapter.ViewHolder>(ExerciseDetailCallback) {

    var listener: Listener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemExerciseListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: ItemExerciseListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private var workoutListDto: ExerciseDetailDto? = null

        init {
            binding.root.setOnClickListener {
                workoutListDto?.let { listener?.onStockChangeClicked(it) }
            }
        }

        fun bind(item: ExerciseDetailDto) {
            workoutListDto = item

            with(binding) {
                tvTitle.text = getTitle(item)
                tvDescription.text = item.exercise.description
                tvDuration.text = context.getString(R.string.duration, item.duration)
            }
        }

        private fun getTitle(item: ExerciseDetailDto) =
            if (item.reps > 0 && item.sets > 0) context.getString(
                R.string.title,
                item.exercise.name,
                item.reps,
                item.sets
            ) else item.exercise.name
    }

    fun interface Listener {
        fun onStockChangeClicked(stockChange: ExerciseDetailDto)
    }

}