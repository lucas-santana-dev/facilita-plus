package br.com.plusapps.facilitaplus.data.remote.firebase.dto

/**
 * Data Transfer Object representing a user in the system.
 *
 * @property id Unique identifier for the user.
 * @property name Full name of the user.
 * @property email Email address of the user.
 * @property phone Optional phone number of the user.
 * @property photoUrl Optional URL to the user's profile photo.
 * @property role Role of the user in the system. Possible values:
 *  - "CLIENT": End user of the application.
 *  - "PROVIDER": Service provider.
 *  - "ADMIN": Administrator with elevated privileges.
 * @property createdAt Timestamp (milliseconds) when the user was created.
 * @property updatedAt Timestamp (milliseconds) when the user was last updated.
 */
data class UserDto(
    val id: String = "",
    val name: String = "",
    val email: String = "",
    val phone: String? = null,
    val photoUrl: String? = null,
    val role: String = "CLIENT", // CLIENT, PROVIDER, ADMIN
    val createdAt: Long = 0,
    val updatedAt: Long = 0
)