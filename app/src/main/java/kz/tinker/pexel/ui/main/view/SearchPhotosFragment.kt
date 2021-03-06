package kz.tinker.pexel.ui.main.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.searched_photos_fragment.*
import kz.tinker.pexel.R
import kz.tinker.pexel.data.model.Photo
import kz.tinker.pexel.ui.main.adapter.SearchedPhotoAdapter
import kz.tinker.pexel.ui.main.viewmodel.SearchedPhotosViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class SearchPhotosFragment : Fragment(), SearchedPhotoAdapter.PhotoClickListener {

    private val searchedPhotosViewModel by viewModel<SearchedPhotosViewModel>()
    private lateinit var searchedPhotoAdapter: SearchedPhotoAdapter
    private val observer = Observer<List<Photo>> { handleResponse(it) }
    private lateinit var navController: NavController
    private var opened: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return view ?: inflater.inflate(R.layout.searched_photos_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        initViews()
        searchedPhotosViewModel.searchedPhotosLiveData.observe(viewLifecycleOwner, observer)
        val logo: View = toolbar.getChildAt(0)
        logo.setOnClickListener {
            searchPhotos()
        }
        searchEditText.setOnEditorActionListener { _, actionId, _ ->
            var handled = false
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                searchPhotos()
                handled = true
            }
            handled
        }
    }

    override fun onResume() {
        super.onResume()
        searchEditText.requestFocus()
        if (!opened) {
            (activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).showSoftInput(
                searchEditText,
                InputMethodManager.SHOW_IMPLICIT
            )
            opened = true
        }
    }

    private fun initViews() {
        searchedPhotoAdapter = SearchedPhotoAdapter(this)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = searchedPhotoAdapter
            recycledViewPool.clear()
        }
    }

    private fun searchPhotos() {
        if (searchEditText.text.toString().trim() != "") {
            searchedPhotosViewModel.getSearchedPhotos(searchEditText.text.toString())
        } else {
            val toast = Toast.makeText(
                context,
                "Type to search",
                Toast.LENGTH_SHORT
            )
            toast.show()
        }
    }

    private fun handleResponse(it: List<Photo>) {
        bindData(it)
    }

    private fun bindData(photos: List<Photo>) {
        searchedPhotoAdapter.submitList(photos)
    }

    override fun photoClick(position: Int, item: Photo) {
        val action: SearchPhotosFragmentDirections.ActionSearchPhotosFragmentToPhotoDetailsFragment =
            SearchPhotosFragmentDirections.actionSearchPhotosFragmentToPhotoDetailsFragment(item)
        navController.navigate(action)
    }
}