package kz.tinker.pexel.ui.main.view
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.curated_photos_fragment.*
import kotlinx.android.synthetic.main.searched_photos_fragment.view.*
import kz.tinker.PhotosAdapter
import kz.tinker.pexel.R
import kz.tinker.pexel.data.model.Photo
import kz.tinker.pexel.ui.main.viewmodel.CuratedPhotosViewModel
import org.koin.android.ext.android.inject

class CuratedPhotosFragment : Fragment(), PhotosAdapter.PhotoClickListener {
    private val curatedPhotosViewModel: CuratedPhotosViewModel by inject()
    private var photosList: MutableList<Photo> = mutableListOf()
    private lateinit var adapter: PhotosAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var bundle: Bundle
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.curated_photos_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews(view)

        getCuratedPhotos()
        val logo: View = toolbar.getChildAt(0)
        logo.setOnClickListener {
            searchFragmentTransaction(it)
        }
        searchTextView.setOnClickListener {
            searchFragmentTransaction(it)
        }
    }
    private fun bindViews(view: View) {
        recyclerView = view.recyclerView
        adapter = PhotosAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

    }
    private fun getCuratedPhotos() {
        curatedPhotosViewModel.getCuratedPhotos()
        curatedPhotosViewModel.curatedPhotosLiveData.observe(
            viewLifecycleOwner,
            Observer { result ->
                photosList.addAll(result)
                adapter.setPhotos(photosList)
            })
    }

    override fun photoClick(position: Int, item: Photo) {
    }
    private fun searchFragmentTransaction(it: View) {
        Navigation.findNavController(it)
            .navigate(R.id.action_curatedPhotosFragment_to_searchPhotosFragment)
    }
}