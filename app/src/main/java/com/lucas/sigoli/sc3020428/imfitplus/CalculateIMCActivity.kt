package com.lucas.sigoli.sc3020428.imfitplus

// External libraries
import java.util.Locale
import android.os.Bundle
import android.view.MenuItem
import android.content.Intent
import android.annotation.SuppressLint
import androidx.core.content.ContextCompat
import androidx.appcompat.app.AppCompatActivity

// Utils
import com.lucas.sigoli.sc3020428.imfitplus.enums.Category

// Types
import com.lucas.sigoli.sc3020428.imfitplus.dtos.User
import com.lucas.sigoli.sc3020428.imfitplus.constants.Actions
import com.lucas.sigoli.sc3020428.imfitplus.databinding.ActivityCalculateImcBinding

class CalculateIMCActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalculateImcBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCalculateImcBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setupToolbar(binding)

        binding.backButton.setOnClickListener { finish() }
    }

    @SuppressLint("SetTextI18n")
    override fun onStart() {
        super.onStart()

        @Suppress("DEPRECATION")
        var user: User? = intent.getParcelableExtra<User>("USER")

        if (user?.imc == "0,00") user = calculateIMC(user)
        else user

        binding.nameContainer.text = "Olá, ${user?.name}"

        val imcValue = user?.imc?.replace(",", ".")?.toDoubleOrNull() ?: 0.0
        binding.imcContainer.text = "Seu índice de massa corporal é de %.2f ".format(imcValue)

        checkCategory(user?.imc)

        binding.calcButton.setOnClickListener {
            Intent(Actions.CALCULATE_CALORIES).let {
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


    private fun calculateIMC(user: User): User {
        val imc = user.weight / (user.height * user.height)

        val formatedImc = String.format(Locale.US, "%.2f", imc)

        return user.copy(imc = formatedImc)
    }


    private fun checkCategory(imc: String?) {
        val numericIMC = imc?.toDoubleOrNull() ?: return
        val view = binding.categoryContainer
        when {
            numericIMC < 18.5 -> {
                view.text = Category.LOW_WEIGHT.message
                view.setTextColor(
                    ContextCompat.getColor(
                        view.context,
                        R.color.blue
                    )
                )
            }

            numericIMC < 24.9 -> {
                view.text = Category.NORMAL_WEIGHT.message
                view.setTextColor(
                    ContextCompat.getColor(
                        view.context,
                        R.color.green
                    )
                )
            }

            numericIMC < 29.9 -> {
                view.text = Category.OVERWEIGHT.message
                view.setTextColor(
                    ContextCompat.getColor(
                        view.context,
                        R.color.orange
                    )
                )
            }

            else -> {
                view.text = Category.OBESITY.message
                view.setTextColor(
                    ContextCompat.getColor(
                        view.context,
                        R.color.red
                    )
                )
            }
        }
    }

    fun setupToolbar(binding: ActivityCalculateImcBinding) {
        setSupportActionBar(binding.toolbar.toolbar)

        supportActionBar?.title = "Resultado IMC"

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportActionBar?.setDisplayShowHomeEnabled(true)
    }


}