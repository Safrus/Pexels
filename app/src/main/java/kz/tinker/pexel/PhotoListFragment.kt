package kz.tinker.pexel

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kz.tinker.MyAdapter
import kz.tinker.pexel.data.model.Photo
import kz.tinker.pexel.ui.main.viewmodel.CuratedPhotosViewModel
import org.koin.android.ext.android.inject
import org.koin.core.logger.KOIN_TAG

class PhotoListFragment : Fragment(), MyAdapter.PhotoClickListener {

    private val curatedPhotosViewModel: CuratedPhotosViewModel by inject()
    private var photosList: MutableList<Photo> = mutableListOf()
    private lateinit var authorDetailsFragment: AuthorDetailsFragment
    private lateinit var adapter: MyAdapter
    private lateinit var transaction: FragmentTransaction
    private lateinit var decorator: DividerItemDecoration
    private lateinit var recyclerView: RecyclerView
    private lateinit var bundle: Bundle
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.photo_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews(view)
        setAdapter()
        getCuratedPhotos()
    }

    private fun bindViews(view: View) {
        recyclerView = view.findViewById(R.id.recyclerView)
        adapter = MyAdapter(this)
        bundle = Bundle()
        decorator = DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        ContextCompat.getDrawable(
            requireContext(),
            R.drawable.recyclerline
        )?.let {
            decorator.setDrawable(
                it
            )
        }
    }

    private fun setAdapter() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(decorator)
    }

    private fun getCuratedPhotos() {
        curatedPhotosViewModel.getCuratedPhotos()
        curatedPhotosViewModel.curatedPhotosLiveData.observe(
            viewLifecycleOwner,
            Observer { result ->
                photosList.addAll(result)
                adapter.setUsers(photosList)
            })
    }

    override fun photoClick(position: Int, item: Photo) {
        authorDetailsFragment = AuthorDetailsFragment()
        bundle.putString(DETAIL_TAG, item.src.landscape)
        addingFragment(authorDetailsFragment)
        showFragment(authorDetailsFragment)
        authorDetailsFragment.arguments = bundle
    }

    private fun addingFragment(fragment: Fragment) {
        transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.add(R.id.frame, fragment).commit()
    }

    private fun showFragment(fragment: Fragment) {
        transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.show(fragment).commit()
    }

}