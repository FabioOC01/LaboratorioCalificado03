package com.ochoa.orlando.laboratoriocalificado003

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.content.Context
import android.content.Intent
import android.net.Uri
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ochoa.orlando.laboratoriocalificado003.databinding.ItemTeacherBinding

class TeacherAdapter(private val context: Context, private val teacherList: List<Teacher>) : RecyclerView.Adapter<TeacherAdapter.TeacherViewHolder>() {

    inner class TeacherViewHolder(val binding: ItemTeacherBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherViewHolder {
        val binding = ItemTeacherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TeacherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TeacherViewHolder, position: Int) {
        val teacher = teacherList[position]


        Glide.with(context)
            .load(teacher.imageUrl)
            .apply(RequestOptions().placeholder(R.drawable.avatar))
            .into(holder.binding.imageViewPhoto)


        holder.binding.textViewName.text = teacher.name
        holder.binding.textViewLastName.text = teacher.last_name

        holder.binding.root.setOnClickListener {

            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:${teacher.phone}")
            context.startActivity(intent)
        }


        holder.binding.root.setOnLongClickListener {

            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("mailto:${teacher.email}")
            context.startActivity(intent)
            true
        }
    }

    override fun getItemCount(): Int = teacherList.size
}
