package kz.tinker.pexel.ui.main.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kz.tinker.pexel.R
import kz.tinker.pexel.data.model.Photo
import kz.tinker.pexel.ui.main.adapter.CuratedPhotoAdapter

class CuratedPhotosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val ivPhoto: ImageView = itemView.findViewById(R.id.photoImageView)
    private val tvPhotographerName: TextView =
        itemView.findViewById(R.id.photographerNameTextView)

    fun bindData(photo: Photo?, photoClickListener: CuratedPhotoAdapter.PhotoClickListener) {
        photo?.let {
            Glide.with(ivPhoto.context)
                .load(photo.src.large2x)
                .centerInside()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .override(1000, 1000)
                .thumbnail(0.8f)
                .into(ivPhoto)
            tvPhotographerName.text = photo.photographer
            itemView.setOnClickListener {
                photoClickListener.photoClick(adapterPosition, photo)
            }
        }
    }
}