package com.example.conversosTemp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.conversosTemp.ui.theme.MyApplicationTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    TemperatureConverterApp()
                }
            }
        }
    }
}

@Composable
fun TemperatureConverterApp() {
    var celsiusText = remember { mutableStateOf("") }
    var fahrenheitText = remember { mutableStateOf("") }

    Surface(color = MaterialTheme.colorScheme.background) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TextField(
                value = celsiusText.value,
                onValueChange = { celsiusText.value = it },
                label = { Text("Celsius") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.padding(8.dp)
            )
            Button(onClick = {
                if (celsiusText.value.isNotBlank()) {
                    val celsius = celsiusText.value.toFloat()
                    val fahrenheit = celsius * 1.8f + 32
                    val formattedFahrenheit = String.format("%.1f", fahrenheit)
                    fahrenheitText.value = formattedFahrenheit
                }
            }) {
                Text("Celsius para Fahrenheit")
            }

            TextField(
                value = fahrenheitText.value,
                onValueChange = { fahrenheitText.value = it },
                label = { Text("Fahrenheit") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.padding(8.dp)
            )
            Button(onClick = {
                if (fahrenheitText.value.isNotBlank()) {
                    val fahrenheit = fahrenheitText.value.toFloat()
                    val celsius = (fahrenheit - 32) * (5f / 9f)
                    val formattedCelsius = String.format("%.1f", celsius)
                    celsiusText.value = formattedCelsius
                }
            }) {
                Text("Fahrenheit para Celsius")
            }
            Button(onClick = {
                celsiusText.value = ""
                fahrenheitText.value = ""
            }, modifier = Modifier.padding (top = 10.dp)) {
                Text("Limpar Campos")
            }
        }
    }
}



