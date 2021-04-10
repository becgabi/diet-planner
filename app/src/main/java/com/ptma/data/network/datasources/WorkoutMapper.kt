package com.ptma.data.network.datasources

import com.ptma.domain.util.DateTimeMapper
import com.ptma.domain.workout.Workout
import com.ptma.model.WorkoutDto
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings
import org.mapstruct.factory.Mappers

@Mapper(uses = [DateTimeMapper::class])
interface WorkoutMapper {

    fun fromDto(workouts: List<WorkoutDto>?): List<Workout>

    @Mappings(
        Mapping(target = "sumDuration", ignore = true),
        Mapping(target = "muscleGroups", ignore = true)
    )
    fun fromDto(workout: WorkoutDto?): Workout?

    companion object {
        @JvmField
        val INSTANCE: WorkoutMapper = Mappers.getMapper(WorkoutMapper::class.java)
    }
}