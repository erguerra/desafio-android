package com.picpay.desafio.android.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.picpay.desafio.android.ui.theme.PicPayTheme
import com.picpay.desafio.android.ui.userList.compose.UserListScreen
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PicPayTheme {
                UserListScreen()
            }
        }
    }
}
