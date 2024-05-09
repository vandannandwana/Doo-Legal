package com.example.dolegal.presentation.screens.login_signup

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.dolegal.R
import com.example.dolegal.presentation.viewmodel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginPage(context: Context, vModel: HomeViewModel) {

    var email by rememberSaveable {
        mutableStateOf("")
    }

    var password by rememberSaveable {
        mutableStateOf("")
    }


    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Image(
                    painter = painterResource(R.drawable.mylogo),
                    contentDescription = "mylogo",
                    modifier = Modifier
                        .padding(14.dp)
                        .clip(RoundedCornerShape(100.dp))
                        .size(180.dp)

                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xBE737471)),
                contentAlignment = Alignment.Center
            ) {

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TextField(
                        value = email,
                        onValueChange = { email = it },
                        modifier = Modifier.padding(12.dp),
                        shape = RoundedCornerShape(12.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        placeholder = { Text(text = "Enter Email") },
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color(0xFF365349),
                            unfocusedLabelColor = Color.Unspecified,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        )
                    )
                    TextField(
                        value = password,
                        onValueChange = { password = it },
                        modifier = Modifier.padding(12.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        shape = RoundedCornerShape(12.dp),
                        placeholder = { Text(text = "Enter Password") },
                        colors = TextFieldDefaults.textFieldColors(
                            focusedTextColor = Color(0xFF365349),
                            unfocusedLabelColor = Color.Unspecified,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        )
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Row (horizontalArrangement = Arrangement.spacedBy(24.dp), verticalAlignment = Alignment.CenterVertically){
                        Image(
                            painter = painterResource(id = R.drawable.google_logo),
                            contentDescription = "google_logo",
                            modifier = Modifier.size(30.dp)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.facebook_logo),
                            contentDescription = "facebook_logo",
                            modifier = Modifier.size(34.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    ElevatedButton(
                            onClick = {
                                if (email.isNotEmpty() && password.isNotEmpty()) {
                                    vModel.login(email, password)
                                } else {
                                    Toast.makeText(
                                        context,
                                        "Please fill all the fields",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            },
                            modifier = Modifier
                                .padding(12.dp)

                        ) {

                            Text(text = "Login")

                        }

                }

            }

        }

    }


}