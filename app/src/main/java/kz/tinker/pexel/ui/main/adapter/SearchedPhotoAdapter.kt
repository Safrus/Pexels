package kz.tinker.pexel.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import kz.tinker.pexel.R
import kz.tinker.pexel.data.model.Photo
import kz.tinker.pexel.ui.main.viewholders.SearchedPhotosViewHolder


class SearchedPhotoAdapter(private val photoClickListener: PhotoClickListener) :
    ListAdapter<Photo, SearchedPhotosViewHolder>(SearchPhotoDiff) {

    private object SearchPhotoDiff : DiffUtil.ItemCallback<Photo>() {

        override fun areItemsTheSame(oldItem: Photo, newItem: Photo) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Photo, newItem: Photo) = oldItem == newItem
    }

    interface PhotoClickListener {
        fun photoClick(position: Int, item: Photo)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchedPhotosViewHolder {
        return SearchedPhotosViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.photo_item, parent, false)
        )
    }

    override fun onBindViewHolder(
        holder: SearchedPhotosViewHolder,
        position: Int
    ) {
        holder.bindData(getItem(position), photoClickListener)
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onViewRecycled(holder: SearchedPhotosViewHolder) {
        super.onViewRecycled(holder)
        Glide.with(holder.ivPhoto.context)
            .clear(holder.ivPhoto)
    }

}