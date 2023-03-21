package com.example.dndapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dndapp.R
import com.example.dndapp.data.SheetItemData
import com.example.dndapp.ui.adapters.SpellClassItemAdapter
import com.example.dndapp.data.SpellClassItemData
import com.example.dndapp.ui.viewmodels.SheetViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class SpellClassFragment : Fragment(R.layout.fragment_spell_class) {
    private val spellClassItemAdapter = SpellClassItemAdapter(::onSpellClassItemClicked)

    private lateinit var spellClassListRV: RecyclerView

    private val args: SpellClassFragmentArgs by navArgs()

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

        spellClassListRV = view.findViewById(R.id.rv_class_list)
        spellClassListRV.layoutManager = LinearLayoutManager(requireContext())
        spellClassListRV.setHasFixedSize(true)
        spellClassListRV.adapter = spellClassItemAdapter

        populateSpellClassList()
    }

    private fun populateSpellClassList() {
        val list: List<SpellClassItemData> = listOf(
            SpellClassItemData("Cantrips", 0),
            SpellClassItemData("Level 1", 1),
            SpellClassItemData("Level 2", 2),
            SpellClassItemData("Level 3", 3),
            SpellClassItemData("Level 4", 4),
            SpellClassItemData("Level 5", 5),
            SpellClassItemData("Level 6", 6),
            SpellClassItemData("Level 7", 7),
            SpellClassItemData("Level 8", 8),
            SpellClassItemData("Level 9", 9)
        )

        spellClassItemAdapter.updateSpellClassList(list)
    }

    private fun onSpellClassItemClicked(spellClassItemData: SpellClassItemData) {
        val sheet = args.sheet

        if(sheet != null){
            val directions = SpellClassFragmentDirections.navigateToSpellListPage(sheet, spellClassItemData)
            findNavController().navigate(directions)
        }
        else{
            Log.d("test", "Sheet Argument is Null")
        }
    }
}