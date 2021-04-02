package com.ptma.domain.workout

class Workout(
    val id: Long,
    val name: String,
    val exercises: MutableList<ExerciseDetail>
)