package kz.tinker.pexel.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kz.tinker.pexel.data.model.Photo
import kz.tinker.pexel.data.repository.SearchedPhotosRepositoryImpl

class SearchedPhotosViewModel(private val searchedPhotosRepositoryImpl: SearchedPhotosRepositoryImpl) :
    ViewModel() {
    private val mutableSearchedPhotosLiveData = MutableLiveData<List<Photo>>()
    private var getSearchedPhotosJob: Job? = null

    val searchedPhotosLiveData: LiveData<List<Photo>> = mutableSearchedPhotosLiveData

    fun getSearchedPhotos(query: String) {
        getSearchedPhotosJob = viewModelScope.launch {
            searchedPhotosRepositoryImpl.getSearchedPhotos(query, 50).collect {
                mutableSearchedPhotosLiveData.value = it as List<Photo>?
            }
        }
    }
}