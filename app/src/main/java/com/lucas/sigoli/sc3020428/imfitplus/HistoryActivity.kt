package com.lucas.sigoli.sc3020428.imfitplus

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.lucas.sigoli.sc3020428.imfitplus.databinding.ActivityHistoryBinding


class HistoryActivity: AppCompatActivity() {
    private lateinit var binding: ActivityHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        binding = ActivityHistoryBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setContentView(binding.root)

        setupToolbar(binding)

    }

    @SuppressLint("SetTextI18n")
    override fun onStart(){
        super.onStart()

    }


    // Utils
    private fun setupToolbar(binding: ActivityHistoryBinding) {
        setSupportActionBar(binding.toolbar.toolbar)

        supportActionBar?.title = "Histórico"

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

}