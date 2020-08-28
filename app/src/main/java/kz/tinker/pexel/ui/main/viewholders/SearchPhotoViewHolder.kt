package kz.tinker.pexel.ui.main.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kz.tinker.pexel.R
import kz.tinker.pexel.data.model.Photo

class SearchPhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val ivPhoto: ImageView = itemView.findViewById(R.id.photoImageView)
    private val tvPhotographerName: TextView =
        itemView.findViewById(R.id.photographerNameTextView)

    fun bindData(photo: Photo?) {
        photo?.let {
            Glide.with(ivPhoto.context)
                .load(photo.src.large2x)
                .override(1000, 1000)
                .thumbnail(1f)
                .into(ivPhoto)
            tvPhotographerName.text = photo.photographer
        }
    }
}