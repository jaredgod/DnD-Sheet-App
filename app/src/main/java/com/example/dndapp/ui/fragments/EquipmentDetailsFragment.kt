package com.example.dndapp.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.dndapp.R
import com.example.dndapp.data.db.update.EquipmentUpdateData
import com.example.dndapp.ui.viewmodels.SheetViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView


class EquipmentDetailsFragment : Fragment(R.layout.fragment_equipment_details) {

    private val args: EquipmentDetailsFragmentArgs by navArgs()

    val sheetViewModel: SheetViewModel by viewModels(
        ownerProducer = { requireActivity() }
    )

    var title: TextView? = null
    var weapon: CheckBox? = null
    var description: EditText? = null


    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        activity?.findViewById<BottomNavigationView>(R.id.nav_view)?.visibility = View.GONE

        title = view.findViewById(R.id.tv_description_title)
        weapon = view.findViewById(R.id.cb_weapon)
        description = view.findViewById(R.id.et_description)

        sheetViewModel.getEquipmentByIndex(args.equipment.ind).observe(this) { details->
            title?.text = details?.name
            weapon?.isChecked = details?.isWeapon == true
            description?.setText(details?.details)
        }
    }

    override fun onPause(){
        super.onPause()
        if(description != null && weapon != null){
            sheetViewModel.updateEquipment(EquipmentUpdateData(description!!.text.toString(), weapon!!.isChecked, args.equipment.ind))
        }
    }
}