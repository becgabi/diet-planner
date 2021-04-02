package com.ptma.domain.workout

class Workout(
    val id: Long,
    val name: String,
    val exercises: MutableList<ExerciseDetail>
) {

    val sumDuration: Int = exercises.asSequence()
        .map { it.duration }
        .sum()

    val muscleGroups: List<MuscleGroup> = exercises.asSequence()
        .flatMap { it.exercise.muscleGroups }
        .distinct()
        .toList()

}