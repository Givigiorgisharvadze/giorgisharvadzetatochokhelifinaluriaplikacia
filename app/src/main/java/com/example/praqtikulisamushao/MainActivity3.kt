package com.example.praqtikulisamushao

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        var characterName = findViewById<TextView>(R.id.characterName)
        var characterAge = findViewById<TextView>(R.id.characterAge)
        var firstMovieAppearence = findViewById<TextView>(R.id.firstMovieAppearence)
        var characteristic = findViewById<TextView>(R.id.characteristic)
        var actor = findViewById<TextView>(R.id.actor)
        var characterPhoto = findViewById<ImageView>(R.id.characterPhoto)
        var button=findViewById<Button>(R.id.button)

        characterName.text = intent.getStringExtra("characterName")
        characterAge.text = intent.getStringExtra("characterAge")
        firstMovieAppearence.text = intent.getStringExtra("firstMovieAppearence")
        characteristic.text = intent.getStringExtra("characteristic")
        actor.text = intent.getStringExtra("actor")
        Picasso.get().load(intent.getStringExtra("characterPhoto")).into(characterPhoto)
        val valSharedPreferences = getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE)
        var varSharedPreferences =valSharedPreferences.getBoolean("PrefKey",false)
        varSharedPreferences = true
        valSharedPreferences.edit().putBoolean(intent.getStringExtra("characterName"),varSharedPreferences).apply()
        button.setOnClickListener {
            var intent1= Intent(this,MainActivity2::class.java)
            intent1.putExtra("identifier",characterName.text)
            startActivity(intent1)
        }

    }
}