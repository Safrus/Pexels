package kz.tinker

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.main_list_item.view.*
import kz.tinker.pexel.R

class MyAdapter (private val itemList: ArrayList<Model>, val context: Context):RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(model: Model){
            itemView.textLine1.text=model.name
            itemView.textLine2.text=model.des
            Glide.with(itemView).load(model.imageUrl).into(itemView.photo)


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

      holder.itemView.setOnClickListener{

          val model = itemList.get(position)
          var gTitle:String = model.name
          var gDescription :String=model.des
        //  var gImageView: Int =model.image
          val intent = Intent(context, ItemDetail::class.java)

          intent.putExtra("title",gTitle)
          intent.putExtra("des",gDescription)
         // intent.putExtra("ggg",gImageView)


          context.startActivity(intent)
      }


}
}


