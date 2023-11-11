package com.example.cozystitchapp.ui.home.projects.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cozystitchapp.R

import com.example.cozystitchapp.model.Project

class ProjectListAdapter : RecyclerView.Adapter<ProjectListAdapter.MyViewHolder>(){

    private var projectList = ArrayList<Project>()
    var onItemCLick: ((Project) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.project_list_item,
            parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val project = projectList[position]

        holder.projectName.text = project.title
        holder.projectImage.setImageResource(R.drawable.logo_app)
        //get image from url
        holder.projectStatus.text = project.status.toString()

        holder.itemView.setOnClickListener{
            onItemCLick?.invoke(project)
        }
    }

    fun updateProjectList(projectList : List<Project>){
        this.projectList.clear()
        this.projectList.addAll(projectList)
        notifyDataSetChanged()
    }

    override fun getItemCount() = projectList.size

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val projectName: TextView = itemView.findViewById<TextView>(R.id.projectNameTextView)
        val projectImage: ImageView = itemView.findViewById<ImageView>(R.id.projectImageView)
        val projectStatus: TextView = itemView.findViewById<TextView>(R.id.statusPercentageTextView)

    }
}