package kz.tinker.pexel.ui.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.curated_photos_fragment.*
import kz.tinker.pexel.R

class CuratedPhotosFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return view ?: inflater.inflate(R.layout.curated_photos_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val logo: View = toolbar.getChildAt(0)
        logo.setOnClickListener {
            searchFragmentTransaction()
        }
        searchTextView.setOnClickListener {
            searchFragmentTransaction()
        }
    }

    private fun searchFragmentTransaction() {
        val fragment: Fragment?
        fragment = SearchPhotosFragment()
        val fragmentManager: FragmentManager? = activity?.supportFragmentManager
        fragmentManager?.beginTransaction()?.replace(R.id.frame_layout, fragment)
            ?.addToBackStack(null)
            ?.commit()
    }
}