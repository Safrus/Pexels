package kz.tinker.pexel.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import kz.tinker.pexel.R
import kz.tinker.pexel.data.model.Photo
import kz.tinker.pexel.ui.main.viewholders.SearchPhotoViewHolder


class SearchedPhotoAdapter :
    ListAdapter<Photo, SearchPhotoViewHolder>(SearchPhotoDiff) {

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


}