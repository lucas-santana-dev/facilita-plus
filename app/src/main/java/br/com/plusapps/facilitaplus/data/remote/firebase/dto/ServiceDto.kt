package br.com.plusapps.facilitaplus.data.remote.firebase.dto

/**
 * Data Transfer Object representing a service offered by a provider.
 *
 * @property id Unique identifier for the service.
 * @property providerId Identifier of the provider offering the service.
 * @property title Title or name of the service.
 * @property description Detailed description of the service.
 * @property price Price of the service.
 * @property estimatedDuration Estimated duration of the service in minutes.
 * @property createdAt Timestamp (in milliseconds) when the service was created.
 * @property updatedAt Timestamp (in milliseconds) when the service was last updated.
 */
data class ServiceDto(
    val id: String = "",
    val providerId: String = "",
    val title: String = "",
    val description: String = "",
    val price: Double = 0.0,
    val estimatedDuration: Int = 0,
    val createdAt: Long = 0,
    val updatedAt: Long = 0
)