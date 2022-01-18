package com.example.praqtikulisamushao

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class fragmentChangePassword : Fragment() {
    private lateinit var etNewPass : EditText
    private lateinit var etRepNewPass : EditText
    private lateinit var tvReg1 : TextView
    private lateinit var tvAuth1 : TextView
    private lateinit var tvPassRec2 : TextView
    private lateinit var btnSave : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_change_password, container, false)

        val etNewPass = v.findViewById<EditText>(R.id.etNewPass)

        val etRepNewPass = v.findViewById<EditText>(R.id.etRepNewPass)

        val tvReg1 = v.findViewById<TextView>(R.id.tvReg1)

        val tvAuth1 = v.findViewById<TextView>(R.id.tvAuth1)

        val tvPassRec2 = v.findViewById<TextView>(R.id.tvPassRec2)

        val btnSave = v.findViewById<Button>(R.id.btnSave)

        btnSave.setOnClickListener{

            if(etNewPass.text.isEmpty() or etRepNewPass.text.isEmpty()) {

                Toast.makeText(activity,"შეავსეთ ცარიელი ველი", Toast.LENGTH_SHORT).show()

            }

            else if(etNewPass.text.toString() != etRepNewPass.text.toString()) {

                Toast.makeText(activity,"პაროლები უნდა ემთხვეოდეს", Toast.LENGTH_SHORT).show()

            }

            else if(etNewPass.text.length < 8) {

                Toast.makeText(activity,"პაროლი უნდა შეიცავდეს 8ზე მეტ სიმბოლოს", Toast.LENGTH_SHORT).show()

            }

            else if(etRepNewPass.text.length < 8) {

                Toast.makeText(activity,"პაროლი უნდა შეიცავდეს 8ზე მეტ სიმბოლოს", Toast.LENGTH_SHORT).show()

            }

            else {

                etNewPass.text.clear()
                etRepNewPass.text.clear()
                Toast.makeText(activity,"პაროლი წარმატებით შეიცვალა", Toast.LENGTH_SHORT).show()
                val fragmentAuthorization = fragmentAuthorization()
                fragmentManager?.beginTransaction()?.replace(R.id.layoutMain,fragmentAuthorization)?.commit()

            }

        }

        tvReg1.setOnClickListener{

            val fragmentRegistration = fragmentRegistration()
            fragmentManager?.beginTransaction()?.replace(R.id.layoutMain,fragmentRegistration)?.commit()

        }

        tvAuth1.setOnClickListener{

            val fragmentAuthorization = fragmentAuthorization()
            fragmentManager?.beginTransaction()?.replace(R.id.layoutMain,fragmentAuthorization)?.commit()

        }

        tvPassRec2.setOnClickListener{

            val fragmentPasswordRecovery = fragmentPasswordRecovery()
            fragmentManager?.beginTransaction()?.replace(R.id.layoutMain,fragmentPasswordRecovery)?.commit()

        }

        return v
    }
}