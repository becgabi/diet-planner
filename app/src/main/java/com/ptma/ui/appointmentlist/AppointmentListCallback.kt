package com.ptma.ui.appointmentlist

import androidx.recyclerview.widget.DiffUtil

object AppointmentListCallback : DiffUtil.ItemCallback<AppointmentListDto>() {
    override fun areItemsTheSame(oldItem: AppointmentListDto, newItem: AppointmentListDto): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: AppointmentListDto, newItem: AppointmentListDto): Boolean {
        return oldItem == newItem
    }
}