package com.lucas.sigoli.sc3020428.imfitplus

// External Libraries
import android.os.Bundle
import android.content.Intent

// Types
import androidx.appcompat.app.AppCompatActivity
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

    private fun handleContinueClick() {
        val intent = Intent(this, FormActivity::class.java)

        startActivity(intent)
    }

    private fun setupToolbar(binding: ActivityMainBinding) {
        setSupportActionBar(binding.toolbar.toolbar)

        supportActionBar?.title = "Boas Vindas"
    }
}
