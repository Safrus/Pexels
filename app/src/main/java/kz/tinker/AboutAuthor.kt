package kz.tinker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kz.tinker.pexel.R

class AboutAuthor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_author)


        val myText=findViewById<TextView>(R.id.textLine1)
        myText.setOnClickListener{
            val intent = Intent (this, AboutAuthor::class.java)
            startActivity(intent)
        }
    }
}