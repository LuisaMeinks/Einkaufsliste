package com.example.einkaufslisten

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EinkaufslistenApp()
        }
    }
}

@Composable
fun EinkaufslistenApp() {
    var eingabe by remember { mutableStateOf("") }
    var liste by remember { mutableStateOf(listOf<String>()) }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = eingabe,
            onValueChange = { eingabe = it },
            label = { Text("Artikel eingeben") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            if (eingabe.isNotBlank()) {
                liste = liste + eingabe
                eingabe = ""
            }
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Hinzufügen")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(liste) { artikel ->
                Text(
                    text = artikel,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            liste = liste - artikel
                        }
                        .padding(8.dp)
                )
            }
        }
    }
}
