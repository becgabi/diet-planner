package com.ptma.data.disk.datasource

import com.ptma.data.disk.entity.RoomWorkout
import com.ptma.domain.workout.Workout
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings
import org.mapstruct.factory.Mappers

@Mapper
interface WorkoutMapper {

    fun fromRoomEntities(workouts: List<RoomWorkout>): List<Workout>

    @Mappings(
        Mapping(target = "sumDuration", ignore = true),
        Mapping(target = "muscleGroups", ignore = true)
    )
    fun fromRoomEntity(workout: RoomWorkout): Workout

    fun toRoomEntities(workouts: List<Workout>): List<RoomWorkout>

    fun toRoomEntity(workout: Workout): RoomWorkout

    companion object {
        @JvmField
        val INSTANCE: WorkoutMapper = Mappers.getMapper(WorkoutMapper::class.java)
    }
}