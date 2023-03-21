package com.example.dndapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dndapp.R
import com.example.dndapp.data.EquipmentItemData
import com.example.dndapp.data.SheetItemData

class SheetItemAdapter (
    private val onSelect: (SheetItemData) -> Unit,
    private val onDelete: (SheetItemData) -> Unit
) : RecyclerView.Adapter<SheetItemAdapter.ViewHolder>() {
    var sheetData: List<SheetItemData> = listOf()

    fun updateSheetList(list: List<SheetItemData>?) {
        sheetData = list ?: listOf()
        notifyDataSetChanged()
    }

    override fun getItemCount() = this.sheetData.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_delete, parent, false)
        return ViewHolder(view, onSelect, onDelete)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(this.sheetData[position])
    }

    class ViewHolder(
        itemView: View,
        val onSelect: (SheetItemData) -> Unit,
        val onDelete: (SheetItemData) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        private val sheetName: TextView = itemView.findViewById(R.id.tv_name)
        private val equipDeleteButton: Button = itemView.findViewById(R.id.b_remove_sheet)

        private lateinit var currentSheet: SheetItemData

        init {
            itemView.setOnClickListener {
                currentSheet.let(onSelect)
            }
            equipDeleteButton.setOnClickListener {
                currentSheet.let(onDelete)
            }
        }

        fun bind(sheet: SheetItemData) {
            currentSheet = sheet
            sheetName.text = sheet.name
        }
    }
}

