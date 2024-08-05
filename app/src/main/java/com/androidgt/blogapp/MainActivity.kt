package com.androidgt.blogapp

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import java.io.ByteArrayOutputStream
import java.util.UUID

// FireBase Tomar Fotos.
class MainActivity : AppCompatActivity() {


    private lateinit var imageView: ImageView

    private val REQUEST_IMAGE_CAPTURE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val btnTakePicture = findViewById<Button>(R.id.btn_take_picture)
        // la variable guarda la referencia del elemento xml.
        imageView = findViewById(R.id.imageView)

        btnTakePicture.setOnClickListener {
            dispatchTakePictureIntent()
        }
    }


    // metodo que tomara la foto.
    private fun dispatchTakePictureIntent() {
        // Lo que hace intent encuentra cualquier app abre la camara con actionimageCapture.
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            // retorna un resultado de la foto tomada.
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)

        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this, "No se encontro app para tomar foto", Toast.LENGTH_SHORT).show()
        }
    }

    //
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // compara los resultados.
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            // le asignamos el bitmap al imageView.
            imageView.setImageBitmap(imageBitmap)
            uploadPicture(imageBitmap)
        }
    }

    //
    private fun uploadPicture(bitmap: Bitmap) {
        val storageRef = FirebaseStorage.getInstance().reference
        val imagesRef = storageRef.child("${UUID.randomUUID()}.jpg")
        // putFile: mandamos la uri de la imagen.lugar donde esta la imagen.
        // putByte: sube una secuencia de Bytes a nuestro Storage.
//        imagesRef.putBytes()
        val baos = ByteArrayOutputStream()
        // comprime la imagen.
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()
        val uploadTask = imagesRef.putBytes(data)
        // esperamos a que la tarea termine.
        uploadTask.continueWithTask { task ->
            if (!task.isSuccessful) {
                task.exception?.let { exception ->
                    throw exception
                }
            }
            imagesRef.downloadUrl
        }.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val downloadUrl = task.result.toString()
                // mandamos un nuevo atributo al LA. entra al documento y actualiza y crea el campo imageUrl.
                FirebaseFirestore.getInstance().collection("ciudades").document("LA").update(mapOf("imageUrl" to downloadUrl))
                Log.d("Storage", "uploadPictureUrl: ${downloadUrl}")
            }
        }
    }
}