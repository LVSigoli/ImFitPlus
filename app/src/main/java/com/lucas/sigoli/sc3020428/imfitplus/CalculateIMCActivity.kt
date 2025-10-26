package com.lucas.sigoli.sc3020428.imfitplus

// External libraries
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.lucas.sigoli.sc3020428.imfitplus.databinding.ActivityCalculateImcBinding
import com.lucas.sigoli.sc3020428.imfitplus.dtos.User
import com.lucas.sigoli.sc3020428.imfitplus.enums.Category
import java.util.Locale


class CalculateIMCActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalculateImcBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCalculateImcBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

        @Suppress("DEPRECATION")
        var user: User? = intent.getParcelableExtra<User>("USER")

        if (user?.imc == "0,00") user = calculateIMC(user)
        else user



        binding.nameContainer.text = "Nome: ${user?.name}"

        binding.imcContainer.text = "IMC (Indice de mass corporal): ${user?.imc}"

        checkCategory(user?.imc)



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
                view.setTextColor(ContextCompat.getColor(view.context, R.color.blue))
            }
            numericIMC < 24.9 -> {
                view.text = Category.NORMAL_WEIGHT.message
                view.setTextColor(ContextCompat.getColor(view.context, R.color.green))
            }
            numericIMC < 29.9 -> {
                view.text = Category.OVERWEIGHT.message
                view.setTextColor(ContextCompat.getColor(view.context, R.color.orange))
            }
            else -> {
                view.text = Category.OBESITY.message
                view.setTextColor(ContextCompat.getColor(view.context, R.color.red))
            }
        }
    }


}