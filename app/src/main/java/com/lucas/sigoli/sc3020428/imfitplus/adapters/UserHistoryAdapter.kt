package com.lucas.sigoli.sc3020428.imfitplus.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lucas.sigoli.sc3020428.imfitplus.R
import com.lucas.sigoli.sc3020428.imfitplus.dtos.UserHistory

class UserHistoryAdapter(
    private val userHistory: List<UserHistory>
) : RecyclerView.Adapter<UserHistoryAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.userName)
        val imc: TextView = view.findViewById(R.id.userImc)
        val category: TextView = view.findViewById(R.id.userCategory)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_history_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val historyItem = userHistory[position]
        holder.name.text = "Nome: ${historyItem.user.name}"
        holder.imc.text = "IMC: ${historyItem.user.imc}"
        holder.category.text = "Categoria: ${historyItem.user.imcCategory}"
    }

    override fun getItemCount(): Int = userHistory.size
}
