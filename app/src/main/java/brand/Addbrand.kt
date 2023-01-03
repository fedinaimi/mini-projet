package brand

import android.content.DialogInterface
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import api.boutiqueApiInterface
import api.userApiInterface
import com.example.frippy_v_fin.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import models.BoutiqueModelItem
import models.ProduitModel
import models.UserModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Addbrand : AppCompatActivity() {

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var latitude:TextView
    private lateinit var longitude:TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addbrand)
        val nom = findViewById<EditText>(R.id.brandname)
        val brandadress = findViewById<EditText>(R.id.brandadress)
        val brandemail = findViewById<EditText>(R.id.brandemail)
        val NumTel = findViewById<EditText>(R.id.NumTel)
        val link = findViewById<EditText>(R.id.link)
        val sharedPreferences = getSharedPreferences("FRIPPY", MODE_PRIVATE)

        var id = sharedPreferences.getString("_id",null)

        val add = findViewById<Button>(R.id.btnsub)

        //----------------------------
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        latitude = findViewById(R.id.latitude)
        longitude = findViewById(R.id.longitude)
        val button = findViewById<Button>(R.id.getPos)

        button.setOnClickListener { getLocation() }


        //---------------------




        add.setOnClickListener {
            val apiInterface = boutiqueApiInterface.create()
            apiInterface.addboutique(
                mapOf(
                    "nom" to nom.text.toString(),
                    "adresse" to brandadress.text.toString(),
                    "brandemail" to brandemail.text.toString(),
                    "numtelf" to NumTel.text.toString(),
                    "linkSocialmedia" to link.text.toString(),
                    "client" to id.toString(),

                    )
            ).enqueue(
                object : Callback<BoutiqueModelItem> {
                    override fun onResponse(
                        call: Call<BoutiqueModelItem>, response:
                        Response<BoutiqueModelItem>
                    ) {
                        if (response.isSuccessful) {
                            Toast.makeText(
                                this@Addbrand,
                                "add success!",
                                Toast.LENGTH_SHORT
                            ).show()
                            finish()

                        } else {
                            Toast.makeText(
                                this@Addbrand,
                                "mch te5dem",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    /**
                     * Invoked when a network exception occurred talking to the server or when an unexpected exception
                     * occurred creating the request or processing the response.
                     */
                    override fun onFailure(call: Call<BoutiqueModelItem>, t: Throwable) {
                        val builder = AlertDialog.Builder(this@Addbrand)
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


    private fun getLocation(){
        if (ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_COARSE_LOCATION)
            !=PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),100)
            return
        }
        val location = fusedLocationProviderClient.lastLocation
        location.addOnSuccessListener {
            if(it!=null){
                val textLatitude = "Latitude: "+it.latitude.toString()
                val textLongitude = "Longitude: "+it.longitude.toString()

                latitude.text = textLatitude
                longitude.text = textLongitude
            }
        }
    }


}