package com.osias.githubrepos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.osias.actions.Actions.openHomeIntent

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(openHomeIntent(this))
        finish()
    }
}