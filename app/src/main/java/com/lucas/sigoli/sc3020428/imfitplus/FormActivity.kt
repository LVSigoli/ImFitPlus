package com.lucas.sigoli.sc3020428.imfitplus

import android.os.Bundle
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.core.widget.doOnTextChanged
import androidx.appcompat.app.AppCompatActivity
import com.lucas.sigoli.sc3020428.imfitplus.databinding.ActivityFormBinding
import com.lucas.sigoli.sc3020428.imfitplus.dtos.User

class FormActivity : AppCompatActivity() {


    private lateinit var binding: ActivityFormBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar(binding)

        setupSportsLevelSpinner()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

//
//    private fun createUser(){
//        val name = binding.nameInput.text.toString()
//        val age = binding.ageInput.text.toString().toIntOrNull() ?: 0
//        val height = binding.heightInput.text.toString().toDoubleOrNull()?: 0.00
//        val weight = binding.weightInput.text.toString().toDoubleOrNull()?: 0.00
//
//
//        return User(age = age, name = name, '', weight = weight, height = height, sportsLevel = '')
//
//    }

    // Utils
    fun setupToolbar(binding: ActivityFormBinding) {
        setSupportActionBar(binding.toolbar.toolbar)

        supportActionBar?.title = "Informações Gerais"

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    private fun setupSportsLevelSpinner() {
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.sports_levels_labels,
            android.R.layout.simple_spinner_item
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.sportsLevelSpinner.adapter = adapter
    }
}
