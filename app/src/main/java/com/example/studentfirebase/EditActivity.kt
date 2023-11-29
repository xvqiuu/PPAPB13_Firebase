package com.example.studentfirebase

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.studentfirebase.databinding.ActivityEditBinding

class EditActivity : AppCompatActivity() {

    private lateinit var binding : ActivityEditBinding

    private var updateId = " "

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            edtNama.setText(intent.getStringExtra("nama"))
            edtNim.setText(intent.getStringExtra("nim"))
            edtJurusan.setText(intent.getStringExtra("jurusan"))
            edtSemester.setText(intent.getStringExtra("semester"))
            edtAsal.setText(intent.getStringExtra("asal"))
            btnEdit.setOnClickListener {
                val updateStudent = Student (
                    id = intent.getStringExtra("id"),
                    nama = edtNama.text.toString(),
                    nim = edtNim.text.toString(),
                    jurusan = edtJurusan.text.toString(),
                    semester = edtSemester.text.toString(),
                    asal = edtAsal.text.toString()
                )
                updateStudent(updateStudent)
                //diperbarui dengan nilai baru dari objek student
                updateId = ""
                finish()
            }
        }
    }

    private fun updateStudent(student : Student) {
        MainActivity.studentCollectionRef.document(student.id.toString()).set(student)
           .addOnFailureListener {
               Log.d("MainActivity", "Error updating student : ", it)

               runOnUiThread {
                   finish()
                   Toast.makeText(
                       this@EditActivity,
                       "Student Saved Successfully",
                       Toast.LENGTH_SHORT
                   ).show()
               }
            }
    }
}
