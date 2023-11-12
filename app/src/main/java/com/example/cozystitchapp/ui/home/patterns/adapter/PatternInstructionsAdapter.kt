package com.example.cozystitchapp.ui.home.patterns.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cozystitchapp.R


class PatternInstructionsAdapter(instructions: List<String>) : RecyclerView.Adapter<PatternInstructionsAdapter.InstructionsViewHolder>(){

    private var instructionsList : List<String> = instructions

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstructionsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.pattern_row_item,
            parent, false)

        return InstructionsViewHolder(view)
    }

    override fun onBindViewHolder(holder: InstructionsViewHolder, position: Int) {

        val row = instructionsList[position]

        holder.patternInstructionsRow.text = row

    }
    override fun getItemCount() = instructionsList.size

    class InstructionsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val patternInstructionsRow: TextView = itemView.findViewById<TextView>(R.id.rowTextView)

    }
}