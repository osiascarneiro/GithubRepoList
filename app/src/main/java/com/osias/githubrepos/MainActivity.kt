package com.osias.githubrepos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.osias.actions.Actions.openHomeIntent

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(openHomeIntent(this))
        finish()
    }
}