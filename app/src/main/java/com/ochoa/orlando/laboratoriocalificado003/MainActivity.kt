package com.ochoa.orlando.laboratoriocalificado003

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import android.util.Log
import com.ochoa.orlando.laboratoriocalificado003.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var teacherList: List<Teacher>
    private lateinit var adapter: TeacherAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        fetchTeachers()
    }

    private fun fetchTeachers() {
        ApiClient.apiService.getTeachers().enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    // Accede a la lista de profesores dentro de la propiedad 'teachers'
                    teacherList = response.body()?.teachers ?: emptyList()
                    Log.d("MainActivity", "Datos obtenidos: $teacherList")
                    adapter = TeacherAdapter(this@MainActivity, teacherList)
                    binding.recyclerView.adapter = adapter
                } else {
                    Toast.makeText(this@MainActivity, "Error al obtener datos", Toast.LENGTH_SHORT).show()
                    Log.e("MainActivity", "Error al obtener datos: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error de conexión: ${t.message}", Toast.LENGTH_SHORT).show()
                Log.e("MainActivity", "Error de conexión: ${t.message}")
            }
        })
    }
}

