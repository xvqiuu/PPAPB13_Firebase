package com.example.studentfirebase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.studentfirebase.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    //inisialisasi binding
    private lateinit var binding : ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //mengatur tata letak menggunakan binding
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //mendapatkan data yang dikirimkan melalui intent
        val data = intent.extras

        with(binding) {
            //data yang ditampilkan
            descNama.text = "${data?.getString("nama")}"
            descNim.text = "${data?.getString("nim")}"
            descJurusan.text = "${data?.getString("jurusan")}"
            descSemester.text = "${data?.getString("semester")}"
            descAsal.text = "${data?.getString("asal")}"

            btnBack.setOnClickListener {
                finish()
            }
        }
    }
}