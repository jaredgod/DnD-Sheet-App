package com.example.dndapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dndapp.R
import com.example.dndapp.data.WeaponItemData

class WeaponItemAdapter (private val onClick: (WeaponItemData) -> Unit)
    : RecyclerView.Adapter<WeaponItemAdapter.ViewHolder>() {
    var weaponData: List<WeaponItemData> = listOf()

    fun updateWeaponList(list: List<WeaponItemData>?) {
        weaponData = list ?: listOf()
        notifyDataSetChanged()
    }

    override fun getItemCount() = this.weaponData.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item, parent, false)
        return ViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(this.weaponData[position])
    }

    class ViewHolder(itemView: View, val onClick: (WeaponItemData) -> Unit)
        : RecyclerView.ViewHolder(itemView) {
        private val weaponName: TextView = itemView.findViewById(R.id.tv_name)

        private lateinit var currentWeapon: WeaponItemData

        init {
            itemView.setOnClickListener {
                currentWeapon.let(onClick)
            }
        }

        fun bind(weapon: WeaponItemData) {
            currentWeapon = weapon
            weaponName.text = weapon.name
        }
    }
}