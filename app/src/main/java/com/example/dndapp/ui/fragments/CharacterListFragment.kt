package com.example.dndapp.ui.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dndapp.R
import com.example.dndapp.ui.adapters.SheetItemAdapter
import com.example.dndapp.data.SheetItemData
import com.example.dndapp.data.db.CharacterSheetData
import com.example.dndapp.ui.viewmodels.SheetViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.progressindicator.CircularProgressIndicator

class CharacterListFragment : Fragment(R.layout.fragment_character_list) {
    private val sheetItemAdapter = SheetItemAdapter(::onSheetItemSelected, ::onSheetItemDeleted)

    private lateinit var sheetListRV: RecyclerView
    private lateinit var loadingErrorTV: TextView
    private lateinit var loadingIndicator: CircularProgressIndicator

    val sheetViewModel: SheetViewModel by viewModels(
        ownerProducer = { requireActivity() }
    )

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        activity?.findViewById<BottomNavigationView>(R.id.nav_view)?.visibility = View.GONE

        loadingErrorTV = view.findViewById(R.id.tv_loading_error)
        loadingIndicator = view.findViewById(R.id.loading_indicator)

        sheetListRV = view.findViewById(R.id.rv_sheet_list)
        sheetListRV.layoutManager = LinearLayoutManager(requireContext())
        sheetListRV.setHasFixedSize(true)
        sheetListRV.adapter = sheetItemAdapter

        sheetViewModel.sheets.observe(this) { sheets ->
            val list: MutableList<SheetItemData> = mutableListOf()
            for(sheet in sheets){
                list += SheetItemData(sheet)
            }
            sheetItemAdapter.updateSheetList(list)
        }

        val addSheetButton = view.findViewById<Button>(R.id.b_add_sheet)

        addSheetButton.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            val alertView: View = layoutInflater.inflate(R.layout.dialog_add_sheet, null)
            builder.setView(alertView)

            val response = alertView.findViewById<EditText>(R.id.et_dialog_sheet)
            response.hint = "Character Name"

            var alert : AlertDialog? = null

            alertView.findViewById<Button>(R.id.b_dialog_sheet).setOnClickListener {
                sheetViewModel.addSheet(CharacterSheetData(response.text.toString()))

                alert?.cancel()
            }

            alert = builder.create()
            alert.show()
        }
    }

    private fun onSheetItemSelected(sheet: SheetItemData) {
        sheetViewModel.currentSheet = sheet
        val directions = CharacterListFragmentDirections.navigateToCombatPage(sheet)
        findNavController().navigate(directions)
    }

    private fun onSheetItemDeleted(sheet: SheetItemData) {
        sheetViewModel.removeSheet(sheet.name)
    }
}