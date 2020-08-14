package kz.tinker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import kotlinx.android.synthetic.main.activity_item_detail.*
import kotlinx.android.synthetic.main.main_list_item.*
import kz.tinker.pexel.R



class ItemDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)


        val actionBar : ActionBar? =supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        actionBar!!.setDisplayShowHomeEnabled(true)

        var intent = intent
        val  aTitle= intent.getStringExtra("iTitle")
        val aDesctiption= intent.getStringExtra("iDescription")
        val aImageView = intent.getIntExtra("iImageView",0)
          actionBar.setTitle(aTitle)
        a_title.text= aTitle
        a_description.text=aDesctiption


         imageView.setImageResource(aImageView)


    }
}