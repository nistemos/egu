package com.example.egu.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.egu.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComposableVoting(navController: NavController){
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
        Voting()
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Voting(){
    Column(
        modifier = Modifier
            .padding(start = 32.dp, end = 32.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(text = stringResource(R.string.candidatos_que_puntuan_las_elecciones))
        Row(
            modifier = Modifier
                .padding(vertical = 20.dp)
        ) {
            Card(
                onClick = { /* Do something */ },
                modifier = Modifier.size(width = 160.dp, height = 100.dp)
            ) {
                Box(Modifier.fillMaxSize()) {
                    Text("Candidato 1", Modifier.align(Alignment.Center))
                }
            }
            Spacer(modifier = Modifier.padding(horizontal = 5.dp))
            Card(
                onClick = { /* Do something */ },
                modifier = Modifier.size(width = 160.dp, height = 100.dp)
            ) {
                Box(Modifier.fillMaxSize()) {
                    Text("Candidato 2", Modifier.align(Alignment.Center))
                }
            }
        }
        Text(text = stringResource(R.string.total_votos))
        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .height(15.dp),
            progress = 0.7f,
        )
    }
}