package com.androidgt.blogapp

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // la consulta y ingreso de informacion a la base de datos es asincrona esto quiere decir que se ejecuta en segundo plano.
        // y que una tarea puede terminar antes que la otra.
        // get y set son metodos asincronos.
        // Las interfaces addOnSuccessListener y OnFailureListener son asincronas.

        //CONSULTAR INFORMACION.
//  creamos la instancia de la base de datos.
        val db = FirebaseFirestore.getInstance()
//  obtenemos los datos de la base de datos.
//        esta esta dentro de un collection que es document y dentro del documento obtenemos con get() busca solo una vez la informacion
//        la data.
        db.collection("ciudades").document("NY").get().addOnSuccessListener { document ->
            document?.let {
                // convierte el documento en un objeto de tipo ciudad.
                // lo guardamos en la variable ciudad.
                val ciudad = document.toObject(Ciudad::class.java)
                Log.d("FireBase", "Color: ${ciudad?.color}")
                Log.d("FireBase", "Population: ${ciudad?.population}")
                Log.d("FireBase", "PostalCode: ${ciudad?.pc}")
//                val color = document.getString("color")
//                val population = document.getLong("population")
//                Log.d("Firebase", " color: ${color}")
//                Log.d("Firebase", " population: ${population}")
            }
        }.addOnFailureListener { exception ->
            Log.e("FireBase", "Error getting documents: ${exception.toString()}")
        }

        //INGRESAR INFORMACION A LA BASE DE DATOS.

        // se guarda en la misma coleccion ciudades.
        // se crea un nuevo documento que se llama "NY"
        // luego ingresamos los datos que queremos ingresar a la base de datos
        db.collection("ciudades").document("LA").set(
            // ingresando un objeto.
            Ciudad("Red", 3000000)
            //es un escucha si ha exito o no. en la obtension de la informacion.
        ).addOnSuccessListener {
            Log.d("Firebase", "Se guardo la ciudad correctamente.")
        }.addOnFailureListener { exception ->
            Log.e("Firebase", "Error getting documents: ${exception.toString()}")
        }


        // OBTENER INFORMACION EN TIEMPO REAL.
        // cada vez que se actualice un valor de AZ devuelve la data con los ultimos cambios.
        db.collection("ciudades").document("AZ").addSnapshotListener { snapshot, exception ->
            snapshot?.let { document ->
                val ciudad = document.toObject(Ciudad::class.java)
                Log.d("FireBase", "Color: ${ciudad?.color}")
                Log.d("FireBase", "Population: ${ciudad?.population}")
                Log.d("FireBase", "PostalCode: ${ciudad?.pc}")
            }
        }
    }
}

data class Ciudad(
    val color: String = "",
    val population: Long = 0,
    val pc: Int = 0
)
