package com.example.homework16

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val api = ApiClient.client.create(ApiInterface::class.java)
        api.getHeroes().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                println(it)

            }, {

                Toast.makeText(this, "Fetch error ${it.message}", Toast.LENGTH_LONG).show() })
    }
}

data class SuperHeroResponse(val heroes: Heroes)
data class Heroes(val hero: List<Hero>)
data class Hero(val name: String, val id: String, val slug: String)
//data class Appear(val gender: String, val race: String)
//data class HeroImages(val xs: String, val sm: String)