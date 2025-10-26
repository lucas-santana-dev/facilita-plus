package br.com.plusapps.facilitaplus.domain.model

/**
 * Represents a rating given by a client to a provider for a specific appointment.
 *
 * @property id Unique identifier for the rating.
 * @property appointmentId Identifier of the appointment being rated.
 * @property clientId Identifier of the client who gave the rating.
 * @property providerId Identifier of the provider who received the rating.
 * @property stars Number of stars given, ranging from 1 to 5.
) {
    init {
        require(stars in 1..5) { "Stars must be between 1 and 5" }
    }
}* @property comment Optional comment provided by the client.
 * @property createdAt Timestamp (in milliseconds) when the rating was created.
 */
data class Rating(
    val id: String,
    val appointmentId: String,
    val clientId: String,
    val providerId: String,
    val stars: Int,
    val comment: String?,
    val createdAt: Long
)