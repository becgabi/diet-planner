package com.ptma.ui.workoutdetail

data class ExerciseDetailDto(
    val id: Long,
    val sets: Int,
    val reps: Int,
    val duration: Int,
    val exercise: ExerciseDto
)