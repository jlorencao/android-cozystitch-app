package com.example.cozystitchapp.ui.home.patterns.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cozystitchapp.R
import com.example.cozystitchapp.model.CrochetPattern

class PatternAdapter : RecyclerView.Adapter<PatternAdapter.MyViewHolder>(){

    private var crochetPatternList = ArrayList<CrochetPattern>()
    var onItemCLick: ((CrochetPattern) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.pattern_list_item,
            parent, false)

        return MyViewHolder(view)
    }

     override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

         val pattern = crochetPatternList[position]

         holder.patternName.text = pattern.name

         if (pattern.imageUrl?.isNotEmpty() == true){
             //get the image from url
         } else {
             holder.patternImage.setImageResource(R.drawable.logo_app)
         }

         holder.itemView.setOnClickListener{
                onItemCLick?.invoke(pattern)
         }

    }

    fun updatePatternList(patternList: List<CrochetPattern>){
        this.crochetPatternList.clear()
        this.crochetPatternList.addAll(patternList)
        notifyDataSetChanged()
    }

    override fun getItemCount() = crochetPatternList.size

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val patternName: TextView = itemView.findViewById<TextView>(R.id.patternNameTextView)
        val patternImage: ImageView = itemView.findViewById<ImageView>(R.id.patternImageView)
    }
}