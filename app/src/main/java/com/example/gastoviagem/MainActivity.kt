package com.example.gastoviagem

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.gastoviagem.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonCalculate.setOnClickListener(this)
        binding.buttonCalcularAutonomia.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if(view.id == R.id.button_calculate) {
            calculate()
        } else if (view.id == R.id.button_calcular_autonomia) {
            gadCalculate()
        }
    }

    //GastoViagem

    private fun isValid(): Boolean {
        val distance = binding.editDistance.text.toString().toFloatOrNull()
        val price = binding.editPrice.text.toString().toFloatOrNull()
        val autonomy = binding.editAutonomy.text.toString().toFloatOrNull()
        return (distance != null && price != null && autonomy != null && autonomy != 0f)
    }

    private fun calculate() {
        if(isValid()) {
            val distance = binding.editDistance.text.toString().toFloat()
            val price = binding.editPrice.text.toString().toFloat()
            val autonomy = binding.editAutonomy.text.toString().toFloat()

            val totalValue = (distance * price) / autonomy

            binding.textShowTotal.text = "R$ ${"%.2f".format(totalValue)}"
        } else {
            Toast.makeText(this, R.string.Validation_Fields, Toast.LENGTH_SHORT).show()
        }
    }

    //Autonomia

    private fun gadIsValid(): Boolean {
        val litros = binding.editQtdLitros.text.toString().toFloatOrNull()
        val km = binding.editKmPercorridos.text.toString().toFloatOrNull()

        // Verifica se foram preenchidos corretamente e se litros não é zero
        return (litros != null && km != null && litros != 0f)
    }

    private fun gadCalculate() {
        if(gadIsValid()) {
            val gadLitros = binding.editQtdLitros.text.toString().toFloat()
            val gadKm = binding.editKmPercorridos.text.toString().toFloat()

            val gadResultado = gadKm / gadLitros

            binding.textResultadoAutonomia.text = "${"%.2f".format(gadResultado)} KM/L"
        } else {
            Toast.makeText(this, R.string.Validation_Fields, Toast.LENGTH_SHORT).show()
        }
    }
}
