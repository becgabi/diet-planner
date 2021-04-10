package com.ptma.data.disk

import androidx.room.TypeConverter
import com.ptma.data.disk.entity.RoomExerciseDetail
import com.ptma.model.AppointmentStatus
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types.newParameterizedType
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Converters {

    private val format = "yyyy-MM-dd HH:mm"
    private val formatter = DateTimeFormatter.ofPattern(format)
    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    private val exerciseDetails =
        newParameterizedType(MutableList::class.java, RoomExerciseDetail::class.java)
    private val jsonAdapter: JsonAdapter<MutableList<RoomExerciseDetail>> =
        moshi.adapter(exerciseDetails)


    @TypeConverter
    fun parseDateTime(value: String?): LocalDateTime? {
        return value?.let { text ->
            LocalDateTime.parse(text, formatter)
        }
    }

    @TypeConverter
    fun formatDateTime(dateTime: LocalDateTime?): String? {
        return dateTime?.format(formatter)
    }

    @TypeConverter
    fun stringToStatus(string: String?): AppointmentStatus? {
        return string?.let { value -> AppointmentStatus.valueOf(value) }
    }

    @TypeConverter
    fun statusToString(status: AppointmentStatus?): String? {
        return status?.toString()
    }

    @TypeConverter
    fun stringToExerciseDetail(jsonString: String?): MutableList<RoomExerciseDetail>? {
        return jsonString?.let { jsonAdapter.fromJson(jsonString) }
    }

    @TypeConverter
    fun exerciseDetailToString(muscleGroups: MutableList<RoomExerciseDetail>?): String? {
        return jsonAdapter.toJson(muscleGroups)
    }
}