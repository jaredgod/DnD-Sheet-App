package com.example.dndapp.ui.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dndapp.R
import com.example.dndapp.data.EquipmentItemData
import com.example.dndapp.ui.adapters.SpellItemAdapter
import com.example.dndapp.data.SpellItemData
import com.example.dndapp.data.db.EquipmentDetailsData
import com.example.dndapp.data.db.SpellDetailsData
import com.example.dndapp.ui.viewmodels.SheetViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class SpellListFragment : Fragment(R.layout.fragment_spell_list) {
    private val spellItemAdapter = SpellItemAdapter(::onSpellItemSelected, ::onSpellItemDeleted)

    private lateinit var spellListRV: RecyclerView

    private val args: SpellListFragmentArgs by navArgs()

    val sheetViewModel: SheetViewModel by viewModels(
        ownerProducer = { requireActivity() }
    )

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        activity?.findViewById<BottomNavigationView>(R.id.nav_view)?.visibility = View.VISIBLE

        spellListRV = view.findViewById(R.id.rv_spell_list)
        spellListRV.layoutManager = LinearLayoutManager(requireContext())
        spellListRV.setHasFixedSize(true)
        spellListRV.adapter = spellItemAdapter

        sheetViewModel.getSpells(args.sheet.name, args.spellClass.ind).observe(this) { spells ->
            val list: MutableList<SpellItemData> = mutableListOf()
            if (spells != null) {
                for(s in spells){
                    list += SpellItemData(s.name, s.ind)
                }
            }
            spellItemAdapter.updateSpellList(list)
        }

        val addSpellButton = view.findViewById<Button>(R.id.b_add_spell)

        addSpellButton.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            val alertView: View = layoutInflater.inflate(R.layout.dialog_add_sheet, null)
            builder.setView(alertView)

            val response = alertView.findViewById<EditText>(R.id.et_dialog_sheet)
            response.hint = "Spell Name"

            var alert : AlertDialog? = null

            alertView.findViewById<Button>(R.id.b_dialog_sheet).setOnClickListener {
                sheetViewModel.addSpell(SpellDetailsData(args.sheet.name, args.spellClass.ind, response.text.toString()))

                alert?.cancel()
            }

            alert = builder.create()
            alert.show()
        }
    }

    private fun onSpellItemSelected(spellItemData: SpellItemData) {

    }

    private fun onSpellItemDeleted(spellItemData: SpellItemData) {
        sheetViewModel.removeSpell(spellItemData.ind)
    }
}