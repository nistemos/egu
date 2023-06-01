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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.egu.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComposableVoting(navController: NavController, listCandidate : List<Candidates>){
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

        Voting(listCandidate)
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Voting(listCandidate : List<Candidates>){
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(text = stringResource(R.string.candidatos_que_puntuan_las_elecciones))
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            items(listCandidate){candidate->
                Card(
                    onClick = { /* Do something */ },
                    modifier = Modifier.padding(8.dp).size(width = 200.dp, height = 100.dp)
                )
                {
                    Column(
                        Modifier.fillMaxSize()
                        .wrapContentHeight(Alignment.CenterVertically)
                            .padding(horizontal = 10.dp)
                    ) {
                        Text(
                            candidate.name,
                            Modifier
                            .align(Alignment.CenterHorizontally)
                        )
                        Text("Votos " + candidate.voto,
                            Modifier
                            .align(Alignment.CenterHorizontally)
                        )

                    }
                }
            }
        }
        Text(text = stringResource(R.string.total_votos) + " "+(listCandidate.reduce{acc,valor->  Candidates("","","","","",acc.voto+valor.voto) }).voto)
    }
}