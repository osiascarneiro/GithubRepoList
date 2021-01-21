package com.osias.actions

import android.content.Context
import android.content.Intent

object Actions {

    private const val HOME_ACTION = "com.osias.home.action"

    fun openHomeIntent(context: Context) = internalIntent(context, HOME_ACTION)

    private fun internalIntent(context: Context, action: String) = Intent(action).setPackage(context.packageName)

}