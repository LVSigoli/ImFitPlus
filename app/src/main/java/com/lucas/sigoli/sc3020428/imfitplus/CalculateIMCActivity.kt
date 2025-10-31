package com.lucas.sigoli.sc3020428.imfitplus

// External libraries
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

        binding.nameContainer.text = "Olá, ${user?.name}"

        val imcValue = user?.imc?.replace(",", ".")?.toDoubleOrNull() ?: 0.0

        binding.imcContainer.text = "Seu índice de massa corporal é de %.2f ".format(imcValue)

        user = checkCategory(user)

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

    private fun checkCategory(user:User?): User? {
        val numericIMC = user?.imc?.toDoubleOrNull() ?: return user
        val view = binding.categoryContainer

        var category: String = ""
        when {
            numericIMC < 18.5 -> {
                view.text = Category.LOW_WEIGHT.message
                category = Category.LOW_WEIGHT.message
                view.setTextColor(
                    ContextCompat.getColor(
                        view.context,
                        R.color.blue
                    )
                )
            }

            numericIMC < 24.9 -> {
                view.text = Category.NORMAL_WEIGHT.message
                category = Category.NORMAL_WEIGHT.message
                view.setTextColor(
                    ContextCompat.getColor(
                        view.context,
                        R.color.green
                    )
                )
            }

            numericIMC < 29.9 -> {
                view.text = Category.OVERWEIGHT.message
                category = Category.OVERWEIGHT.message
                view.setTextColor(
                    ContextCompat.getColor(
                        view.context,
                        R.color.orange
                    )
                )
            }

            else -> {
                view.text = Category.OBESITY.message
                category = Category.OBESITY.message
                view.setTextColor(
                    ContextCompat.getColor(
                        view.context,
                        R.color.red
                    )
                )
            }
        }

        return user.copy(imcCategory = category)
    }

    fun setupToolbar(binding: ActivityCalculateImcBinding) {
        setSupportActionBar(binding.toolbar.toolbar)

        supportActionBar?.title = "Resultado IMC"

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportActionBar?.setDisplayShowHomeEnabled(true)
    }
}