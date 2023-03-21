package com.example.dndapp.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dndapp.R
import com.example.dndapp.data.db.SheetNoteUpdateData
import com.example.dndapp.data.db.SheetStatUpdateData
import com.example.dndapp.ui.viewmodels.SheetViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class StatFragment : Fragment(R.layout.fragment_stats) {

    private val args: StatFragmentArgs by navArgs()

    private var level: EditText? = null
    private var proficiencyBonus: EditText? = null
    private var strengthVal: EditText? = null
    private var strengthMod: EditText? = null
    private var dexterityVal: EditText? = null
    private var dexterityMod: EditText? = null
    private var constitutionVal: EditText? = null
    private var constitutionMod: EditText? = null
    private var intelligenceVal: EditText? = null
    private var intelligenceMod: EditText? = null
    private var wisdomVal: EditText? = null
    private var wisdomMod: EditText? = null
    private var charismaVal: EditText? = null
    private var charismaMod: EditText? = null

    private var acrobaticsToggle: CheckBox? = null
    private var acrobaticsMod: EditText? = null
    private var animalHandlingToggle: CheckBox? = null
    private var animalHandlingMod: EditText? = null
    private var arcanaToggle: CheckBox? = null
    private var arcanaMod: EditText? = null
    private var athleticsToggle: CheckBox? = null
    private var athleticsMod: EditText? = null
    private var deceptionToggle: CheckBox? = null
    private var deceptionMod: EditText? = null
    private var historyToggle: CheckBox? = null
    private var historyMod: EditText? = null
    private var insightToggle: CheckBox? = null
    private var insightMod: EditText? = null
    private var intimidationToggle: CheckBox? = null
    private var intimidationMod: EditText? = null
    private var investigationToggle: CheckBox? = null
    private var investigationMod: EditText? = null
    private var medicineToggle: CheckBox? = null
    private var medicineMod: EditText? = null
    private var natureToggle: CheckBox? = null
    private var natureMod: EditText? = null
    private var perceptionToggle: CheckBox? = null
    private var perceptionMod: EditText? = null
    private var performanceToggle: CheckBox? = null
    private var performanceMod: EditText? = null
    private var persuasionToggle: CheckBox? = null
    private var persuasionMod: EditText? = null
    private var religionToggle: CheckBox? = null
    private var religionMod: EditText? = null
    private var sleightOfHandToggle: CheckBox? = null
    private var sleightOfHandMod: EditText? = null
    private var stealthToggle: CheckBox? = null
    private var stealthMod: EditText? = null
    private var survivalToggle: CheckBox? = null
    private var survivalMod: EditText? = null

    private val sheetViewModel: SheetViewModel by viewModels(
        ownerProducer = { requireActivity() }
    )
    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        activity?.findViewById<BottomNavigationView>(R.id.nav_view)?.visibility = View.VISIBLE

        setupValues(view)
    }

    private fun setupValues(view: View){
        level = view.findViewById<EditText>(R.id.et_level)
        proficiencyBonus = view.findViewById<EditText>(R.id.et_proficiency_bonus)
        strengthVal = view.findViewById<EditText>(R.id.et_strength)
        strengthMod = view.findViewById<EditText>(R.id.et_strength_mod)
        dexterityVal = view.findViewById<EditText>(R.id.et_dexterity)
        dexterityMod = view.findViewById<EditText>(R.id.et_dexterity_mod)
        constitutionVal = view.findViewById<EditText>(R.id.et_constitution)
        constitutionMod = view.findViewById<EditText>(R.id.et_constitution_mod)
        intelligenceVal = view.findViewById<EditText>(R.id.et_intelligence)
        intelligenceMod = view.findViewById<EditText>(R.id.et_intelligence_mod)
        wisdomVal = view.findViewById<EditText>(R.id.et_wisdom)
        wisdomMod = view.findViewById<EditText>(R.id.et_wisdom_mod)
        charismaVal = view.findViewById<EditText>(R.id.et_charisma)
        charismaMod = view.findViewById<EditText>(R.id.et_charisma_mod)

        acrobaticsToggle = view.findViewById<CheckBox>(R.id.checkbox_acrobatics)
        acrobaticsMod = view.findViewById<EditText>(R.id.et_acrobatics)
        animalHandlingToggle = view.findViewById<CheckBox>(R.id.checkbox_animal_handling)
        animalHandlingMod = view.findViewById<EditText>(R.id.et_animal_handling)
        arcanaToggle = view.findViewById<CheckBox>(R.id.checkbox_arcana)
        arcanaMod = view.findViewById<EditText>(R.id.et_arcana)
        athleticsToggle = view.findViewById<CheckBox>(R.id.checkbox_athletics)
        athleticsMod = view.findViewById<EditText>(R.id.et_athletics)
        deceptionToggle = view.findViewById<CheckBox>(R.id.checkbox_deception)
        deceptionMod = view.findViewById<EditText>(R.id.et_deception)
        historyToggle = view.findViewById<CheckBox>(R.id.checkbox_history)
        historyMod = view.findViewById<EditText>(R.id.et_history)
        insightToggle = view.findViewById<CheckBox>(R.id.checkbox_insight)
        insightMod = view.findViewById<EditText>(R.id.et_insight)
        intimidationToggle = view.findViewById<CheckBox>(R.id.checkbox_intimidation)
        intimidationMod = view.findViewById<EditText>(R.id.et_intimidation)
        investigationToggle = view.findViewById<CheckBox>(R.id.checkbox_investigation)
        investigationMod = view.findViewById<EditText>(R.id.et_investigation)
        medicineToggle = view.findViewById<CheckBox>(R.id.checkbox_medicine)
        medicineMod = view.findViewById<EditText>(R.id.et_medicine)
        natureToggle = view.findViewById<CheckBox>(R.id.checkbox_nature)
        natureMod = view.findViewById<EditText>(R.id.et_nature )
        perceptionToggle = view.findViewById<CheckBox>(R.id.checkbox_perception)
        perceptionMod = view.findViewById<EditText>(R.id.et_perception)
        performanceToggle = view.findViewById<CheckBox>(R.id.checkbox_performance)
        performanceMod = view.findViewById<EditText>(R.id.et_performance)
        persuasionToggle = view.findViewById<CheckBox>(R.id.checkbox_persuasion)
        persuasionMod = view.findViewById<EditText>(R.id.et_persuasion)
        religionToggle = view.findViewById<CheckBox>(R.id.checkbox_religion)
        religionMod = view.findViewById<EditText>(R.id.et_religion)
        sleightOfHandToggle = view.findViewById<CheckBox>(R.id.checkbox_sleight_of_hand)
        sleightOfHandMod = view.findViewById<EditText>(R.id.et_sleight_of_hand)
        stealthToggle = view.findViewById<CheckBox>(R.id.checkbox_stealth)
        stealthMod = view.findViewById<EditText>(R.id.et_stealth)
        survivalToggle = view.findViewById<CheckBox>(R.id.checkbox_survival)
        survivalMod = view.findViewById<EditText>(R.id.et_survival)

        val sheet = sheetViewModel.getCurrentSheet()

        sheet.observe(this) { sheetData ->
            if(sheetData != null) {
                level!!.setText(sheetData.level.toString())
                proficiencyBonus?.setText(sheetData.proficiencyBonus.toString())
                strengthVal?.setText(sheetData.strengthVal.toString())
                strengthMod?.setText(sheetData.strengthMod.toString())
                dexterityVal?.setText(sheetData.dexterityVal.toString())
                dexterityMod?.setText(sheetData.dexterityMod.toString())
                constitutionVal?.setText(sheetData.constitutionVal.toString())
                constitutionMod?.setText(sheetData.constitutionMod.toString())
                intelligenceVal?.setText(sheetData.intelligenceVal.toString())
                intelligenceMod?.setText(sheetData.intelligenceMod.toString())
                wisdomVal?.setText(sheetData.wisdomVal.toString())
                wisdomMod?.setText(sheetData.wisdomMod.toString())
                charismaVal?.setText(sheetData.charismaVal.toString())
                charismaMod?.setText(sheetData.charismaMod.toString())

                acrobaticsToggle?.isChecked = sheetData.acrobaticsToggle
                acrobaticsMod?.setText(sheetData.acrobaticsMod.toString())
                animalHandlingToggle?.isChecked = sheetData.animalHandlingToggle
                animalHandlingMod?.setText(sheetData.animalHandlingMod.toString())
                arcanaToggle?.isChecked = sheetData.arcanaToggle
                arcanaMod?.setText(sheetData.arcanaMod.toString())
                athleticsToggle?.isChecked = sheetData.athleticsToggle
                athleticsMod?.setText(sheetData.athleticsMod.toString())
                deceptionToggle?.isChecked = sheetData.deceptionToggle
                deceptionMod?.setText(sheetData.deceptionMod.toString())
                historyToggle?.isChecked = sheetData.historyToggle
                historyMod?.setText(sheetData.historyMod.toString())
                insightToggle?.isChecked = sheetData.insightToggle
                insightMod?.setText(sheetData.insightMod.toString())
                intimidationToggle?.isChecked = sheetData.intimidationToggle
                intimidationMod?.setText(sheetData.intimidationMod.toString())
                investigationToggle?.isChecked = sheetData.investigationToggle
                investigationMod?.setText(sheetData.investigationMod.toString())
                medicineToggle?.isChecked = sheetData.medicineToggle
                medicineMod?.setText(sheetData.medicineMod.toString())
                natureToggle?.isChecked = sheetData.natureToggle
                natureMod?.setText(sheetData.natureMod.toString())
                perceptionToggle?.isChecked = sheetData.perceptionToggle
                perceptionMod?.setText(sheetData.perceptionMod.toString())
                performanceToggle?.isChecked = sheetData.performanceToggle
                performanceMod?.setText(sheetData.performanceMod.toString())
                persuasionToggle?.isChecked = sheetData.persuasionToggle
                persuasionMod?.setText(sheetData.persuasionMod.toString())
                religionToggle?.isChecked = sheetData.religionToggle
                religionMod?.setText(sheetData.religionMod.toString())
                sleightOfHandToggle?.isChecked = sheetData.sleightOfHandToggle
                sleightOfHandMod?.setText(sheetData.sleightOfHandMod.toString())
                stealthToggle?.isChecked = sheetData.stealthToggle
                stealthMod?.setText(sheetData.stealthMod.toString())
                survivalToggle?.isChecked = sheetData.survivalToggle
                survivalMod?.setText(sheetData.survivalMod.toString())
            }
        }
    }


    override fun onPause() {
        super.onPause()

        sheetViewModel.updateCurrentSheet(SheetStatUpdateData(
            Integer.parseInt(level?.text.toString()),
            Integer.parseInt(proficiencyBonus?.text.toString()),
            Integer.parseInt(strengthVal?.text.toString()),
            Integer.parseInt(strengthMod?.text.toString()),
            Integer.parseInt(dexterityVal?.text.toString()),
            Integer.parseInt(dexterityMod?.text.toString()),
            Integer.parseInt(constitutionVal?.text.toString()),
            Integer.parseInt(constitutionMod?.text.toString()),
            Integer.parseInt(intelligenceVal?.text.toString()),
            Integer.parseInt(intelligenceMod?.text.toString()),
            Integer.parseInt(wisdomVal?.text.toString()),
            Integer.parseInt(wisdomMod?.text.toString()),
            Integer.parseInt(charismaVal?.text.toString()),
            Integer.parseInt(charismaMod?.text.toString()),

            acrobaticsToggle?.isChecked!!,
            Integer.parseInt(acrobaticsMod?.text.toString()),
            animalHandlingToggle?.isChecked!!,
            Integer.parseInt(animalHandlingMod?.text.toString()),
            arcanaToggle?.isChecked!!,
            Integer.parseInt(arcanaMod?.text.toString()),
            athleticsToggle?.isChecked!!,
            Integer.parseInt(athleticsMod?.text.toString()),
            deceptionToggle?.isChecked!!,
            Integer.parseInt(deceptionMod?.text.toString()),
            historyToggle?.isChecked!!,
            Integer.parseInt(historyMod?.text.toString()),
            insightToggle?.isChecked!!,
            Integer.parseInt(insightMod?.text.toString()),
            intimidationToggle?.isChecked!!,
            Integer.parseInt(intimidationMod?.text.toString()),
            investigationToggle?.isChecked!!,
            Integer.parseInt(investigationMod?.text.toString()),
            medicineToggle?.isChecked!!,
            Integer.parseInt(medicineMod?.text.toString()),
            natureToggle?.isChecked!!,
            Integer.parseInt(natureMod?.text.toString()),
            perceptionToggle?.isChecked!!,
            Integer.parseInt(perceptionMod?.text.toString()),
            performanceToggle?.isChecked!!,
            Integer.parseInt(performanceMod?.text.toString()),
            persuasionToggle?.isChecked!!,
            Integer.parseInt(persuasionMod?.text.toString()),
            religionToggle?.isChecked!!,
            Integer.parseInt(religionMod?.text.toString()),
            sleightOfHandToggle?.isChecked!!,
            Integer.parseInt(sleightOfHandMod?.text.toString()),
            stealthToggle?.isChecked!!,
            Integer.parseInt(stealthMod?.text.toString()),
            survivalToggle?.isChecked!!,
            Integer.parseInt(survivalMod?.text.toString()),
        ))

    }
}