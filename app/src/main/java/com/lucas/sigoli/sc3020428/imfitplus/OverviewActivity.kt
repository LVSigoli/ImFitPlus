package com.lucas.sigoli.sc3020428.imfitplus

// External Libraries
import android.os.Bundle
import android.content.Intent
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

// Utils
import com.lucas.sigoli.sc3020428.imfitplus.constants.Actions

// Services
import com.lucas.sigoli.sc3020428.imfitplus.database.repositories.UserRepository

// Types
import com.lucas.sigoli.sc3020428.imfitplus.dtos.User
import com.lucas.sigoli.sc3020428.imfitplus.databinding.ActivityOverviewBinding


class OverviewActivity: AppCompatActivity() {
    private lateinit var userRepository: UserRepository
    private lateinit var binding: ActivityOverviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        @Suppress("deprecation")
        val user: User? = intent.getParcelableExtra<User>("USER")

        userRepository = UserRepository(this)
        val idealWaterConsumption = calculateIdealWaterAmount(user?.weight)

        saveUser(user, idealWaterConsumption)
        binding = ActivityOverviewBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setupToolbar(binding)

        binding.nameContainer.text = "Nome:  ${user?.name}"

        binding.imcContainer.text = "IMC: ${user?.imc}"

        binding.imcCategoryContainer.text = "Categoria de IMC: ${user?.imcCategory}"

        binding.weigthContainer.text = "Peso ideal: ${user?.idealWeight}"


        binding.caloriesContainer.text = "Gasto calórico diário: ${user?.baseCalories}"

        binding.waterConsumption.text = "Quantidade recomentada de Agua por dia: %.2f L".format(idealWaterConsumption)


        binding.backButton.setOnClickListener { finish() }

        binding.seeHistory.setOnClickListener {
            Intent(Actions.HISTORY).let{

            it.putExtra("USER", user)

            startActivity(it)
            }
        }
    }

    fun setupToolbar(binding: ActivityOverviewBinding) {
        setSupportActionBar(binding.toolbar.toolbar)

        supportActionBar?.title = "Relatório"

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    fun saveUser(user:User?, waterConsumption: Double?){
        if (user == null) return

        val updatedUser = user.copy(
            waterConsumption = "%.2f".format(waterConsumption ?: 0.0)
        )

        userRepository.insert(updatedUser)
    }

    fun calculateIdealWaterAmount(weight: Double?): Double?{
        if(weight == null) return  0.00

        return 0.035 * weight
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
}