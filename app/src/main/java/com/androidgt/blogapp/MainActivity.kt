package com.androidgt.blogapp

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

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

        }
    }
}