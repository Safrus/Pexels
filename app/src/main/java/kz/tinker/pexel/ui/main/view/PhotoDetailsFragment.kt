package kz.tinker.pexel.ui.main.view

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.photo_details_fragment.*
import kz.tinker.pexel.R
import kz.tinker.pexel.data.model.Photo


class PhotoDetailsFragment : Fragment() {

    private lateinit var photo: Photo

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return view ?: inflater.inflate(R.layout.photo_details_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments != null) {
            val args: PhotoDetailsFragmentArgs =
                PhotoDetailsFragmentArgs.fromBundle(requireArguments())
            photo = args.photo
        }
        Glide.with(photoImageView.context)
            .load(photo.src.large2x)
            .centerInside()
            .override(1000, 1000)
            .thumbnail(0.5f)
            .into(photoImageView)
        photographerTextView.text = photo.photographer

        originalPhotoDownloadButton.setOnClickListener {
            if (checkingPermission()) {
                downloadPhoto(photo.src.original)
            }
        }

        largePhotoDownloadButton.setOnClickListener {
            if (checkingPermission()) {
                downloadPhoto(photo.src.large)
            }
        }

        mediumPhotoDownloadButton.setOnClickListener {
            if (checkingPermission()) {
                downloadPhoto(photo.src.medium)
            }
        }

        smallPhotoDownloadButton.setOnClickListener {
            if (checkingPermission()) {
                downloadPhoto(photo.src.small)
            }
        }
    }

    private fun checkingPermission(): Boolean {
        val sp: SharedPreferences =
            requireActivity().getSharedPreferences("checkbox", Context.MODE_PRIVATE)
        return sp.getBoolean("access", false)
    }

    @SuppressLint("ObsoleteSdkInt")
    private fun downloadPhoto(format: String) {
        val url = format
        val id = photo.id
        val request = DownloadManager.Request(Uri.parse(url))
        request.setDescription("Pexels")
        request.setTitle("Pexels Photo")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        }
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "$id.jpeg")
        val manager = activity?.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        manager.enqueue(request)
    }
}