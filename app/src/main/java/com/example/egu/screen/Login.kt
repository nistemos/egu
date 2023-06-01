package com.example.egu.screen

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.egu.R
import com.example.egu.navigation.AppScreen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase



@Composable
fun ComposableLogin(navController: NavController){
    Login(
        text = stringResource(R.string.login),
        navController = navController,
    )
}



private lateinit var auth: FirebaseAuth


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(text: String, navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    auth = Firebase.auth
    Column(
        modifier = Modifier
            .padding(start = 32.dp, end = 32.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        TextField(
            value = email,
            onValueChange = {text ->
                email= text

            },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
            label = {
                Text(stringResource(id = R.string.email))
            },
            leadingIcon = { Icon(Icons.Filled.Email, contentDescription = "Localized description") },
            trailingIcon = { Icon(Icons.Filled.Info, contentDescription = "Localized description") },
            /*supportingText = {
                Text("Supporting text that is long and perhaps goes onto another line.")
            },*/
            modifier = Modifier.padding(TextFieldDefaults.textFieldWithLabelPadding())
        )
        TextField(
            value = password,
            onValueChange = {text ->
                password = text
            },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
            singleLine = true,
            label = {
                Text(stringResource(id = R.string.clave))
            },
            leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = "Localized description") },
            trailingIcon = { Icon(Icons.Filled.Info, contentDescription = "Localized description") },
            modifier = Modifier.padding(TextFieldDefaults.textFieldWithLabelPadding())
        )
        Button(
            onClick = {
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        navController.navigate(route = AppScreen.PlatformScreen.route)

                    }else{
                        Log.w(TAG, "signInWithCredential:failure", task.exception)
                    }

                }

            },
            modifier = Modifier
                .padding(ButtonDefaults.ButtonWithIconContentPadding),
        ) {
            Row{
                Text(stringResource(id = R.string.login))
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Icon(
                    Icons.Filled.Send,
                    contentDescription = "Localized description",
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
            }
        }
    }
}