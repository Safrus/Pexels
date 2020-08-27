package kz.tinker

import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kz.tinker.pexel.AuthorDetailsFragment
import kz.tinker.pexel.PhotoListFragment
import kz.tinker.pexel.R
import kz.tinker.pexel.data.model.Photo
import kz.tinker.pexel.ui.main.viewmodel.CuratedPhotosViewModel
import org.koin.android.ext.android.inject


private const val TAG = "main_activity"

class MainActivity : AppCompatActivity() {

    private var photoListFragment: PhotoListFragment = PhotoListFragment()
    private var fragmentManager: FragmentManager = supportFragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addingFragment()
        showMainFragment()
    }

    private fun addingFragment() {
        fragmentManager.beginTransaction().add(R.id.frame, photoListFragment).commit()
    }

    private fun showMainFragment() {
        fragmentManager.beginTransaction().show(photoListFragment).commit()
    }
}