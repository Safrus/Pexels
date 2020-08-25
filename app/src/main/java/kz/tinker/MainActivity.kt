package kz.tinker

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kz.tinker.pexel.R


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val itemList = ArrayList<Model>()
        itemList.add(Model("Alan Shell", 7, 1))
        itemList.add(Model("Romeo Boll", 8, 2))
        itemList.add(Model("Harry Brown", 9, 3))
        itemList.add(Model("Nikola Smith", 10, 4))
        itemList.add(Model("Georgie Kim", 11, 5))
        itemList.add(Model("Kana Kasteev", 12, 6))
        itemList.add(Model("Alan Shell", 13, 19))
        itemList.add(Model("Romeo Boll", 14, 21))
        itemList.add(Model("Harry Brown", 15, 31))
        itemList.add(Model("Nikola Smith", 16, 41))
        itemList.add(Model("Georgie Kim", 17, 51))
        itemList.add(Model("Kana Kasteev", 18, 61))

        val myAdapter = MyAdapter(itemList, this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = myAdapter

        val decorator = DividerItemDecoration(applicationContext, LinearLayoutManager.VERTICAL)
        decorator.setDrawable(
            ContextCompat.getDrawable(
                applicationContext,
                R.drawable.recyclerline
            )!!
        )
        recyclerView.addItemDecoration(decorator)
    }
}