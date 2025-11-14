package com.example.aplicacionmoviliot.Firebase


import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

object FirebaseHelper {
    private val database: DatabaseReference = FirebaseDatabase.getInstance().reference

    fun write(path: String, value: Any) {
        database.child(path).setValue(value)
    }

    fun update(path: String, map: Map<String, Any>) {
        database.child(path).updateChildren(map)
    }

    fun ref(path: String): DatabaseReference {
        return database.child(path)
    }
}
