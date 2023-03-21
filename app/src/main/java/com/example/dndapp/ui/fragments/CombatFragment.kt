package com.example.dndapp.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dndapp.R
import com.example.dndapp.ui.adapters.WeaponItemAdapter
import com.example.dndapp.data.item.WeaponItemData
import com.example.dndapp.data.db.update.SheetCombatUpdateData
import com.example.dndapp.data.item.EquipmentItemData
import com.example.dndapp.ui.viewmodels.SheetViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class CombatFragment : Fragment(R.layout.fragment_combat) {

    private val weaponItemAdapter = WeaponItemAdapter(::onWeaponItemClicked)

    private lateinit var weaponListRV: RecyclerView

    private val args: CombatFragmentArgs by navArgs()

    val sheetViewModel: SheetViewModel by viewModels(
        ownerProducer = { requireActivity() }
    )

    private var hpCurrent: EditText? = null
    private var hpMax: EditText? = null
    private var hitDiceCurrent: EditText? = null
    private var hitDiceMax: EditText? = null
    private var armorClass: EditText? = null
    private var initiative: EditText? = null
    private var speed: EditText? = null
    private var spellAttackBonus: EditText? = null

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        val bottomNav = activity?.findViewById<BottomNavigationView>(R.id.nav_view)
        bottomNav?.visibility = View.VISIBLE

        weaponListRV = view.findViewById(R.id.rv_weapon_list)
        weaponListRV.layoutManager = LinearLayoutManager(requireContext())
        weaponListRV.setHasFixedSize(true)
        weaponListRV.adapter = weaponItemAdapter

        setupValues(view)
    }

    private fun setupValues(view: View) {
        hpCurrent = view.findViewById<EditText>(R.id.et_health)
        hpMax = view.findViewById<EditText>(R.id.et_health_max)
        hitDiceCurrent = view.findViewById<EditText>(R.id.et_hit_dice)
        hitDiceMax = view.findViewById<EditText>(R.id.et_hit_dice_max)
        armorClass = view.findViewById<EditText>(R.id.et_armor_class)
        initiative = view.findViewById<EditText>(R.id.et_initiative)
        speed = view.findViewById<EditText>(R.id.et_speed)
        spellAttackBonus = view.findViewById<EditText>(R.id.et_spell_attack_bonus)

        val sheet = sheetViewModel.getCurrentSheet()

        sheet.observe(this) { sheetData ->
            if (sheetData != null) {
                hpCurrent!!.setText(sheetData.hpCurrent.toString())
                hpMax!!.setText(sheetData.hpMax.toString())
                hitDiceCurrent!!.setText(sheetData.hitDiceCurrent.toString())
                hitDiceMax!!.setText(sheetData.hitDiceMax.toString())
                armorClass!!.setText(sheetData.armorClass.toString())
                initiative!!.setText(sheetData.initiative.toString())
                speed!!.setText(sheetData.speed.toString())
                spellAttackBonus!!.setText(sheetData.spellAttackBonus.toString())
            }
        }

        sheetViewModel.getWeapons(args.sheet.name).observe(this) { weapons->
            val list: MutableList<WeaponItemData> = mutableListOf()
            if (weapons != null) {
                for(w in weapons){
                    list += WeaponItemData(w.name, w.ind)
                }
            }
            weaponItemAdapter.updateWeaponList(list)
        }
    }

    private fun onWeaponItemClicked(weaponItemData: WeaponItemData) {
        val directions = CombatFragmentDirections.navigateToEquipmentDetailsPage(
            EquipmentItemData(weaponItemData.name, weaponItemData.ind)
        )
        findNavController().navigate(directions)
    }

    override fun onPause() {
        super.onPause()

        sheetViewModel.updateCurrentSheet(
            SheetCombatUpdateData(
                Integer.parseInt(hpCurrent?.text.toString()),
                Integer.parseInt(hpMax?.text.toString()),
                Integer.parseInt(hitDiceCurrent?.text.toString()),
                Integer.parseInt(hitDiceMax?.text.toString()),
                Integer.parseInt(armorClass?.text.toString()),
                Integer.parseInt(initiative?.text.toString()),
                Integer.parseInt(speed?.text.toString()),
                Integer.parseInt(spellAttackBonus?.text.toString())
            )
        )
    }
}