package com.example.egu.navigation

import android.content.ContentValues
import android.util.Log
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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase



val db = Firebase.firestore
var candidatesList = mutableListOf<Candidates>();
var voteList = mutableListOf<CandidatesVote>();


fun getCurrentUid() : String {
    val user = Firebase.auth.currentUser
   if(user!= null){
       return user.uid
   }
    return ""
}

fun getCandidate(){
    candidatesList = mutableListOf<Candidates>();
    db.collection("candidate")
        .get()
        .addOnSuccessListener { result ->
            for (document in result) {
                val user = document.toObject(Candidates::class.java)
                candidatesList.add(user)
            }
        }
        .addOnFailureListener { exception ->
            Log.w(ContentValues.TAG, "Error getting documents.", exception)
        }
}
fun getVote(uid :String){
    voteList = mutableListOf<CandidatesVote>();
    db.collection("vote")
        .whereEqualTo("user",uid)
        .get()
        .addOnSuccessListener { result ->
            for (document in result) {
                val user = document.toObject(CandidatesVote::class.java)
                voteList.add(user)
            }
        }
        .addOnFailureListener { exception ->
            Log.w(ContentValues.TAG, "Error getting documents.", exception)
        }
}

@Composable
fun AppNavigation() {
    getCandidate()
    getVote(getCurrentUid())
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreen.SplashScreen.route) {

        composable(route = AppScreen.SplashScreen.route) {
            ComposableSplash(navController)
        }
        composable(route = AppScreen.LoginScreen.route) {
            ComposableLogin(navController)
        }
        composable(route = AppScreen.PlatformScreen.route) {
            ComposablePlatform(navController, voteList)
        }


        composable(route = AppScreen.ComposablePlatformListCandidateScreen.route) {
            ComposablePlatformListCandidate(navController,candidatesList)
        }
        composable(route = AppScreen.ComposableVoteScreen.route) {
            ComposableVote(navController, candidatesList)
        }
        composable(route = AppScreen.ComposableVotingScreen.route) {
            ComposableVoting(navController, candidatesList)
        }
    }
}