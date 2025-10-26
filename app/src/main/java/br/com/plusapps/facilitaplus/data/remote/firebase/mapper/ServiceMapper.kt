package br.com.plusapps.facilitaplus.data.remote.firebase.mapper

import br.com.plusapps.facilitaplus.data.remote.firebase.dto.ServiceDto
import br.com.plusapps.facilitaplus.domain.model.Service

fun ServiceDto.toDomain() = Service(
    id = id,
    providerId = providerId,
    title = title,
    description = description,
    price = price,
    estimatedDuration = estimatedDuration,
    createdAt = createdAt,
    updatedAt = updatedAt
)

/**
 * Converts a [Service] domain model to a [ServiceDto] for remote data transfer.
 * Performs a direct field mapping from domain to DTO.
 */
fun Service.toDto() = ServiceDto(
    id = id,
    providerId = providerId,
    title = title,
    description = description,
    price = price,
    estimatedDuration = estimatedDuration,
    createdAt = createdAt,
    updatedAt = updatedAt
)