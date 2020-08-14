package kz.tinker

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kz.tinker.pexel.R


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val  itemList = ArrayList<Model>()

        itemList.add(Model("news", "this is description",1))
        itemList.add(Model("lll", "this is description", 2))
        itemList.add(Model("njjks", "this is description",3))
        itemList.add(Model("nnnn", "this is description",4))
        itemList.add(Model("nn", "this is description", 5))
        itemList.add(Model("nes", "this is description", 6))

        val myAdapter= MyAdapter(itemList,this)


        recyclerView.layoutManager=LinearLayoutManager(this)
        recyclerView.adapter= myAdapter



        Toast.makeText(this,"welcomeUser",Toast.LENGTH_SHORT).show()

    }
}