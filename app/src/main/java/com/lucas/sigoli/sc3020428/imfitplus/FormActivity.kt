package com.lucas.sigoli.sc3020428.imfitplus

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.lucas.sigoli.sc3020428.imfitplus.databinding.ActivityFormBinding
import com.lucas.sigoli.sc3020428.imfitplus.databinding.ActivityMainBinding

class FormActivity : AppCompatActivity() {


    private lateinit var binding: ActivityFormBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar(binding)


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

    // Utils
    fun setupToolbar(binding: ActivityFormBinding){
        setSupportActionBar(binding.toolbar.toolbar)

        supportActionBar?.title = "Informações Gerais"

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportActionBar?.setDisplayShowHomeEnabled(true)
    }
}
