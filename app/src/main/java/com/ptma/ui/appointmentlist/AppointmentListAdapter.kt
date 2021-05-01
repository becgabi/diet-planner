package com.ptma.ui.appointmentlist

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ptma.R
import com.ptma.databinding.ItemAppointmentListBinding
import com.ptma.domain.appointment.AppointmentStatus
import com.ptma.ui.util.DateUtil.getDateFormat
import com.ptma.ui.util.DateUtil.getTimeFormat
import java.util.*

class AppointmentListAdapter(
    val context: Context
) : ListAdapter<AppointmentListDto, AppointmentListAdapter.ViewHolder>(AppointmentListCallback) {

    var listener: Listener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemAppointmentListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: ItemAppointmentListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private var appointmentListDto: AppointmentListDto? = null

        init {
            binding.root.setOnClickListener {
                appointmentListDto?.let { listener?.onStockChangeClicked(it) }
            }
        }

        fun bind(item: AppointmentListDto) {
            appointmentListDto = item

            binding.tvLocationName.text = item.locationName
            binding.tvTrainerName.text = item.trainerName
            binding.tvStatus.text = item.status.toString().capitalize(Locale.ROOT)
            setStatusColor(item.status)

            binding.tvDate.text = getDateFormat(item.start)
            @SuppressLint("SetTextI18n")
            binding.tvTimeBetween.text = "${getTimeFormat(item.start)} - ${getTimeFormat(item.end)}"
        }

        private fun setStatusColor(status: AppointmentStatus) {
            if (status == AppointmentStatus.ACCEPTED) {
                binding.tvStatus.setTextColor(context.getColor(R.color.green))
            } else if (status == AppointmentStatus.CANCELED) {
                binding.tvStatus.setTextColor(context.getColor(R.color.red))
            }
        }
    }

    fun interface Listener {
        fun onStockChangeClicked(stockChange: AppointmentListDto)
    }

}