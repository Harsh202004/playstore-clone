package com.example.playstoreclone

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.example.playstoreclone.ui.navigation.NavigationHost
import com.example.playstoreclone.ui.theme.PlaystoreCloneTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlaystoreCloneTheme {
                NavigationHost()
            }
        }
    }
}