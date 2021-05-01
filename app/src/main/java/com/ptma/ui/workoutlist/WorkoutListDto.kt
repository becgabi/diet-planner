package com.ptma.ui.workoutlist

import com.ptma.domain.workout.MuscleGroup

data class WorkoutListDto(
    val id: Long,
    val name: String,
    val sumDuration: Int,
    val muscleGroups: List<MuscleGroup>
)