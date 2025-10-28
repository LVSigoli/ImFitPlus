package com.lucas.sigoli.sc3020428.imfitplus

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lucas.sigoli.sc3020428.imfitplus.databinding.ActivityCalculateCaloriesSpentBinding
import com.lucas.sigoli.sc3020428.imfitplus.databinding.ActivityCalculateImcBinding
import com.lucas.sigoli.sc3020428.imfitplus.dtos.User
import com.lucas.sigoli.sc3020428.imfitplus.enums.Gender
import com.lucas.sigoli.sc3020428.imfitplus.enums.SportsLevel


class CalculateCaloriesSpentActivity : AppCompatActivity() {

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
        var user = intent.getParcelableExtra<User>("User")

        if (user?.baseCalories == "0,00") user = calculateBaseCalories(user)
        else user
    }


    fun setupToolbar(binding: ActivityCalculateCaloriesSpentBinding) {
        setSupportActionBar(binding.toolbar.toolbar)

        supportActionBar?.title = "Gasto calórico diário"

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    fun calculateBaseCalories(user: User): User {
        var baseCals = 0.00

        val sportsLevel = getActivityLevel(user.sportsLevel)

        baseCals =
            if (user.gender == Gender.MASCULINO) {
                66 + (13.7 * user.weight) + (5 * user.height * 100) - (6.8 * user.age)
            } else {
                655 + (9.6 * user.weight) + (1.8 * user.height * 100) - (4.7 * user.age)
            }

        return user.copy(baseCalories = (baseCals * sportsLevel).toString())
    }

    fun getActivityLevel(sportsLevel: SportsLevel): Double {
        return when (sportsLevel) {
            SportsLevel.LEVE -> 1.375
            SportsLevel.INTENSO -> 1.9
            SportsLevel.MODERADO -> 1.55
            SportsLevel.SEDENTARIO -> 1.2
        }
    }


}