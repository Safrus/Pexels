package kz.tinker.pexel

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide

class AuthorDetailsFragment : Fragment() {

    private lateinit var ivPhoto: ImageView
    private lateinit var tvUsername: TextView
    private lateinit var ivAvatar: ImageView
    private lateinit var cardView: CardView
    private lateinit var url: String
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.user_details_fragment, container, false)
    }

    override fun setArguments(args: Bundle?) {
        super.setArguments(args)
        if (args != null) {
            url = args.getString(DETAIL_TAG, "")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews(view)
    }

    private fun bindViews(view: View) {
        cardView = view.findViewById(R.id.cardView)
        ivPhoto = view.findViewById(R.id.ivPhoto)
        tvUsername = view.findViewById(R.id.tvUsername)
        ivAvatar = view.findViewById(R.id.ivAvatar)
        Log.d(DETAIL_TAG, url)
        Glide.with(view).load(url).into(ivAvatar)
    }
}