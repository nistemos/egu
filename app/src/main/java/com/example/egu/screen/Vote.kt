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
import com.example.egu.navigation.AppScreen
import com.example.egu.navigation.getCandidate
import com.example.egu.navigation.getCurrentUid
import com.example.egu.navigation.getVote
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.io.Serializable


data class CandidatesVote(
    val user: String,
    val candidate: String
    ) :Serializable{
        constructor():this("","")
    }



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComposableVote(navController: NavController, candidates: List<Candidates>){
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
                Vote(candidates,navController)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Vote(candidates: Candidates,navController: NavController) {
    var openDialog by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
            horizontalArrangement = Arrangement.Center
    ){
        Card(
            onClick = {
                val data = mutableMapOf<String, Any>()
                System.out.println("voto " + candidates.voto)
                data["voto"] = candidates.voto + 1

                db.document("candidate/"+candidates.id)
                    .update(data).addOnCompleteListener {task ->
                        if(task.isSuccessful){
                            db.collection("vote").add(CandidatesVote(getCurrentUid(),candidates.id)).addOnCompleteListener(
                                OnCompleteListener {task ->
                                if(task.isSuccessful){
                                    getCandidate()
                                    getVote(getCurrentUid())
                                    openDialog = !openDialog
                                }

                                }
                            )

                        }
                    }.addOnFailureListener{e->
                        System.out.println(e.toString())
                    }

                      },
            modifier = Modifier.size(width = 160.dp, height = 100.dp),
            elevation = CardDefaults.cardElevation()
        ) {
            Box(Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                   /* Text(
                        candidates.image,
                        Modifier.align(Alignment.CenterHorizontally)
                    )*/
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
                TextButton(onClick = {
                    openDialog = false
                    navController.navigate(route = AppScreen.PlatformScreen.route)

                }) {
                    Text("Aceptar")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    openDialog = false
                    navController.navigate(route = AppScreen.PlatformScreen.route)

                }) {
                    Text("Cancelar")
                }
            }
        )
    }
}
