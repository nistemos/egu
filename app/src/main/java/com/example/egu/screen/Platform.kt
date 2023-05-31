package com.example.egu.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.example.egu.navigation.AppScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComposablePlatform(navController: NavController){
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
        Platform(modifier = Modifier, navController)
    }
}

@Composable
fun Platform(modifier: Modifier, navController: NavController) {
    Column(
        modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.welcome_to_egu),
            modifier = Modifier.padding(bottom = 20.dp)
        )
        Button(
            onClick = {
                navController.navigate(route = AppScreen.ComposablePlatformListCandidateScreen.route)
            },
            modifier = Modifier
                .width(200.dp),
        ) {
            Text(
                text = stringResource(R.string.candidates)
            )
        }
        Button(
            onClick = {
                navController.navigate(route = AppScreen.ComposableVoteScreen.route)
            },
            modifier = Modifier
                .width(200.dp),
        ) {
            Text(
                text = stringResource(R.string.vote)
            )
        }
        Button(
            onClick = {
                navController.navigate(route = AppScreen.ComposableVotingScreen.route)
            },
            modifier = Modifier
                .width(200.dp),
        ) {
            Text(
                text = stringResource(R.string.voting)
            )
        }
        Button(
            onClick = { },
            modifier = Modifier
                .width(200.dp),
        ) {
            Text(
                text = stringResource(R.string.sign_off)
            )
        }
    }
}