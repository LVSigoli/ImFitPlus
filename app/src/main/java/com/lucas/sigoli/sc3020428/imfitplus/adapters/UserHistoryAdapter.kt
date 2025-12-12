package com.lucas.sigoli.sc3020428.imfitplus.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lucas.sigoli.sc3020428.imfitplus.R
import com.lucas.sigoli.sc3020428.imfitplus.dtos.UserHistory
import java.util.Date
import java.util.Locale

class UserHistoryAdapter(
    private val userHistory: List<UserHistory>
) : RecyclerView.Adapter<UserHistoryAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name = view.findViewById<TextView>(R.id.userName)
        val age = view.findViewById<TextView>(R.id.userAge)
        val imc = view.findViewById<TextView>(R.id.userImc)
        val category = view.findViewById<TextView>(R.id.userCategory)
        val extra1 = view.findViewById<TextView>(R.id.userExtra1)
        val calories = view.findViewById<TextView>(R.id.userCalories)
        val water = view.findViewById<TextView>(R.id.userWater)
        val createdAt = view.findViewById<TextView>(R.id.userCreatedAt)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_history_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = userHistory[position]

        val user = item.user

        holder.name.text = "Usuario: ${user.name}"

        holder.age.text = "Idade: ${user.age}"

        holder.imc.text = "IMC: ${user.imc}"

        holder.category.text = "Classificação: ${user.imcCategory}"

        holder.extra1.text = "Peso: ${user.weight} kg • Altura: ${user.height} m"

        holder.calories.text = "Gasto calórico: ${user.baseCalories} kcal"

        holder.water.text = "Indicação de consumo diario de água: ${user.waterConsumption} L"

        holder.createdAt.text = "Criado em: ${formatDate(item.createdAt)}"
    }

    private fun formatDate(timestamp: Long): String {
        val sdf = java.text.SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())

        return sdf.format(Date(timestamp))
    }

    override fun getItemCount(): Int = userHistory.size
}
