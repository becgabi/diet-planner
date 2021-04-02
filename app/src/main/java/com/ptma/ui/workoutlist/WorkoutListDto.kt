package com.ptma.ui.workoutlist

import com.ptma.domain.workout.MuscleGroup

class WorkoutListDto(
    val id: Long,
    val name: String,
    val sumDuration: Int,
    val muscleGroups: List<MuscleGroup>
)