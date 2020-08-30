package kz.tinker.pexel.ui.main.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kz.tinker.pexel.R


class MainActivity : AppCompatActivity() {

    private val curatedPhotosViewModel by viewModel<CuratedPhotosViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        curatedPhotosViewModel.getCuratedPhotos()

        curatedPhotosViewModel.curatedPhotosLiveData.observe(this, Observer {
            var photographersName: String? = null
            val photos = it
            for (photo in photos) {
                photographersName += ", ${photo.photographer}"
            }
        })

    }
}