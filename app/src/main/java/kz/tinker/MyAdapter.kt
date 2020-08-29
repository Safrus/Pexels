package kz.tinker
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.main_list_item.view.*
import kz.tinker.pexel.R
import kz.tinker.pexel.data.model.Photo

class MyAdapter(private val photoClick: PhotoClickListener) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private var itemList: MutableList<Photo> = mutableListOf()

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

    fun setPhoto(itemList: MutableList<Photo>) {
        this.itemList = itemList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindItems(itemList[position])
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(photo: Photo) {
            val tvUsername = itemView.tvUsername
            val ivPoster = itemView.ivPoster
            val ivAvatar = itemView.ivAvatar
            tvUsername.text = photo.photographer
            Glide.with(itemView).load(photo.src.landscape).into(ivPoster)
            Glide.with(itemView).load(photo.src.landscape).into(ivAvatar)
            itemView.setOnClickListener {
                photoClick.photoClick(adapterPosition, photo)
            }
        }
    }

    interface PhotoClickListener {
        fun photoClick(position: Int, item: Photo)
    }
}


