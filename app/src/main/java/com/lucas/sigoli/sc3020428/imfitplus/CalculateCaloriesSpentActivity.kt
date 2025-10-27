package com.lucas.sigoli.sc3020428.imfitplus

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lucas.sigoli.sc3020428.imfitplus.databinding.ActivityCalculateCaloriesSpentBinding
import com.lucas.sigoli.sc3020428.imfitplus.databinding.ActivityCalculateImcBinding
import com.lucas.sigoli.sc3020428.imfitplus.dtos.User


class CalculateCaloriesSpentActivity: AppCompatActivity()  {

    private lateinit var binding: ActivityCalculateCaloriesSpentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCalculateCaloriesSpentBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setupToolbar(binding)
    }

    override fun onStart() {
        super.onStart()

        @Suppress("Deprecation")
        val user = intent.getParcelableExtra<User>("User")

        if
    }


    fun setupToolbar(binding: ActivityCalculateCaloriesSpentBinding) {
        setSupportActionBar(binding.toolbar.toolbar)

        supportActionBar?.title = "Gasto calórico diário"

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

}