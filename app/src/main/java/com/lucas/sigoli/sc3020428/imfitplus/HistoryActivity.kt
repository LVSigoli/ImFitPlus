package com.lucas.sigoli.sc3020428.imfitplus

// External Libraries
import android.os.Bundle
import android.view.MenuItem
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager

// Services
import com.lucas.sigoli.sc3020428.imfitplus.adapters.UserHistoryAdapter

// Utils
import com.lucas.sigoli.sc3020428.imfitplus.database.repositories.UserRepository

// Types
import com.lucas.sigoli.sc3020428.imfitplus.dtos.User
import com.lucas.sigoli.sc3020428.imfitplus.dtos.UserHistory
import com.lucas.sigoli.sc3020428.imfitplus.databinding.ActivityHistoryBinding


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

        binding.btnEnd.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or
                    Intent.FLAG_ACTIVITY_NEW_TASK or
                    Intent.FLAG_ACTIVITY_CLEAR_TASK

            startActivity(intent)
            finish()
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onStart(){
        super.onStart()

        val user: User? = intent.getParcelableExtra<User>("USER")

        if (user != null) {
            userHistory = userRepository.getUserHistory(user.name)

            binding.recyclerHistory.apply {
                layoutManager = LinearLayoutManager(this@HistoryActivity)
                adapter = UserHistoryAdapter(userHistory)
            }
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