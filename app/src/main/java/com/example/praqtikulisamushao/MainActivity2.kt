package com.example.praqtikulisamushao

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val myFun : (starWars) -> Unit = { starWars ->

            var intent = Intent (this, MainActivity3 :: class.java)
            intent.putExtra("characterName",starWars.characterName)
            intent.putExtra("characterAge", starWars.characterAge.toString())
            intent.putExtra("characterPhoto",starWars.characterPhoto)
            intent.putExtra("firstMovieAppearence",starWars.firstMovieAppearence)
            intent.putExtra("characteristic",starWars.characteristic)
            intent.putExtra("actor", starWars.actor)
            startActivity(intent)


        }

        val starWars2 = listOf(
            starWars("Darth Vader", 42, "https://lumiere-a.akamaihd.net/v1/images/Darth-Vader_6bda9114.jpeg?region=0%2C23%2C1400%2C785&width=960", "Star Wars: Episode IV—A New Hope", "antagonist", "Hayden Christensen" ),
            starWars("Luke Skywalker", 21, "https://upload.wikimedia.org/wikipedia/en/9/9b/Luke_Skywalker.png", "Star Wars: Episode IV—A New Hope", "protagonist", "Mark Hamill" ),
            starWars("Princess Leia", 19, "https://upload.wikimedia.org/wikipedia/en/1/1b/Princess_Leia%27s_characteristic_hairstyle.jpg", "Star Wars: Episode IV—A New Hope", "protagonist", "Carrie Fisher" ),
            starWars("Sheev Palpatine", 52, "https://static.wikia.nocookie.net/starwars/images/9/98/Palpatine-TROS-infobox.jpg/revision/latest?cb=20200401080828", "Star Wars: The Empire Strikes Back (Episode V)", "antagonist", "Ian McDiarmid")
        )

        val sharedPreferences = getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE)

        val recyclerViewer = findViewById<RecyclerView>(R.id.recyclerViewer)
        recyclerViewer.layoutManager = LinearLayoutManager(this)
        recyclerViewer.adapter = recyclerViewAdapter(starWars2,myFun,sharedPreferences)

    }

    override fun onResume() {

        super.onResume()

    }

}
