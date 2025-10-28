package com.lucas.sigoli.sc3020428.imfitplus

// External Libraries
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lucas.sigoli.sc3020428.imfitplus.databinding.ActivityCalculateCaloriesSpentBinding

// Types
import com.lucas.sigoli.sc3020428.imfitplus.databinding.ActivityIdealWeightBinding
import com.lucas.sigoli.sc3020428.imfitplus.dtos.User

class IdealWeightActivity : AppCompatActivity() {

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

        if(user?.idealWeight == "0,00") {}
        else user
    }

    fun setupToolbar(binding: ActivityIdealWeightBinding) {
        setSupportActionBar(binding.toolbar.toolbar)

        supportActionBar?.title = "Peso ideal"

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    fun calculateIdealWeight(user:User){}

}