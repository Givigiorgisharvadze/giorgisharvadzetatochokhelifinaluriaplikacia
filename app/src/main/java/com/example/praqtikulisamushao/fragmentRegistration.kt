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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserInfo
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.*

class fragmentRegistration : Fragment() {
    private lateinit var etName : EditText
    private lateinit var etSurName : EditText
    private lateinit var etEmail1 : EditText
    private lateinit var etPass1 : EditText
    private lateinit var tvAuth : TextView
    private lateinit var tvPassRec1 : TextView
    private lateinit var tvPassChange1 : TextView
    private lateinit var btnReg : Button
    private val firebaseauth = FirebaseAuth.getInstance()
    private val dataBaseUserInfo: DatabaseReference = FirebaseDatabase.getInstance().getReference("UserInfo")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_registration, container, false)

        val etName = v.findViewById<EditText>(R.id.etName)

        val etSurName = v.findViewById<EditText>(R.id.etSurName)

        val etEmail1 = v.findViewById<EditText>(R.id.etEmail1)

        val etPass1 = v.findViewById<EditText>(R.id.etPass1)

        val tvAuth = v.findViewById<TextView>(R.id.tvAuth)

        val tvPassRec1 = v.findViewById<TextView>(R.id.tvPassRec1)

        val tvPassChange1 = v.findViewById<TextView>(R.id.tvPassChange1)

        val btnReg = v.findViewById<Button>(R.id.btnReg)

        btnReg.setOnClickListener {

            val etEmail1Text = etEmail1.text.toString().trim()

            val etPass1Text = etPass1.text.toString().trim()

            val et1Name = etName.text.toString().trim()

            val et1surName = etSurName.text.toString().trim()

            if(etName.text.isEmpty() or etSurName.text.isEmpty() or etEmail1.text.isEmpty() or etPass1.text.isEmpty()) {

                Toast.makeText(activity,"შეავსეთ ყველა ველი", Toast.LENGTH_SHORT).show()

            }
            else if(etName.text.length < 2) {

                Toast.makeText(activity,"სახელი უნდა შეიცავდეს 2ზე მეტ სიმბოლოს", Toast.LENGTH_SHORT).show()

            }

            else if(etSurName.text.length < 5) {

                Toast.makeText(activity,"გვარი უნდა შეიცავდეს 5ზე მეტ სიმბოლოს", Toast.LENGTH_SHORT).show()

            }

            else if(etEmail1.text.length < 8) {

                Toast.makeText(activity,"მეილი უნდა შეიცავდეს 8ზე მეტ სიმბოლოს", Toast.LENGTH_SHORT).show()

            }

            else if(!etEmail1.text.contains("@") or !etEmail1.text.contains(".")) {

                Toast.makeText(activity,"მეილი ითხოვს @ და .", Toast.LENGTH_SHORT).show()

            }

            else if(etPass1.text.length < 8) {

                Toast.makeText(activity,"პაროლი უნდა შეიცავდეს 8ზე მეტ სიმბოლოს", Toast.LENGTH_SHORT).show()

            }

            else {

                Toast.makeText(activity,"წარმატებით გაიარეთ რეგისტრაცია", Toast.LENGTH_SHORT).show()

                FirebaseAuth.getInstance().createUserWithEmailAndPassword(etEmail1Text,
                    etPass1Text
                ).
                addOnCompleteListener {task ->
                    if(task.isSuccessful){
                        val userInfo = Info(et1Name,et1surName)
                        dataBaseUserInfo.child(firebaseauth.currentUser?.uid!!).setValue(userInfo)
                        etName.setText("")
                        etSurName.setText("")
                        val fragmentAuthorization = fragmentAuthorization()
                        fragmentManager?.beginTransaction()?.replace(R.id.layoutMain,fragmentAuthorization)?.commit()
                    }
                    else{
                        Toast.makeText(activity, "მომხმარებელი უკვე არსებობს!", Toast.LENGTH_SHORT).show()
                    }

                }
                val fragmentAuthorization = fragmentAuthorization()
                fragmentManager?.beginTransaction()?.replace(R.id.layoutMain,fragmentAuthorization)?.commit()

            }

        }

        tvAuth.setOnClickListener{

            val fragmentAuthorization = fragmentAuthorization()
            fragmentManager?.beginTransaction()?.replace(R.id.layoutMain,fragmentAuthorization)?.commit()

        }

        tvPassRec1.setOnClickListener{

            val fragmentPasswordRecovery = fragmentPasswordRecovery()
            fragmentManager?.beginTransaction()?.replace(R.id.layoutMain,fragmentPasswordRecovery)?.commit()

        }

        tvPassChange1.setOnClickListener{

            val fragmentChangePassword = fragmentChangePassword()
            fragmentManager?.beginTransaction()?.replace(R.id.layoutMain,fragmentChangePassword)?.commit()

        }

        return v
    }

}