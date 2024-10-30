package com.example.currency

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.icu.util.Currency
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.util.Log

class MainActivity : AppCompatActivity() {
    lateinit var input: EditText
    lateinit var output: TextView
    lateinit var inputSpinner: Spinner
    lateinit var outputSpinner: Spinner
    lateinit var convert : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        input = findViewById(R.id.input)
        inputSpinner = findViewById(R.id.spinner)
        var inArrayAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.countries,
            android.R.layout.simple_spinner_item
        )
        inArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        inputSpinner.adapter = inArrayAdapter

        output = findViewById(R.id.text_result)
        outputSpinner = findViewById(R.id.spinner2)
        var outArrayAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.countries,
            android.R.layout.simple_spinner_item
        )
        outArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        outputSpinner.adapter = outArrayAdapter

        //menh gia cac loai tien, coi USD la moc
        val VN = ExchangeRate("VietNam", 25330.0)
        val CN = ExchangeRate("China",7.12)
        val EU = ExchangeRate("Europe",0.92)
        val JP = ExchangeRate("Japan",153.0)
        val UK = ExchangeRate("United Kingdom",0.77)
        val US = ExchangeRate("United States",1.0)

        var rate1: Double = 0.0
        var rate2: Double = 0.0
        inputSpinner.run {
            onItemSelectedListener = object : OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, id: Long) {
                    if (p0 != null) {
                        if (position == 0){
                            rate1 = VN.rate
                        } else if (position == 1){
                            rate1 = CN.rate
                        } else if (position == 2){
                            rate1 = EU.rate
                        } else if (position == 3){
                            rate1 = JP.rate
                        } else if (position == 4){
                            rate1 = UK.rate
                        } else if (position == 5){
                            rate1 = US.rate
                        }

                    }
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        }

        outputSpinner.run{
            onItemSelectedListener = object : OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, id: Long) {
                    if (p0 != null) {
                        if (position == 0){
                            rate2 = VN.rate
                        } else if (position == 1){
                            rate2 = CN.rate
                        } else if (position == 2){
                            rate2 = EU.rate
                        } else if (position == 3){
                            rate2 = JP.rate
                        } else if (position == 4){
                            rate2 = UK.rate
                        } else if (position == 5){
                            rate2 = US.rate
                        }

                    }
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }

        }

        convert = findViewById(R.id.materialButtonC)
        convert.setOnClickListener {
            var value: Double = input.text.toString().toDouble()

            var res: Double = convertFun(value,rate1,rate2)
            Log.d("MainActivity", "Value: $value, Rate1: $rate1, Rate2: $rate2, Res: $res")
            output.setText(res.toString())
        }

    }
}

data class ExchangeRate(val country: String, val rate: Double)

fun convertFun(input: Double, rate1: Double, rate2: Double): Double {
    return  input * rate2 / rate1
}