package com.example.dndapp.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.dndapp.R
import com.example.dndapp.data.db.SheetNoteUpdateData
import com.example.dndapp.ui.viewmodels.SheetViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class NoteFragment : Fragment(R.layout.fragment_note) {


    private val args: NoteFragmentArgs by navArgs()

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

        var notes = view.findViewById<EditText>(R.id.et_notes)

        val sheet = sheetViewModel.getCurrentSheet()

        sheet.observe(this) { sheetData ->
            if(sheetData != null) {
                if(notes != null) notes!!.setText(sheetData.notes)
            }
        }


    }

    override fun onPause(){
        super.onPause()
        var notes = view?.findViewById<EditText>(R.id.et_notes)
        if(notes != null){
            sheetViewModel.updateCurrentSheet(SheetNoteUpdateData(notes!!.text.toString()))
        }
    }
}