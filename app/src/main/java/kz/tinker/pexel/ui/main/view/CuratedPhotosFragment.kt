package kz.tinker.pexel.ui.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.curated_photos_fragment.*
import kz.tinker.pexel.R
import kz.tinker.pexel.data.model.Photo
import kz.tinker.pexel.ui.main.adapter.CuratedPhotoAdapter
import kz.tinker.pexel.ui.main.viewmodel.CuratedPhotosViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CuratedPhotosFragment : Fragment(), CuratedPhotoAdapter.PhotoClickListener {

    private val curatedPhotosViewModel by viewModel<CuratedPhotosViewModel>()
    private lateinit var curatedPhotoAdapter: CuratedPhotoAdapter
    private val observer = Observer<List<Photo>> { handleResponse(it) }
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return view ?: inflater.inflate(R.layout.curated_photos_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        initViews()
        curatedPhotosViewModel.curatedPhotosLiveData.observe(viewLifecycleOwner, observer)
        curatedPhotosViewModel.getCuratedPhotos()
        val logo: View = toolbar.getChildAt(0)
        logo.setOnClickListener {
            searchFragmentTransaction(navController)
        }
        searchTextView.setOnClickListener {
            searchFragmentTransaction(navController)
        }
    }

    private fun initViews() {
        curatedPhotoAdapter = CuratedPhotoAdapter(this)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = curatedPhotoAdapter
            recycledViewPool.clear()
        }
    }

    private fun handleResponse(it: List<Photo>) {
        bindData(it)
    }

    private fun bindData(photos: List<Photo>) {
        curatedPhotoAdapter.submitList(photos)
    }

    private fun searchFragmentTransaction(navController: NavController) {
            navController.navigate(R.id.action_curatedPhotosFragment_to_searchPhotosFragment)
    }

    override fun photoClick(position: Int, item: Photo) {
        val action: CuratedPhotosFragmentDirections.ActionCuratedPhotosFragmentToPhotoDetailsFragment =
            CuratedPhotosFragmentDirections.actionCuratedPhotosFragmentToPhotoDetailsFragment(item)
        navController.navigate(action)
    }
}