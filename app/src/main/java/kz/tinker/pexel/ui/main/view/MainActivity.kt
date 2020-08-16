package kz.tinker.pexel.ui.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kz.tinker.pexel.R
import kz.tinker.pexel.data.model.Src
import kz.tinker.pexel.ui.main.viewmodel.PhotoViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val curatedPhotosViewModel by viewModel<PhotoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        curatedPhotosViewModel.data.observe(this, Observer {
            textView.text = it.photographer
            val photoSrc: Src = it.src
            Picasso.get()
                .load(photoSrc.original)
                .into(imageView)
        })

        curatedPhotosViewModel.loadingState.observe(this, Observer {
            // Observe the loading state
        })
    }
}