package com.coderipper.hsma.usecases.reservations.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.coderipper.hsma.R
import com.coderipper.hsma.databinding.ReservationsItemBinding
import com.coderipper.hsma.models.ReservationEntity

class ReservationsAdapter(private val reservationEntities: List<ReservationEntity>): RecyclerView.Adapter<ReservationsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.reservations_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(reservationEntities[position])
    }

    override fun getItemCount() = reservationEntities.size

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val binding = ReservationsItemBinding.bind(view)

        fun bind(reservationEntity: ReservationEntity) {
            binding.run {

            }
        }
    }
}