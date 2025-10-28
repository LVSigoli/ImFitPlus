package com.lucas.sigoli.sc3020428.imfitplus

// External libraries
import android.os.Bundle
import android.content.Intent
import android.annotation.SuppressLint
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

// Utils
import com.lucas.sigoli.sc3020428.imfitplus.enums.Gender
import com.lucas.sigoli.sc3020428.imfitplus.constants.Actions
import com.lucas.sigoli.sc3020428.imfitplus.enums.SportsLevel

// Types
import com.lucas.sigoli.sc3020428.imfitplus.dtos.User
import com.lucas.sigoli.sc3020428.imfitplus.databinding.ActivityCalculateCaloriesSpentBinding

class CalculateCaloriesSpentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCalculateCaloriesSpentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCalculateCaloriesSpentBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setupToolbar(binding)

        binding.backButton.setOnClickListener { finish() }
    }

    @SuppressLint("SetTextI18n", "DefaultLocale")
    override fun onStart() {
        super.onStart()

        @Suppress("Deprecation")
        var user = intent.getParcelableExtra<User>("USER")

        if (user?.baseCalories == "0,00") user = calculateBaseCalories(user)
        else user
        val baseCalories = user?.baseCalories?.toDoubleOrNull() ?: 0.0

        binding.textDisplay.text = "${user?.name} seu gasto calórico diário é "

        binding.caloriesDisplay.text = String.format("%.2f kcal/dia", baseCalories)

        binding.calcButton.setOnClickListener {
            Intent(Actions.IDEAL_WEIGHT).let {
                it.putExtra("USER", user)

                startActivity(it)
            }

        }

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