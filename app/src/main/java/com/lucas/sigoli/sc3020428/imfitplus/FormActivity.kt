package com.lucas.sigoli.sc3020428.imfitplus

// External libraries
import java.util.Locale
import android.os.Bundle
import android.widget.Toast
import android.view.MenuItem
import android.content.Intent
import android.widget.ArrayAdapter
import androidx.core.widget.doOnTextChanged

// Services
import com.lucas.sigoli.sc3020428.imfitplus.validators.UserValidator

// Utils
import com.lucas.sigoli.sc3020428.imfitplus.enums.Gender
import com.lucas.sigoli.sc3020428.imfitplus.utils.showToast
import com.lucas.sigoli.sc3020428.imfitplus.constants.Actions
import com.lucas.sigoli.sc3020428.imfitplus.enums.SportsLevel

// Types
import androidx.appcompat.app.AppCompatActivity
import com.lucas.sigoli.sc3020428.imfitplus.dtos.User
import com.lucas.sigoli.sc3020428.imfitplus.databinding.ActivityFormBinding

class FormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFormBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar(binding)

        setupSportsLevelSpinner()

        binding.heightInput.doOnTextChanged { text, _, _, _ ->
            text?.let { input ->

                val clean = input.toString().replace("[^0-9]".toRegex(), "")

                if (clean.isNotEmpty()) {

                    val value = clean.toDouble() / 100

                    val formatted = String.format(Locale.US, "%.2f", value)


                    if (formatted != input.toString()) {
                        binding.heightInput.setText(formatted)
                        binding.heightInput.setSelection(formatted.length)
                    }
                }
            }
        }

        binding.weightInput.doOnTextChanged { text, _, _, _ ->
            text?.let { input ->
                val clean = input.toString().replace("[^0-9]".toRegex(), "")

                if (clean.isNotEmpty()) {

                    val value = clean.toDouble() / 100

                    val formatted = String.format(Locale.US, "%.2f", value)

                    if (formatted != input.toString()) {
                        binding.weightInput.setText(formatted)
                        binding.weightInput.setSelection(formatted.length)
                    }
                }
            }
        }

        binding.backButton.setOnClickListener { finish() }

        binding.calcButton.setOnClickListener { handleCalculateClick() }
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

    private fun handleCalculateClick() {
        val user: User = createUser()

        val message = UserValidator.validate(user)

        if (!message.isEmpty()) {
            showToast(message, Toast.LENGTH_LONG)
            return
        }

        Intent(Actions.CALCULATE_IMC).let {
            it.putExtra("USER", user)
            startActivity(it)
        }
    }

    private fun createUser(): User {
        val name = binding.nameInput.text.toString()

        val age = binding.ageInput.text.toString().toIntOrNull() ?: 0

        val height = binding.heightInput.text.toString().toDoubleOrNull() ?: 0.00

        val weight = binding.weightInput.text.toString().toDoubleOrNull() ?: 0.00

        val selectedGender = binding.genderGroup.checkedRadioButtonId

        val selectedLevel = binding.sportsLevelSpinner.selectedItem.toString()

        val sportsLevel = when (selectedLevel) {
            "Leve" -> SportsLevel.LEVE
            "Moderado" -> SportsLevel.MODERADO
            "Intenso" -> SportsLevel.INTENSO
            else -> SportsLevel.SEDENTARIO
        }

        val gender = when (selectedGender) {
            R.id.gender_male -> Gender.MASCULINO
            R.id.gender_female -> Gender.FEMININO
            else -> Gender.NAO_INFORMADO
        }

        return User(
            age = age,
            name = name,
            gender = gender,
            weight = weight,
            height = height,
            sportsLevel = sportsLevel
        )
    }

    fun setupToolbar(binding: ActivityFormBinding) {
        setSupportActionBar(binding.toolbar.toolbar)

        supportActionBar?.title = "Informações Gerais"

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    private fun setupSportsLevelSpinner() {
        val adapter = ArrayAdapter.createFromResource(
            this, R.array.sports_levels_labels, android.R.layout.simple_spinner_item
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.sportsLevelSpinner.adapter = adapter
    }
}
