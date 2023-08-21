package com.example.firestorepractice.data.source.remote

import com.example.firestorepractice.data.model.RoomDto
import com.example.firestorepractice.data.model.UserDto
import com.example.firestorepractice.domain.source.FirebaseDataSource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.util.concurrent.CancellationException
import javax.inject.Inject

class FirebaseDataSourceImp @Inject constructor(
    private val firebaseFireStore: FirebaseFirestore,
    private val auth: FirebaseAuth
) : FirebaseDataSource {
    override suspend fun signUp(email: String, password: String): FirebaseUser? {
        try {
            return auth.createUserWithEmailAndPassword(email, password)
                .await().user
        } catch (e: Exception) {
            if (e is CancellationException)
                return null
        }
        return null
    }

    override suspend fun login(email: String, password: String): FirebaseUser? {
        try {
            return auth.signInWithEmailAndPassword(email, password)
                .await().user
        } catch (e: Exception) {
            if (e is CancellationException)
                return null
        }
        return null
    }

    override suspend fun addUser(name: String): Boolean {
        val user = auth.currentUser
        if (user != null) {
            val userDocumentRef = firebaseFireStore.collection(USERS).document(user.uid)
            userDocumentRef.set(
                UserDto(
                    id = user.uid,
                    email = user.email,
                    name = name
                )
            ).await()
            return true
        }
        return false
    }

    override suspend fun createRoom(adminId: String): RoomDto? {
        val roomId = getRoomId()
        val userDocumentRef = firebaseFireStore.collection(ROOMS).document(roomId)
        val admin = getUser(adminId)
        val room = RoomDto(
            roomId = roomId,
            adminId = adminId,
            users = listOf(admin!!)
        )
        return try {
            userDocumentRef.set(room).await()
            room
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun joinRoom(roomId: String, userId: String): RoomDto? {
        val roomDocumentRef = firebaseFireStore.collection(ROOMS).document(roomId)

        try {
            val roomDocumentSnapshot = roomDocumentRef.get().await()

            if (roomDocumentSnapshot.exists()) {
                val room = roomDocumentSnapshot.toObject(RoomDto::class.java)
                val userExistsInRoom = room?.users?.any { it.id == userId } ?: false

                if (!userExistsInRoom) {
                    val user = getUser(userId)
                    if (user != null) {
                        val updatedUsers = room?.users?.plus(user) ?: listOf(user)
                        roomDocumentRef.update(USERS, updatedUsers).await()
                        return room
                    }
                }
                return room
            }
        } catch (e: Exception) {
            return null
        }
        return null
    }

    private fun getRoomId() = System.currentTimeMillis().toString()

    override suspend fun getUser(userId: String): UserDto? {
        return try {
            val userDocumentSnapshot = firebaseFireStore.collection(USERS)
                .document(userId)
                .get()
                .await()

            if (userDocumentSnapshot.exists()) {
                userDocumentSnapshot.toObject(UserDto::class.java)
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun getRoom(roomId: String): RoomDto? {
        return try {
            val userDocumentSnapshot = firebaseFireStore.collection(ROOMS)
                .document(roomId)
                .get()
                .await()

            if (userDocumentSnapshot.exists()) {
                userDocumentSnapshot.toObject(RoomDto::class.java)
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }


    companion object {
        private const val USERS = "users"
        private const val ROOMS = "rooms"
    }
}