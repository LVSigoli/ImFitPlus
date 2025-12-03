package com.lucas.sigoli.sc3020428.imfitplus

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.lucas.sigoli.sc3020428.imfitplus.database.repositories.UserRepository
import com.lucas.sigoli.sc3020428.imfitplus.databinding.ActivityHistoryBinding
import com.lucas.sigoli.sc3020428.imfitplus.dtos.User
import com.lucas.sigoli.sc3020428.imfitplus.dtos.UserHistory


class HistoryActivity: AppCompatActivity() {
    private lateinit var binding: ActivityHistoryBinding
    private var userHistory: List<UserHistory> = emptyList()

    private lateinit var userRepository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        binding = ActivityHistoryBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setupToolbar(binding)

        userRepository = UserRepository(this)
    }

    @SuppressLint("SetTextI18n")
    override fun onStart(){
        super.onStart()

        val user: User? = intent.getParcelableExtra<User>("USER")


        if (user != null) {
            userHistory = userRepository.getUserHistory(user.name)


            binding.test.text = "Histórico de ${user.name} (${userHistory.size} registros)"
        }

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