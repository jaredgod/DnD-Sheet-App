package com.example.dndapp.ui.activities

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.dndapp.R
import com.example.dndapp.ui.viewmodels.SheetViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.snackbar.Snackbar

const val SHEET_INFORMATION = "com.example.android.lifecycleweather.SHEET_INFORMATION"

class SheetActivity  : AppCompatActivity() {

    private lateinit var appBarConfig: AppBarConfiguration

    val sheetViewModel: SheetViewModel by viewModels()

    val listener: NavigationBarView.OnItemSelectedListener = NavigationBarView.OnItemSelectedListener {
        val id = it.itemId

        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.nav_host_fragment
        ) as NavHostFragment
        val controller = navHostFragment.navController

        when (id) {

            R.id.combat_page -> {
                val bundle = bundleOf("sheet" to sheetViewModel.currentSheet)
                controller.popBackStack()
                controller.navigate(R.id.combat_page, bundle)
            }
            R.id.stat_page -> {
                val bundle = bundleOf("sheet" to sheetViewModel.currentSheet)
                controller.popBackStack()
                controller.navigate(R.id.stat_page, bundle)
            }
            R.id.spell_class_page -> {
                val bundle = bundleOf("sheet" to sheetViewModel.currentSheet)
                controller.popBackStack()
                controller.navigate(R.id.spell_class_page, bundle)
            }
            R.id.inventory_page -> {
                val bundle = bundleOf("sheet" to sheetViewModel.currentSheet)
                controller.popBackStack()
                controller.navigate(R.id.inventory_page, bundle)
            }
            R.id.note_page -> {
                val bundle = bundleOf("sheet" to sheetViewModel.currentSheet)
                controller.popBackStack()
                controller.navigate(R.id.note_page, bundle)
            }
        }
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sheet)

        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.nav_host_fragment
        ) as NavHostFragment
        val navController = navHostFragment.navController
        appBarConfig = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfig)

        val bottomNav = findViewById<BottomNavigationView>(R.id.nav_view)
        if(bottomNav != null){
            bottomNav.setupWithNavController(navController)
            bottomNav.labelVisibilityMode = NavigationBarView.LABEL_VISIBILITY_LABELED
            bottomNav.setOnItemSelectedListener(listener)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfig)
                || super.onSupportNavigateUp()
    }

}