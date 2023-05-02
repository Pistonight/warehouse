package edu.rosehulman.todoheap.account.view

import android.graphics.drawable.Drawable

data class AccountViewModel(
    val name: String = "",
    var profilePicture: Drawable
) {
}