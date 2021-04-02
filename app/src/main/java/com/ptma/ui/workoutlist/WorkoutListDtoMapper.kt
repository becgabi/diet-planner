package com.ptma.ui.workoutlist

import com.ptma.domain.workout.Workout
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

@Mapper
interface WorkoutListDtoMapper {

    fun toDto(workouts: List<Workout>): List<WorkoutListDto>

    companion object {
        @JvmField
        val INSTANCE: WorkoutListDtoMapper = Mappers.getMapper(WorkoutListDtoMapper::class.java)
    }
}