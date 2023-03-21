package com.example.dndapp.ui.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dndapp.R
import com.example.dndapp.ui.adapters.EquipmentItemAdapter
import com.example.dndapp.data.item.EquipmentItemData
import com.example.dndapp.data.db.EquipmentDetailsData
import com.example.dndapp.ui.viewmodels.SheetViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class InventoryFragment : Fragment(R.layout.fragment_inventory) {
    private val equipItemAdapter = EquipmentItemAdapter(::onInvItemSelected, ::onInvItemDeleted)

    private lateinit var invListRV: RecyclerView

    private val args: InventoryFragmentArgs by navArgs()

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

        invListRV = view.findViewById(R.id.rv_item_list)
        invListRV.layoutManager = LinearLayoutManager(requireContext())
        invListRV.setHasFixedSize(true)
        invListRV.adapter = equipItemAdapter

        sheetViewModel.getEquipment(args.sheet.name).observe(this) { equipment->
            val list: MutableList<EquipmentItemData> = mutableListOf()
            if (equipment != null) {
                for(e in equipment){
                    list += EquipmentItemData(e.name, e.ind)
                }
            }
            equipItemAdapter.updateEquipmentList(list)
        }

        val addEquipmentButton = view.findViewById<Button>(R.id.b_add_item)

        addEquipmentButton.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            val alertView: View = layoutInflater.inflate(R.layout.dialog_add_equipment, null)
            builder.setView(alertView)

            val response = alertView.findViewById<EditText>(R.id.et_dialog)
            response.hint = "Equipment Name"


            val weaponCheckBox = alertView.findViewById<CheckBox>(R.id.cb_dialog)

            var alert : AlertDialog? = null

            alertView.findViewById<Button>(R.id.b_dialog).setOnClickListener {
                sheetViewModel.addEquipment(EquipmentDetailsData(args.sheet.name, response.text.toString(), weaponCheckBox.isChecked))

                alert?.cancel()
            }

            alert = builder.create()
            alert.show()
        }
    }

    private fun onInvItemSelected(equipmentItemData: EquipmentItemData) {
        val directions = InventoryFragmentDirections.navigateToEquipmentDetailsPage(equipmentItemData)
        findNavController().navigate(directions)
    }

    private fun onInvItemDeleted(invItemData: EquipmentItemData) {
        sheetViewModel.removeEquipment(invItemData.ind)
    }
}