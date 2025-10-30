package com.lucas.sigoli.sc3020428.imfitplus

// External Libraries
import kotlin.math.pow
import android.os.Bundle
import android.view.MenuItem
import android.content.Intent
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity

// Types
import com.lucas.sigoli.sc3020428.imfitplus.dtos.User
import com.lucas.sigoli.sc3020428.imfitplus.databinding.ActivityIdealWeightBinding

class IdealWeightActivity : AppCompatActivity() {

    var differenceWeights: Double = 0.00

    private lateinit var binding: ActivityIdealWeightBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityIdealWeightBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setupToolbar(binding)
    }


    @SuppressLint("SetTextI18n")
    override fun onStart() {
        super.onStart()

        @Suppress("DEPRECATION")
        var user: User? = intent.getParcelableExtra<User>("USER")

        if (user?.idealWeight == "0,00") user = calculateIdealWeight(user)
        else user

        differenceWeights = calculateDifferenceWeights(user)

        binding.currentWeight.text = "Seu peso Atual é %.2f kg".format(user?.weight?.toDouble())

        binding.informationDisplay.text =
            "Peso ideal: %.2f kg".format(user?.idealWeight?.toDouble())

        val message = when {
            differenceWeights > 0 -> "Você está %.2f kg acima do seu peso ideal.".format(
                differenceWeights
            )

            differenceWeights < 0 -> "Você está %.2f kg abaixo do peso ideal.".format(
                differenceWeights
            )

            else -> "Parabéns! Você está no seu peso ideal 🎉"
        }

        binding.differenceDisplay.text = message

        binding.finishButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }

    }

    fun setupToolbar(binding: ActivityIdealWeightBinding) {
        setSupportActionBar(binding.toolbar.toolbar)

        supportActionBar?.title = "Peso ideal"

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportActionBar?.setDisplayShowHomeEnabled(true)
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

    fun calculateIdealWeight(user: User?): User? {
        if (user == null) return null

        val idealWeight = 22 * user.height.pow(2)

        return user.copy(idealWeight = "%.2f".format(idealWeight))
    }

    fun calculateDifferenceWeights(user: User?): Double {
        val weight = user?.weight ?: 0.0

        val idealWeight = user?.idealWeight
            ?.replace(",", ".")
            ?.toDoubleOrNull() ?: 0.0

        return weight - idealWeight
    }

}