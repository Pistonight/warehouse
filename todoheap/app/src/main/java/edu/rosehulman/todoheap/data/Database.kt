package edu.rosehulman.todoheap.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

object Database {
    private const val COLLECTION_USERS = "users"
    private const val COLLECTION_FREE_EVENTS = "free_events"
    private const val COLLECTION_SCHEDULED_EVENTS = "scheduled_events"
    private const val COLLECTION_SETTINGS = "settings"
    private const val DOCUMENT_NOTIFICATION = "settings"
    private const val DOCUMENT_WEIGHT_SETTINGS = "weight_settings"

    private val userCollection = FirebaseFirestore.getInstance().collection(COLLECTION_USERS)
    val auth = FirebaseAuth.getInstance()


    fun signOut() = auth.signOut()

    //val uid get() = auth.uid

    val freeEventsCollection get() = if (auth.uid==null)null else userCollection.document(auth.uid?:"").collection(COLLECTION_FREE_EVENTS)
    val scheduledEventsCollection get() = if (auth.uid==null)null else userCollection.document(auth.uid?:"").collection(COLLECTION_SCHEDULED_EVENTS)
    val settingsCollection get() = if (auth.uid==null)null else userCollection.document(auth.uid?:"").collection(COLLECTION_SETTINGS)
    val notificationSettingsDocument get() = settingsCollection?.document(DOCUMENT_NOTIFICATION)
    val weightSettingsDocument get() = settingsCollection?.document(DOCUMENT_WEIGHT_SETTINGS)

}