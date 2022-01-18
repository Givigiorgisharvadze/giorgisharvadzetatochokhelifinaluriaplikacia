package com.example.praqtikulisamushao

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentAuthorization = fragmentAuthorization()

        val fm : FragmentManager = supportFragmentManager

        fm.beginTransaction().add(R.id.layoutMain,fragmentAuthorization).commit()

    }

}