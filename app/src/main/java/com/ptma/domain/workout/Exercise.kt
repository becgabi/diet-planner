package com.ptma.domain.workout

class Exercise(
    val id: Long,
    val name: String,
    val description: String,
    val muscleGroups: MutableList<MuscleGroup>
)

enum class MuscleGroup {
    ABS, BACK, BICEPS, BOOTY, CALVES, CARDIO, CHEST, HAMSTRINGS, SHOULDER, TRICEPS, QUADS
}