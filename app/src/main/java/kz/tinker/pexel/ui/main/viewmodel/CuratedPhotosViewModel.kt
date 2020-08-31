package kz.tinker.pexel.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kz.tinker.pexel.data.model.Photo
import kz.tinker.pexel.data.repository.CuratedPhotosRepositoryImpl

class CuratedPhotosViewModel(private val curatedPhotosRepositoryImpl: CuratedPhotosRepositoryImpl) :
    ViewModel() {

    private val mutableCuratedPhotosLiveData = MutableLiveData<List<Photo>>()
    private var getCuratedPhotosJob: Job? = null

    val curatedPhotosLiveData: LiveData<List<Photo>> = mutableCuratedPhotosLiveData

    fun getCuratedPhotos() {
        getCuratedPhotosJob = viewModelScope.launch {
            curatedPhotosRepositoryImpl.getCuratedPhotos(80, 1).collect {
                mutableCuratedPhotosLiveData.value = it as List<Photo>?
            }
        }
    }
}