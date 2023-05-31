package com.example.egu.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.egu.screen.Candidates
import com.example.egu.screen.CandidatesVote
import com.example.egu.screen.ComposableLogin
import com.example.egu.screen.ComposablePlatform
import com.example.egu.screen.ComposablePlatformListCandidate
import com.example.egu.screen.ComposableSplash
import com.example.egu.screen.ComposableVote
import com.example.egu.screen.ComposableVoting

private val candidates : List<CandidatesVote> = listOf(
    CandidatesVote("photo", "Pepito Prez", "Presidencia"),
    CandidatesVote("photo", "Pepito Prez", "Presidencia"),
    CandidatesVote("photo", "Pepito Prez", "Presidencia"),
    CandidatesVote("photo", "Pepito Prez", "Presidencia")
)

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreen.SplashScreen.route) {
        composable(route = AppScreen.SplashScreen.route) {
            ComposableSplash(navController)
        }
        composable(route = AppScreen.LoginScreen.route) {
            ComposableLogin(navController)
        }
        composable(route = AppScreen.PlatformScreen.route) {
            ComposablePlatform(navController)
        }


        composable(route = AppScreen.ComposablePlatformListCandidateScreen.route) {
            ComposablePlatformListCandidate(navController)
        }
        composable(route = AppScreen.ComposableVoteScreen.route) {
            ComposableVote(navController, candidates)
        }
        composable(route = AppScreen.ComposableVotingScreen.route) {
            ComposableVoting(navController)
        }
    }
}