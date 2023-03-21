package com.example.dndapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dndapp.R
import com.example.dndapp.data.EquipmentItemData

class EquipmentItemAdapter (
    private val onSelect: (EquipmentItemData) -> Unit,
    private val onDelete: (EquipmentItemData) -> Unit
)
    : RecyclerView.Adapter<EquipmentItemAdapter.ViewHolder>() {
    var equipItemData: List<EquipmentItemData> = listOf()

    fun updateEquipmentList(list: List<EquipmentItemData>?) {
        equipItemData = list ?: listOf()
        notifyDataSetChanged()
    }

    override fun getItemCount() = this.equipItemData.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_delete, parent, false)
        return ViewHolder(view, onSelect, onDelete)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(this.equipItemData[position])
    }

    class ViewHolder(
        itemView: View,
        val onSelect: (EquipmentItemData) -> Unit,
        val onDelete: (EquipmentItemData) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        private val equipItemName: TextView = itemView.findViewById(R.id.tv_name)
        private val equipDeleteButton: Button = itemView.findViewById(R.id.b_remove_sheet)

        private lateinit var currentEquipItem: EquipmentItemData

        init {
            itemView.setOnClickListener {
                currentEquipItem.let(onSelect)
            }
            equipDeleteButton.setOnClickListener {
                currentEquipItem.let(onDelete)
            }
        }

        fun bind(equipItem: EquipmentItemData) {
            currentEquipItem = equipItem
            equipItemName.text = equipItem.name
        }
    }
}