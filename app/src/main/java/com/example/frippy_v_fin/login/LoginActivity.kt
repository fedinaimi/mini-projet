package com.example.frippy_v_fin.login

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import api.userApiInterface
import com.example.frippy_v_fin.R
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.textfield.TextInputEditText
import menu1.MainActivity
import models.UserModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
   // private var verificat: TextView? = null
    private var singup: TextView? = null
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val signInButton = findViewById<Button>(R.id.btnlogin)
        val email = findViewById<TextInputEditText>(R.id.txtemail)
        val password = findViewById<TextInputEditText>(R.id.txtpassword)
        val rememberCheckBox = findViewById<MaterialCheckBox>(R.id.cbrmbr)
        val sharedPreferences = getSharedPreferences("FRIPPY", MODE_PRIVATE)

     /*   verificat = findViewById(R.id.veriff)
        verificat!!.setOnClickListener {
            val mainIntent = Intent(this, Mainwebview::class.java)
            startActivity(mainIntent)
        }*/

        if (sharedPreferences.all.isNotEmpty()){
            startActivity(Intent(this,MainActivity::class.java))
        }
        val userApiInterface = userApiInterface.create()
        singup = findViewById(R.id.singup)
        singup!!.setOnClickListener {
            val mainIntent = Intent(this, Singup::class.java)
            startActivity(mainIntent)
        }
        signInButton.setOnClickListener {
            userApiInterface.signin(
                mapOf(
                    "email" to email.text.toString(),
                    "password" to password.text.toString()
                )
            ).enqueue(
                (object : Callback<UserModel> {
                    override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {

                        println("----------------------------------------------------------")
                        println(response.body()!!.toString())
                        println("----------------------------------------------------------")

                        if (response.isSuccessful) {
                            Toast.makeText(
                                applicationContext,
                                "Login successfull",
                                Toast.LENGTH_SHORT
                            ).show()
                            if (rememberCheckBox.isChecked) {
                                sharedPreferences.edit().clear().apply()
                                sharedPreferences.edit().apply() {
                                    putString("_id", response.body()!!.user?._id)
                                    putString("email", response.body()!!.user?.email)
                                    putString(
                                        "name",
                                        response.body()?.user?.firstName + response.body()!!.user?.lastName
                                    )
                                }.apply()
                            }
                            Log.e("TAG", "onCreate: ${sharedPreferences.all}")
                            startActivity(Intent(applicationContext, MainActivity::class.java))
                        } else {
                            Toast.makeText(
                                applicationContext,
                                "Verify your credentials",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    override fun onFailure(call: Call<UserModel>, t: Throwable) {
                        Toast.makeText(applicationContext, "${t.toString()}", Toast.LENGTH_SHORT)
                            .show()
                    }
                })
            )

        }
    }
}