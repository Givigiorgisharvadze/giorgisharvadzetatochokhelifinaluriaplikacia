package com.example.praqtikulisamushao

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class fragmentPasswordRecovery : Fragment() {

    private lateinit var et1NewPass: EditText
    private lateinit var tvAuth2: TextView
    private lateinit var tvReg2: TextView
    private lateinit var tvPassChange2: TextView
    private lateinit var btnSave1: Button
    private lateinit var Auth1: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_password_recovery, container, false)

        val et1NewPass = v.findViewById<EditText>(R.id.et1NewPass)

        val tvAuth2 = v.findViewById<TextView>(R.id.tvAuth2)

        val tvReg2 = v.findViewById<TextView>(R.id.tvReg2)

        val tvPassChange2 = v.findViewById<TextView>(R.id.tvPassChange2)

        val btnSave1 = v.findViewById<Button>(R.id.btnSave1)

        btnSave1.setOnClickListener {

            val et1NewPassText = et1NewPass.text.toString().trim()

            if (android.util.Patterns.EMAIL_ADDRESS.matcher(et1NewPassText).matches()) {
                Auth1.sendPasswordResetEmail(et1NewPassText).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(activity,
                            "შეცვლის მოთხოვნა გამოიგზავნა მეილზე",
                            Toast.LENGTH_SHORT).show()
                        val fragmentAuthorization = fragmentAuthorization()
                        fragmentManager?.beginTransaction()
                            ?.replace(R.id.layoutMain, fragmentAuthorization)?.commit()
                    } else {
                        Toast.makeText(activity, "არასწორი მეილია", Toast.LENGTH_SHORT).show()

                    }

                }

            }
        }

        tvReg2.setOnClickListener {

            val fragmentRegistration = fragmentRegistration()
            fragmentManager?.beginTransaction()?.replace(R.id.layoutMain,fragmentRegistration)?.commit()

        }

        tvAuth2.setOnClickListener {

            val fragmentAuthorization = fragmentAuthorization()
            fragmentManager?.beginTransaction()?.replace(R.id.layoutMain,fragmentAuthorization)?.commit()

        }

        tvPassChange2.setOnClickListener {

            val fragmentChangePassword = fragmentChangePassword()
            fragmentManager?.beginTransaction()?.replace(R.id.layoutMain,fragmentChangePassword)?.commit()

        }
        return v
    }
}

        