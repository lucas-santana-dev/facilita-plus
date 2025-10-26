package br.com.plusapps.facilitaplus.data.remote.firebase.dto

/**
 * Data Transfer Object representing a service provider.
 *
 * @property id Unique identifier for the provider.
 * @property userId Identifier for the associated user account.
 * @property profession The profession or service offered by the provider.
 * @property description A brief description of the provider's services.
 * @property rating Average rating received by the provider.
 * @property totalReviews Total number of reviews for the provider.
 * @property isVerified Indicates if the provider is verified.
 * @property createdAt Timestamp of when the provider was created.
 * @property updatedAt Timestamp of the last update to the provider.
 */
data class ProviderDto(
    val id: String = "",
    val userId: String = "",
    val profession: String = "",
    val description: String = "",
    val rating: Double = 0.0,
    val totalReviews: Int = 0,
    val isVerified: Boolean = false,
    val createdAt: Long = 0,
    val updatedAt: Long = 0
)