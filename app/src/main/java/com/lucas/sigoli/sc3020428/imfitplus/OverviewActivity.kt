package com.lucas.sigoli.sc3020428.imfitplus

import android.os.Bundle
import android.os.PersistableBundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.lucas.sigoli.sc3020428.imfitplus.databinding.ActivityCalculateImcBinding
import com.lucas.sigoli.sc3020428.imfitplus.databinding.ActivityOverviewBinding
import com.lucas.sigoli.sc3020428.imfitplus.dtos.User

class OverviewActivity: AppCompatActivity() {
    private lateinit var binding: ActivityOverviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState, )
        var user: User? = intent.getParcelableExtra<User>("USER")


        binding = ActivityOverviewBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setupToolbar(binding)

        binding.nameContainer.text = "Nome:  ${user?.name}"

        binding.imcContainer.text = "IMC: ${user?.imc}"

        binding.imcCategoryContainer.text = "Categoria de IMC: ${user?.imcCategory}"

        binding.weigthContainer.text = "Peso ideal: ${user?.idealWeight}"


        binding.caloriesContainer.text = "Gasto calórico diário: ${user?.baseCalories}"

        binding.waterConsumption.text = "Quantidade recomentada de Agua por dia: %.2f L".format(calculateIdealWaterAmount(user?.weight))
    }


    fun setupToolbar(binding: ActivityOverviewBinding) {
        setSupportActionBar(binding.toolbar.toolbar)

        supportActionBar?.title = "Relatório"

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportActionBar?.setDisplayShowHomeEnabled(true)
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