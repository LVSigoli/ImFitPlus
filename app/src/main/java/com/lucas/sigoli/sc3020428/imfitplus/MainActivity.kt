package com.lucas.sigoli.sc3020428.imfitplus

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.lucas.sigoli.sc3020428.imfitplus.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setupToolbar(binding)

        binding.startButton.setOnClickListener { handleContinueClick() }
    }

// Utils
  private fun setupToolbar(binding: ActivityMainBinding){

        setSupportActionBar(binding.toolbar.toolbar)

        supportActionBar?.title= "Boas Vindas"

    }


    // Handlers
    private fun handleContinueClick(){
        val intent = Intent(this, FormActivity::class.java)

        startActivity(intent)
    }

}
