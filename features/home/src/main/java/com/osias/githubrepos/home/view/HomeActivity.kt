package com.osias.githubrepos.home.view

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.osias.githubrepos.home.di.module
import com.osias.home.databinding.ActivityHomeBinding
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class HomeActivity : AppCompatActivity() {

    companion object {
        //used to inject mock modules in test
        const val TEST_EXTRA = "test_extra_key"
    }

    private val isTest: Boolean
        get() = intent.getBooleanExtra(TEST_EXTRA, false)

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(!isTest) loadKoinModules(module)
        binding = ActivityHomeBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        if(!isTest) unloadKoinModules(module)
    }


}