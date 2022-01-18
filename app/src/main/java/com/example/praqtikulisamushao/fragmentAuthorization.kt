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

class fragmentAuthorization : Fragment() {

    private lateinit var etEmail : EditText
    private lateinit var etPass : EditText
    private lateinit var tvReg : TextView
    private lateinit var tvPassRec : TextView
    private lateinit var tvPassChange : TextView
    private lateinit var btnAuth : Button
    private lateinit var btn1 : Button
    private lateinit var btn2 : Button
    private lateinit var btn3 : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_authorization, container, false)

        val etEmail = v.findViewById<EditText>(R.id.etEmail)

        val etPass = v.findViewById<EditText>(R.id.etPass)

        val tvReg = v.findViewById<TextView>(R.id.tvReg)

        val tvPassRec = v.findViewById<TextView>(R.id.tvPassRec)

        val tvPassChange = v.findViewById<TextView>(R.id.tvPassChange)

        val btnAuth = v.findViewById<Button>(R.id.btnAuth)

        btnAuth.setOnClickListener{

            val etEmailText = etEmail.text.toString().trim()

            val etPasswordText = etPass.text.toString().trim()

            if(etEmail.text.isEmpty() or etPass.text.isEmpty()) {

                Toast.makeText(activity,"შეავსეთ ყველა ველი", Toast.LENGTH_SHORT).show()

            }

            else if(etEmail.text.length < 8) {

                Toast.makeText(activity,"მეილი უნდა შეიცავდეს 8ზე მეტ სიმბოლოს", Toast.LENGTH_SHORT).show()

            }

            else if(!etEmail.text.contains("@") or !etEmail.text.contains(".")) {

                Toast.makeText(activity,"გთხოვთ შეიყვანოთ @ და . სიმბოლოები", Toast.LENGTH_SHORT).show()

            }

            else if(etPass.text.length < 8) {

                Toast.makeText(activity,"პაროლი უნდა შეიცავდეს 8ზე მეტ სიმბოლოს", Toast.LENGTH_SHORT).show()

            }

            else {

                FirebaseAuth.getInstance().signInWithEmailAndPassword(etEmailText,etPasswordText).addOnCompleteListener{
                        task ->
                    if (task.isSuccessful){
                        etPass.text.clear()
                        etEmail.text.clear()
                        Toast.makeText(activity,"წარმატებით გაიარეთ ავთენთიფიკაცია", Toast.LENGTH_SHORT).show()
                        Handler().postDelayed({
                            val intent = Intent(activity, MainActivity2 :: class.java)
                            startActivity(intent)


                        }, 2000)

                    } else {

                        Toast.makeText(activity, "აუცილებელია რეგისტრაცია", Toast.LENGTH_SHORT).show()

                    }


                }

            }

        }

        tvReg.setOnClickListener{

            val fragmentRegistration = fragmentRegistration()
            fragmentManager?.beginTransaction()?.replace(R.id.layoutMain,fragmentRegistration)?.commit()

            etPass.text.clear()
            etEmail.text.clear()

        }

        tvPassRec.setOnClickListener{

            val fragmentPasswordRecovery = fragmentPasswordRecovery()
            fragmentManager?.beginTransaction()?.replace(R.id.layoutMain,fragmentPasswordRecovery)?.commit()
            etPass.text.clear()
            etEmail.text.clear()

        }

        tvPassChange.setOnClickListener{

            val fragmentChangePassword = fragmentChangePassword()
            fragmentManager?.beginTransaction()?.replace(R.id.layoutMain,fragmentChangePassword)?.commit()
            etPass.text.clear()
            etEmail.text.clear()

        }

        return v
    }

}