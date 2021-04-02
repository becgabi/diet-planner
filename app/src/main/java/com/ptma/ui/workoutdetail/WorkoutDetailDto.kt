package com.ptma.ui.workoutdetail

import com.ptma.domain.workout.ExerciseDetail
import com.ptma.domain.workout.MuscleGroup

class WorkoutDetailDto(
    val id: Long,
    val name: String,
    val sumDuration: Int,
    val muscleGroups: List<MuscleGroup>,
    val exercises: List<ExerciseDetail>
)