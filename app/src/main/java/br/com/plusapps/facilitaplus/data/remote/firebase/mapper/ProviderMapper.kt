package br.com.plusapps.facilitaplus.data.remote.firebase.mapper

import br.com.plusapps.facilitaplus.data.remote.firebase.dto.ProviderDto
import br.com.plusapps.facilitaplus.domain.model.Provider


fun ProviderDto.toDomain() = Provider(
    id = id,
    userId = userId,
    profession = profession,
    description = description,
    rating = rating,
    totalReviews = totalReviews,
    isVerified = isVerified,
    createdAt = createdAt,
    updatedAt = updatedAt
)

/**
 * Converts a [Provider] domain model to a [ProviderDto] for remote data operations.
 * Performs a field-to-field mapping between the domain and DTO objects.
 */
fun Provider.toDto() = ProviderDto(
    id = id,
    userId = userId,
    profession = profession,
    description = description,
    rating = rating,
    totalReviews = totalReviews,
    isVerified = isVerified,
    createdAt = createdAt,
    updatedAt = updatedAt
)