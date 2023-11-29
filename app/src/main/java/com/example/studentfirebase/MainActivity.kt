package com.example.studentfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studentfirebase.databinding.ActivityMainBinding
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    companion object {
        //akses firestore
        val firestore = FirebaseFirestore.getInstance()

        val studentListLiveData : MutableLiveData<List<Student>>
                by lazy {
                    MutableLiveData<List<Student>>()
                }

        val studentCollectionRef = firestore.collection("student")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){
            fabAdd.setOnClickListener {
                val intent = Intent(this@MainActivity, AddActivity::class.java)
                startActivity(intent)
            }
        }
        observeStudents()
        getAllStudents()
    }

    private fun getAllStudents() {
        studentCollectionRef.addSnapshotListener { snapshots, error ->
            if (error != null) {
                Log.d("MainActivity", "Error listening for student changes",
                    error)
                return@addSnapshotListener
            }
            //loop data
            val students = arrayListOf<Student>()
            snapshots?.forEach {
                //isinya mengambil data data yang berupa documentReference
                    documentReference ->
               students.add(
                    //id digunakan untuk update dan hapus
                    Student(documentReference.id,
                        documentReference.get("nama").toString(),
                        documentReference.get("nim").toString(),
                        documentReference.get("jurusan").toString(),
                        documentReference.get("semester").toString(),
                        documentReference.get("asal").toString()
                    )
                )
            }
            if (students != null) {
                studentListLiveData.postValue(students)
            }
            //set adapter dan layout manager pada RecyclerView
            with(binding) {
                rvStudents.apply {
                    layoutManager = LinearLayoutManager(context)
                }
            }
        }
    }

    private fun observeStudents() {
        studentListLiveData.observe(this) {
                students ->
            val studentAdapter = StudentAdapter(students, {
                student ->
                deleteStudent(student)
            })
            binding.rvStudents.adapter = studentAdapter
        }

    }

    private fun deleteStudent(student : Student) {
        studentCollectionRef.document(student.id.toString()).delete()
            .addOnFailureListener {
                Log.d("MainActivity", "Error deleting student : ", it)
            }
    }

}