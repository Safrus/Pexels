package kz.tinker.pexel.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kz.tinker.pexel.R
import kz.tinker.pexel.data.model.Photo


class SearchedPhotoAdapter :
    ListAdapter<Photo, SearchedPhotoAdapter.SearchPhotoViewHolder>(SearchPhotoDiff) {

    private object SearchPhotoDiff : DiffUtil.ItemCallback<Photo>() {

        override fun areItemsTheSame(oldItem: Photo, newItem: Photo) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Photo, newItem: Photo) = oldItem == newItem
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchPhotoViewHolder {
        return SearchPhotoViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.photo_item, parent, false)
        )
    }

    override fun onBindViewHolder(
        holder: SearchPhotoViewHolder,
        position: Int
    ) {
        holder.bindData(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    class SearchPhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivPhoto: ImageView = itemView.findViewById(R.id.photoImageView)
        private val tvPhotographerName: TextView =
            itemView.findViewById(R.id.photographerNameTextView)

        fun bindData(photo: Photo?) {
            photo?.apply {
                Glide.with(ivPhoto.context)
                    .load(photo.src.large2x)
                    .override(1000, 1000)
                    .thumbnail(1f)
                    .into(ivPhoto)
                tvPhotographerName.text = photo.photographer
            }
        }
    }
}