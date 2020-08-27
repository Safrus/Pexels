package kz.tinker

import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.main_list_item.view.*
import kz.tinker.pexel.R

class MyAdapter(private val itemList: ArrayList<Model>, val context: MainActivity) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(model: Model) {
            itemView.textLine1.text = model.name
            Glide.with(itemView).load(model.imageUrl).into(itemView.photo)
            Glide.with(itemView).load(model.userUrl).into(itemView.user)


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(
            R.layout.main_list_item,
            parent, false
        )
        return MyViewHolder(v)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindItems(itemList[position])
        holder.itemView.setOnClickListener {
            val model = itemList.get(position)
            val gTitle: String = model.name
            val gImageView: Int = model.user
            val gUserView: Int = model.age
            val intent = Intent(context, ItemDetail::class.java)
            intent.putExtra("aTitle", gTitle)
            intent.putExtra("aImage", gImageView)
            intent.putExtra("aUser", gUserView)
            context.startActivity(intent)
        }
    }


}


