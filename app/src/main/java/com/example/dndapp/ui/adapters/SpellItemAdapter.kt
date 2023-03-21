package com.example.dndapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dndapp.R
import com.example.dndapp.data.item.SpellItemData

class SpellItemAdapter (
    private val onSelect: (SpellItemData) -> Unit,
    private val onDelete: (SpellItemData) -> Unit
) : RecyclerView.Adapter<SpellItemAdapter.ViewHolder>() {
    var spellData: List<SpellItemData> = listOf()

    fun updateSpellList(list: List<SpellItemData>?) {
        spellData = list ?: listOf()
        notifyDataSetChanged()
    }

    override fun getItemCount() = this.spellData.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_delete, parent, false)
        return ViewHolder(view, onSelect, onDelete)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(this.spellData[position])
    }

    class ViewHolder(
        itemView: View,
        val onSelect: (SpellItemData) -> Unit,
        val onDelete: (SpellItemData) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        private val spellName: TextView = itemView.findViewById(R.id.tv_name)
        private val equipDeleteButton: Button = itemView.findViewById(R.id.b_remove_sheet)

        private lateinit var currentSpell: SpellItemData

        init {
            itemView.setOnClickListener {
                currentSpell.let(onSelect)
            }
            equipDeleteButton.setOnClickListener {
                currentSpell.let(onDelete)
            }
        }

        fun bind(spell: SpellItemData) {
            currentSpell = spell
            spellName.text = spell.name
        }
    }
}