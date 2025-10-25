package com.lucas.sigoli.sc3020428.imfitplus

// External libraries
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.lucas.sigoli.sc3020428.imfitplus.databinding.ActivityCalculateImcBinding
import com.lucas.sigoli.sc3020428.imfitplus.dtos.User
import com.lucas.sigoli.sc3020428.imfitplus.utils.showToast
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


        val message = user.toString()
        binding.textViewResult.text = "IMC: ${message}"
    }


    private fun calculateIMC(user: User): User {
        val imc = user.weight / (user.height * user.height)

        val formatedImc = String.format(Locale.US, "%.2f", imc)

        return user.copy(imc = formatedImc)
    }
}