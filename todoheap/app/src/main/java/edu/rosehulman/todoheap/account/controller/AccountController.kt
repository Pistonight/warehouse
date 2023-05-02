package edu.rosehulman.todoheap.account.controller

import android.util.Log
import android.view.View
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import edu.rosehulman.todoheap.Constants
import edu.rosehulman.todoheap.R
import edu.rosehulman.todoheap.account.model.NotificationSettingModel
import edu.rosehulman.todoheap.main.MainActivity
import edu.rosehulman.todoheap.data.Database


class AccountController(
    private val activity: MainActivity
) {

    private var loggedIn = false


    private val authListener = FirebaseAuth.AuthStateListener {
        val user = it.currentUser
        if (user==null){
            loggedIn = false
            launchLoginUI()

        }else{
            Log.d(Constants.TAG, "login ok $user");
            if(!loggedIn){
                activity.loginOK()
                loggedIn = true
            }

        }
    }

    fun setupListener(){
        Database.auth.addAuthStateListener(authListener)
    }
    fun removeListener(){
        Database.auth.removeAuthStateListener(authListener)
    }

    fun onLogOut() {
        Database.signOut()

    }

    private fun launchLoginUI() {
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build()
        )
        val loginIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .setTheme(R.style.Theme_TodoHeap)
            .build()
        //loginIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        activity.startActivityForResult(loginIntent, 1)
       // activity.start
    }
}