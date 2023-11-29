package com.example.studentfirebase


import com.google.firebase.firestore.Exclude

data class Student(
    //id merefer langsung ke id yang terdapat dalam firebase
    @set:Exclude @get:Exclude @Exclude var id: String? = "",
    //membuat atribut nam dnegan tipe data String dengan nilai default String kosong
    var nama: String = "",
    var nim: String = "",
    var jurusan: String = "",
    var semester: String = "",
    var asal: String = ""

)
