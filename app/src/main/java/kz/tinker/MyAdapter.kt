package kz.tinker

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.main_list_item.view.*
import kz.tinker.pexel.R

class MyAdapter(private val itemList: ArrayList<Model>, val context: MainActivity):RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(model: Model){
            itemView.textLine1.text=model.name
            Glide.with(itemView).load(model.imageUrl).into(itemView.photo)
            Glide.with(itemView).load(model.userUrl).into(itemView.user)


        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val v = LayoutInflater.from(parent.context).inflate(R.layout.main_list_item,
       parent, false)
           return MyViewHolder(v)
    }

    override fun getItemCount(): Int {
       return itemList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindItems(itemList[position])

        holder.itemView.setOnClickListener {

            val model = itemList.get(position)
            var gTitle: String = model.name
            var gImageView: String = model.imageUrl
            var gUserView: String = model.userUrl
            val intent = Intent(context, ItemDetail::class.java)

            intent.putExtra("title", gTitle)
            intent.putExtra("photo", gImageView)
            intent.putExtra("user", gUserView)
            context.startActivity(intent)

        }
        //holder.aboutAuthor.setOnClickListener() {
       //     val myText = findViewById<TextView>(R.id.textLine1)
       //     myText.setOnClickListener {
           //     val intent = Intent(this, AboutAuthor::class.java)
      //          startActivity(intent)
         //   }


//        }
    }}


