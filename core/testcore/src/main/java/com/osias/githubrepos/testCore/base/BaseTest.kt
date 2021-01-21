package com.osias.githubrepos.testCore.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule

open class BaseTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

}