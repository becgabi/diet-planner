package com.ptma.ui.workoutdetail

import androidx.recyclerview.widget.DiffUtil

object ExerciseDetailCallback : DiffUtil.ItemCallback<ExerciseDetailDto>() {
    override fun areItemsTheSame(oldItem: ExerciseDetailDto, newItem: ExerciseDetailDto): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ExerciseDetailDto, newItem: ExerciseDetailDto): Boolean {
        return oldItem == newItem
    }
}