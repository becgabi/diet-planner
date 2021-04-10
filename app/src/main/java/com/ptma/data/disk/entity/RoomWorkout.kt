package com.ptma.data.disk.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ptma.model.MuscleGroup

@Entity(tableName = "workout")
class RoomWorkout(
    @PrimaryKey val id: Long,
    val name: String,
    val exercises: MutableList<RoomExerciseDetail>
)

class RoomExerciseDetail(
    val id: Long,
    val sets: Int,
    val reps: Int,
    val duration: Int,
    val exercise: RoomExercise
)

class RoomExercise(
    val id: Long,
    val name: String,
    val description: String,
    val muscleGroups: MutableList<MuscleGroup>
)