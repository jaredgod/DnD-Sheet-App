package com.example.dndapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dndapp.R
import com.example.dndapp.data.SpellClassItemData

class SpellClassItemAdapter (private val onClick: (SpellClassItemData) -> Unit)
    : RecyclerView.Adapter<SpellClassItemAdapter.ViewHolder>() {
    var spellClassData: List<SpellClassItemData> = listOf()

    fun updateSpellClassList(list: List<SpellClassItemData>?) {
        spellClassData = list ?: listOf()
        notifyDataSetChanged()
    }

    override fun getItemCount() = this.spellClassData.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item, parent, false)
        return ViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(this.spellClassData[position])
    }

    class ViewHolder(itemView: View, val onClick: (SpellClassItemData) -> Unit)
        : RecyclerView.ViewHolder(itemView) {
        private val spellName: TextView = itemView.findViewById(R.id.tv_name)

        private lateinit var currentSpell: SpellClassItemData

        init {
            itemView.setOnClickListener {
                currentSpell.let(onClick)
            }
        }

        fun bind(spell: SpellClassItemData) {
            currentSpell = spell
            spellName.text = spell.name
        }
    }
}