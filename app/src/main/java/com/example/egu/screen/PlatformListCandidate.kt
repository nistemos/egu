package com.example.egu.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.egu.R
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.io.Serializable

val voyo=0
data class Candidates(
    val name: String,
    val candidate: String,
    val proposal: String,
    val id : String,
    val  image: String,
    val voto : Int) :Serializable{
    constructor() : this("", "", "", "","",0)
}
val db = Firebase.firestore
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComposablePlatformListCandidate(
    navController: NavController,
    listCandidates: List<Candidates>
){
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
        },
        content = {it
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                items(listCandidates){ candidate ->
                    PlatformListCandidate(candidate)
                }
            }
        }
    )
}




@Composable
fun PlatformListCandidate(
    candidates: Candidates,
    modifier: Modifier = Modifier,
){
    var openDialog by remember { mutableStateOf(false) }

    Row(
        modifier
            .fillMaxWidth()
            .clickable {
                openDialog = !openDialog
            }
            .background(MaterialTheme.colorScheme.background)
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            modifier = Modifier
                .size(70.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary),
            contentDescription = "Mi image de test"
        )
        Column(
            modifier = Modifier
                .padding(horizontal = 10.dp)
        ) {
            Text(
                text = candidates.name,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(3.dp))
            Text(
                text = stringResource(R.string.candidate_for)+candidates.candidate,
                color = MaterialTheme.colorScheme.onBackground,
            )
        }
        if (openDialog) {
            AlertDialog(
                onDismissRequest = { openDialog = false },
                title = { Text(candidates.name) },
                text = {
                    Column {
                        Text(
                            text = "Proposal",
                            color = MaterialTheme.colorScheme.onBackground,
                        )
                        Text(
                            text = candidates.proposal,
                            color = MaterialTheme.colorScheme.onBackground,
                        )
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
}