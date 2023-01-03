package brand

import android.app.Activity
import android.content.ContentResolver
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import api.boutiqueApiInterface
import com.example.frippy_v_fin.R
import com.google.android.material.snackbar.Snackbar
import models.ProduitModel
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream

class Addproduit : AppCompatActivity(), UploadRequestBody.UploadCallback {
    lateinit var selectedImageUri: Uri


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addproduit)
        //**********
        val image_view = findViewById<ImageView>(R.id.image_view)

        val add = findViewById<Button>(R.id.btnsub)

        image_view.setOnClickListener {
            opeinImageChooser()
        }

        add.setOnClickListener { uploadImage() }

    }

    private fun uploadImage() {




        val brandId = intent.getStringExtra("id")

        val apiInterface = boutiqueApiInterface.create()
        val progress_bar = findViewById<ProgressBar>(R.id.progress_bar)
        val layout_root = findViewById<ConstraintLayout>(R.id.layout_root)
        if (selectedImageUri == null) {
            layout_root.snackbar("Select an Image First")
            return
        }

        val parcelFileDescriptor = contentResolver.openFileDescriptor(
            selectedImageUri!!, "r", null
        ) ?: return
        val productname = findViewById<EditText>(R.id.produitname)
        val productquanitite = findViewById<EditText>(R.id.quanitite)
        val productprix = findViewById<EditText>(R.id.prix)
        val productdescription = findViewById<EditText>(R.id.description)
        val filesDir = applicationContext.filesDir
        val  file =File(filesDir,contentResolver.getFileName(selectedImageUri!!))
        val inputStream =contentResolver.openInputStream(selectedImageUri)
        val outputStream = FileOutputStream(file)
        inputStream!!.copyTo(outputStream)
        val  requestBody = file.asRequestBody("image/*".toMediaTypeOrNull())
        var part = MultipartBody.Part.createFormData("image",file.name,requestBody)
        var produit = productname.text.toString()
        var quantite = productquanitite.text.toString().toInt()
        var prix = productprix.text.toString().toInt()
        var description = productdescription.text.toString()

        apiInterface.addProduit(
            part,
            produit,
            quantite,
            prix,
            description,



            id = brandId!!,


            ).enqueue(object : Callback<ProduitModel>{

            override fun onResponse(call: Call<ProduitModel>, response: Response<ProduitModel>) {
                if (response.isSuccessful) {
                    Toast.makeText(
                        this@Addproduit,

                        "add success!",
                        Toast.LENGTH_SHORT
                    ).show()

                    finish()

                } else {
                    Toast.makeText(
                        this@Addproduit,
                        "mch te5dem",
                        Toast.LENGTH_SHORT
                    ).show()
                }            }


            override fun onFailure(call: Call<ProduitModel>, t: Throwable) {
                Toast.makeText(applicationContext, "${t.message}", Toast.LENGTH_SHORT).show()
                Log.e("TAG", "onFailure: ${t.message}", )            }


        })


    }

    private fun opeinImageChooser() {

        Intent(Intent.ACTION_PICK).also {
            it.type = "image/*"
            val mimeTypes = arrayOf("image/jpeg", "image/png","image/jpg")
            it.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
            startActivityForResult(it, REQUEST_CODE_IMAGE)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val image_view = findViewById<ImageView>(R.id.image_view)
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_CODE_IMAGE -> {
                    selectedImageUri = data?.data!!
                    image_view.setImageURI(selectedImageUri)
                }
            }
        }
    }

    companion object {
        const val REQUEST_CODE_IMAGE = 101
    }

    override fun onProgressUpdate(percentage: Int) {
        val progress_bar = findViewById<ProgressBar>(R.id.progress_bar)
        progress_bar.progress = percentage
    }

}
fun ContentResolver.getFileName(selectedImageUri: Uri): String {
    var name = ""
    val returnCursor = this.query(selectedImageUri,null,null,null,null)
    if (returnCursor!=null){
        val nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        returnCursor.moveToFirst()
        name = returnCursor.getString(nameIndex)
        returnCursor.close()
    }

    return name
}

fun View.snackbar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).also { snackbar ->
        snackbar.setAction("OK") {
            snackbar.dismiss()
        }
    }.show()

}