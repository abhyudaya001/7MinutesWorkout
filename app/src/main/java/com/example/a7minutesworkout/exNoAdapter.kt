package com.example.a7minutesworkout

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.a7minutesworkout.databinding.ActivityExerciseBinding
import com.example.a7minutesworkout.databinding.ActivityMainBinding
import com.example.a7minutesworkout.databinding.ItemExerciseStatusBinding

class exNoAdapter(val exNo:ArrayList<ExerciseModel>):RecyclerView.Adapter<exNoAdapter.ViewHolder>() {
    class ViewHolder(binding: ItemExerciseStatusBinding):
    RecyclerView.ViewHolder(binding.root){
        val item=binding.tvItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemExerciseStatusBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model:ExerciseModel =exNo[position]
        holder.item.text=model.getId().toString()
        when{
            model.getIsSelected()->{
                holder.item.background=ContextCompat.getDrawable(holder.itemView.context,R.drawable.circle_current)
                holder.item.setTextColor(Color.parseColor("#212121"))
            }
            model.getIsComplete()->{
                holder.item.background=ContextCompat.getDrawable(holder.itemView.context,R.drawable.circle_completed)
                holder.item.setTextColor(Color.parseColor("#FFFFFF"))
            }
            else->{
                holder.item.background=ContextCompat.getDrawable(holder.itemView.context,R.drawable.circle)
                holder.item.setTextColor(Color.parseColor("#212121"))
            }
        }
    }

    override fun getItemCount(): Int {
        return exNo.size
    }

}