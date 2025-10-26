package br.com.plusapps.facilitaplus.data.remote.firebase.mapper

import br.com.plusapps.facilitaplus.data.remote.firebase.dto.AppointmentDto
import br.com.plusapps.facilitaplus.domain.model.Appointment
import br.com.plusapps.facilitaplus.domain.model.AppointmentStatus

/**
 * Mapeamentos entre o DTO remoto `AppointmentDto` e o modelo de domínio `Appointment`.
 *
 * Contém funções de extensão para conversão nos dois sentidos:
 * - `AppointmentDto.toDomain()` converte o DTO recebido da Firebase para o modelo de domínio.
 * - `Appointment.toDto()` converte o modelo de domínio para o DTO usado na camada remota.
 */

/**
 * Converte um `AppointmentDto` (camada remota) para o modelo de domínio `Appointment`.
 *
 * @receiver instância de `AppointmentDto` recebida da fonte remota.
 * @return `Appointment` com os valores mapeados do DTO.
 *
 * Observação: `AppointmentStatus.valueOf(status)` lança IllegalArgumentException se `status`
 * não corresponder a um valor válido do enum. Garanta que o valor recebido seja válido.
 */
fun AppointmentDto.toDomain() = Appointment(
    id = id,
    clientId = clientId,
    providerId = providerId,
    serviceId = serviceId,
    scheduledAt = scheduledAt,
        status = runCatching { AppointmentStatus.valueOf(status) }.getOrNull() ?: AppointmentStatus.PENDING,
    notes = notes,
    createdAt = createdAt,
    updatedAt = updatedAt
)

/**
 * Converte um `Appointment` (modelo de domínio) para o DTO `AppointmentDto` usado na camada remota.
 *
 * @receiver instância de `Appointment` do domínio.
 * @return `AppointmentDto` com os mesmos valores mapeados do modelo de domínio.
 */

fun Appointment.toDto() = AppointmentDto(
    id = id,
    clientId = clientId,
    providerId = providerId,
    serviceId = serviceId,
    scheduledAt = scheduledAt,
    status = status.name,
    notes = notes,
    createdAt = createdAt,
    updatedAt = updatedAt
)
