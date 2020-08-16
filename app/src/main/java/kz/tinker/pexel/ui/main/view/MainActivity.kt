package kz.tinker.pexel.ui.main.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*
import kz.tinker.pexel.R
import kz.tinker.pexel.ui.main.viewmodel.CuratedPhotosViewModel
import kz.tinker.pexel.ui.main.viewmodel.PhotoViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val curatedPhotosViewModel by viewModel<CuratedPhotosViewModel>()
    private val photoViewModel by viewModel<PhotoViewModel>()

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
            textView.text = photographersName
        })

        /*photoViewModel.data.observe(this, Observer {
            textView.text = it.photographer
        })*/
    }
}