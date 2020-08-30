package kz.tinker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kz.tinker.pexel.R
import kz.tinker.pexel.data.model.Photo
import kz.tinker.pexel.ui.main.viewholders.CuratedPhotoViewHolder

class PhotosAdapter(private val photoClick: PhotoClickListener) :
    RecyclerView.Adapter<CuratedPhotoViewHolder>() {

    private var itemList: MutableList<Photo> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CuratedPhotoViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(
            R.layout.photo_item,
            parent, false
        )
        return CuratedPhotoViewHolder(v)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun setPhotos(itemList: MutableList<Photo>) {
        this.itemList = itemList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: CuratedPhotoViewHolder, position: Int) {
        holder.bindData(itemList[position],photoClick)
    }


    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onViewRecycled(holder: CuratedPhotoViewHolder) {
        super.onViewRecycled(holder)
        Glide.with(holder.ivPhoto.context)
            .clear(holder.ivPhoto)
    }

    interface PhotoClickListener {
        fun photoClick(position: Int, item: Photo)
    }
}
