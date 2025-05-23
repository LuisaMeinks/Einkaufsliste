package com.example.einkaufslisten // Paketname – entspricht dem Projektordner

// Imports für Jetpack Compose und Android-Funktionen
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

// Hauptaktivität – Einstiegspunkt der App
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // UI mit Jetpack Compose festlegen
        setContent {
            EinkaufslistenApp() // Unsere Haupt-UI-Komponente aufrufen
        }
    }
}

// UI-Komponente für die gesamte App
@Composable
fun EinkaufslistenApp() {
    var eingabe by remember { mutableStateOf("") } // Zustand für Eingabefeld
    var liste by remember { mutableStateOf(listOf<String>()) } // Zustand für Artikelliste

    // Layout: Elemente untereinander anordnen
    Column(modifier = Modifier.padding(16.dp)) {

        // Texteingabe für neuen Artikel
        TextField(
            value = eingabe, // aktueller Wert
            onValueChange = { eingabe = it }, // bei Änderung aktualisieren
            label = { Text("Artikel eingeben") }, // Beschriftung im Feld
            modifier = Modifier.fillMaxWidth() // ganze Breite nutzen
        )

        Spacer(modifier = Modifier.height(8.dp)) // vertikaler Abstand

        // Button zum Hinzufügen von Artikeln
        Button(
            onClick = {
                if (eingabe.isNotBlank()) { // nur wenn etwas eingegeben wurde kann ->
                    liste = liste + eingabe // neuer Artikel zur Liste hinzufügen
                    eingabe = "" // Eingabefeld leeren
                }
            },
            modifier = Modifier.fillMaxWidth() // ganze Breite nutzen
        ) {
            Text("Hinzufügen") // Button-Text
        }

        Spacer(modifier = Modifier.height(16.dp)) // Abstand zur Liste

        // Anzeige der Artikel in einer scrollbaren Liste
        LazyColumn {
            items(liste) { artikel -> // für jeden Artikel
                Text(
                    text = artikel, // Artikelname anzeigen
                    modifier = Modifier
                        .fillMaxWidth() // ganze Breite nutzen
                        .clickable { liste = liste - artikel } // bei Klick= Artikel entfernen
                        .padding(8.dp) // Innenabstand
                )
            }
        }
    }
}
