package com.ptma.ui.workoutlist

import androidx.recyclerview.widget.DiffUtil

object WorkoutListCallback : DiffUtil.ItemCallback<WorkoutListDto>() {
    override fun areItemsTheSame(oldItem: WorkoutListDto, newItem: WorkoutListDto): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: WorkoutListDto, newItem: WorkoutListDto): Boolean {
        return oldItem == newItem
    }
}