package com.example.studentfirebase

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.studentfirebase.databinding.ActivityAddBinding
import com.google.firebase.firestore.FirebaseFirestore

class AddActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            btnAdd.setOnClickListener {
                //mengambil nilai yg diinputkan
                val nama = addNama.text.toString()
                val nim = addNim.text.toString()
                val jurusan = addJurusan.text.toString()
                val semester = addSemester.text.toString()
                val asal = addAsal.text.toString()

                //membuat objek baru dr kelas student yang berisi nilai- nilai tersebut
                val newStudent = Student(
                    nama = nama, nim = nim, jurusan = jurusan, semester = semester, asal = asal
                )
                addStudent(newStudent)

                Toast.makeText(this@AddActivity, "Student Added Successfully",
                    Toast.LENGTH_SHORT).show()
                finish()
            }

            btnUndo.setOnClickListener {
                finish()
            }
        }
    }

    private fun addStudent(student: Student) {
        MainActivity.studentCollectionRef.add(student)
            .addOnFailureListener {
                Log.d("MainActivity", "Error adding student : ", it)
            }
    }
}