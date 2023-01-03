package com.example.frippy_v_fin.login

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import api.userApiInterface
import com.example.frippy_v_fin.R
import menu1.MainActivity
import models.UserModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Singup : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    private var verificat: TextView? = null
    private var loginn: TextView? = null
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_singup)
        val firstname = findViewById<EditText>(R.id.firstname)
        val lastname = findViewById<EditText>(R.id.lastname)
        val email = findViewById<EditText>(R.id.Email)
        val Password = findViewById<EditText>(R.id.Password)
        val register = findViewById<Button>(R.id.btnNext1)
        val sharedprefs = getSharedPreferences("Login_prefs", MODE_PRIVATE)

        verificat = findViewById(R.id.verif)
        verificat!!.setOnClickListener {
            val mainIntent = Intent(this, Mainwebview::class.java)
            startActivity(mainIntent)
        }
        loginn = findViewById(R.id.login)
        loginn!!.setOnClickListener {
            val mainIntent = Intent(this, LoginActivity::class.java)
            startActivity(mainIntent)
        }
        if (sharedprefs.getString("_id", "")!!.isNotEmpty()) {
            Log.d(
                "Shared prefs",
                "this is whats in the shared prefs" + sharedprefs.getString("_id", "nothing in id")
            )

            val i = Intent(applicationContext, MainActivity::class.java)
            startActivity(i)
        } else {
            register.setOnClickListener {
                val apiInterface = userApiInterface.create()
                apiInterface.register(mapOf(
                    "firstName" to firstname.text.toString(),
                    "lastName" to lastname.text.toString(),
                    "email" to email.text.toString(),
                    "password" to Password.text.toString()
                )).enqueue(
                    object : Callback<UserModel> {
                        override fun onResponse(
                            call: Call<UserModel>, response:
                            Response<UserModel>
                        ) {
                            if (response.isSuccessful) {
                                Toast.makeText(
                                    this@Singup,
                                    "register success!",
                                    Toast.LENGTH_SHORT
                                ).show()


                            } else {
                                Toast.makeText(
                                    this@Singup,
                                    "mch te5dem",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }

                        /**
                         * Invoked when a network exception occurred talking to the server or when an unexpected exception
                         * occurred creating the request or processing the response.
                         */
                        override fun onFailure(call: Call<UserModel>, t: Throwable) {
                            val builder = AlertDialog.Builder(this@Singup)
                            Log.i("erreur", t.toString())
                            builder.setTitle("login failed")
                                .setMessage("An error has occured, try connecting to the internet")
                                .setNegativeButton("ok",
                                    DialogInterface.OnClickListener { dialog, id ->
                                        dialog.cancel()
                                    })

                            // Create the AlertDialog object and return it
                            builder.create().show()
                        }

                    }
                )
            }

        }
    }
}