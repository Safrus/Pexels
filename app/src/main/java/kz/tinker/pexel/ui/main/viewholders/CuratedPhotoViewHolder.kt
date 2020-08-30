package kz.tinker.pexel.ui.main.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.photo_item.view.*
import kz.tinker.PhotosAdapter
import kz.tinker.pexel.data.model.Photo

class CuratedPhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val ivPhoto: ImageView = itemView.photoImageView
    private val tvPhotographerName: TextView =
        itemView.photographerNameTextView

    fun bindData(photo: Photo?,photoClickListener: PhotosAdapter.PhotoClickListener) {
        photo?.let {

            Glide.with(ivPhoto.context)
                .load(photo.src.large2x)
                .centerInside()
                .override(1000, 1000)
                .thumbnail(0.5f)
                .into(ivPhoto)
            tvPhotographerName.text = photo.photographer
            itemView.setOnClickListener {
                photoClickListener.photoClick(adapterPosition, photo)
            }
        }

    }
}