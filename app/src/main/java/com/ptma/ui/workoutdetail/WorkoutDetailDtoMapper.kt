package com.ptma.ui.workoutdetail

import com.ptma.domain.workout.Workout
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

@Mapper
interface WorkoutDetailDtoMapper {

    fun toDto(workout: Workout): WorkoutDetailDto

    companion object {
        @JvmField
        val INSTANCE: WorkoutDetailDtoMapper = Mappers.getMapper(WorkoutDetailDtoMapper::class.java)
    }
}