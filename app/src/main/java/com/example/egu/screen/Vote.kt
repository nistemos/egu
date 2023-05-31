package com.example.egu.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.egu.R


data class CandidatesVote(val photo: String, val name: String, val candidate: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComposableVote(navController: NavController, candidates: List<CandidatesVote>){
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.egu),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Localized description"
                        )
                    }
                }
            )
        }
    ) {
        it
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            items(candidates) { candidates ->
                Vote(candidates)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Vote(candidates: CandidatesVote) {
    var openDialog by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
            horizontalArrangement = Arrangement.Center
    ){
        Card(
            onClick = {
                      /* Do something */
                openDialog = !openDialog
                      },
            modifier = Modifier.size(width = 160.dp, height = 100.dp),
            elevation = CardDefaults.cardElevation()
        ) {
            Box(Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        candidates.photo,
                        Modifier.align(Alignment.CenterHorizontally)
                    )
                    Text(
                        candidates.name,
                        Modifier.align(Alignment.CenterHorizontally)
                    )
                    Text(
                        candidates.candidate,
                        Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.padding(horizontal = 5.dp))
        Card(
            onClick = { /* Do something */ },
            modifier = Modifier.size(width = 160.dp, height = 100.dp)
        ) {
            Box(Modifier.fillMaxSize()) {
                Text("Clickable", Modifier.align(Alignment.Center))
            }
        }
    }
    if (openDialog) {
        AlertDialog(
            onDismissRequest = { openDialog = false },
            title = { Text(candidates.name) },
            text = {
                Column {
                    Text(
                        text = "Gracias por Votar",
                        modifier = Modifier.padding(bottom = 10.dp)
                    )
                    Image(painter = painterResource(id = R.drawable.check), contentDescription = "Thank you for votar" )
                }
            },
            confirmButton = {
                TextButton(onClick = { openDialog = false }) {
                    Text("Aceptar")
                }
            },
            dismissButton = {
                TextButton(onClick = { openDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }
}
