package com.example.egu.navigation

sealed class AppScreen (val route : String){
    object SplashScreen: AppScreen("ComposableSplash")
    object LoginScreen: AppScreen("ComposableLogin")
    object PlatformScreen: AppScreen("ComposablePlatform")
    object ComposablePlatformListCandidateScreen: AppScreen("ComposablePlatformListCandidate")
    object ComposableVoteScreen: AppScreen("ComposableVote")
    object ComposableVotingScreen: AppScreen("ComposableVoting")
}