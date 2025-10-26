package br.com.plusapps.facilitaplus.data.remote.firebase.mapper

import br.com.plusapps.facilitaplus.data.remote.firebase.dto.RatingDto
import br.com.plusapps.facilitaplus.domain.model.Rating

/**
 * Mapeamentos entre o DTO remoto `RatingDto` e o modelo de domínio `Rating`.
 *
 * Contém funções de extensão para converter nos dois sentidos:
 * - `RatingDto.toDomain()` converte do DTO recebido da Firebase para o modelo de domínio.
 * - `Rating.toDto()` converte do modelo de domínio para o DTO usado na camada remota.
 */

/**
 * Converte um `RatingDto` (camada remota) para o modelo de domínio `Rating`.
 *
 * @receiver `RatingDto` instância recebida da fonte remota.
 * @return `Rating` com os mesmos valores mapeados do DTO.
 */

fun RatingDto.toDomain() = Rating(
    id = id,
    appointmentId = appointmentId,
    clientId = clientId,
    providerId = providerId,
    stars = stars,
    comment = comment,
    createdAt = createdAt
)

/**
 * Converte um `Rating` (modelo de domínio) para o DTO `RatingDto` usado na camada remota.
 *
 * @receiver `Rating` instância do domínio.
 * @return `RatingDto` com os mesmos valores mapeados do modelo de domínio.
 */
fun Rating.toDto() = RatingDto(
    id = id,
    appointmentId = appointmentId,
    clientId = clientId,
    providerId = providerId,
    stars = stars,
    comment = comment,
    createdAt = createdAt
)
