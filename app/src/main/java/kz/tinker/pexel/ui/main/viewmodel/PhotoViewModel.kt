package kz.tinker.pexel.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kz.tinker.pexel.data.model.CuratedPhotos
import kz.tinker.pexel.data.model.Photo
import kz.tinker.pexel.data.repository.PhotoRepository
import kz.tinker.pexel.utils.LoadingState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PhotoViewModel(private val repo: PhotoRepository) : ViewModel(),
    Callback<Photo> {

    private val _loadingState = MutableLiveData<LoadingState>()
    val loadingState: LiveData<LoadingState>
        get() = _loadingState

    private val _data = MutableLiveData<Photo>()
    val data: LiveData<Photo>
        get() = _data

    init {
        fetchData()
    }

    private fun fetchData() {
        _loadingState.postValue(LoadingState.LOADING)
        repo.getPhoto().enqueue(this)
    }

    override fun onFailure(call: Call<Photo>, t: Throwable) {
        _loadingState.postValue(LoadingState.error(t.message))
    }

    override fun onResponse(call: Call<Photo>, response: Response<Photo>) {
        if (response.isSuccessful) {
            _data.postValue(response.body())
            _loadingState.postValue(LoadingState.LOADED)
        } else {
            _loadingState.postValue(LoadingState.error(response.errorBody().toString()))
        }
    }

}