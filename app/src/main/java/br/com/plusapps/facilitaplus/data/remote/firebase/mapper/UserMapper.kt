package br.com.plusapps.facilitaplus.data.remote.firebase.mapper

import br.com.plusapps.facilitaplus.data.remote.firebase.dto.UserDto
import br.com.plusapps.facilitaplus.domain.model.User
import br.com.plusapps.facilitaplus.domain.model.UserRole


/**
 * Mapeamentos entre o DTO remoto `UserDto` e o modelo de domínio `User`.
 *
 * Contém funções de extensão para conversão nos dois sentidos:
 * - `UserDto.toDomain()` converte o DTO recebido da Firebase para o modelo de domínio.
 * - `User.toDto()` converte o modelo de domínio para o DTO usado na camada remota.
 */

/**
 * Converte um `UserDto` (camada remota) para o modelo de domínio `User`.
 *
 * @receiver instância de `UserDto` recebida da fonte remota.
 * @return `User` com os valores mapeados do DTO.
 *
 * Observação: `UserRole.valueOf(role)` lança `IllegalArgumentException` se `role`
 * não corresponder a um valor válido do enum. Garanta que o valor recebido seja válido
 * ou trate a exceção ao chamar esta função.
 */

fun UserDto.toDomain() = User(
    id = id,
    name = name,
    email = email,
    phone = phone,
    photoUrl = photoUrl,
        role = runCatching { UserRole.valueOf(role) }.getOrNull() ?: UserRole.CLIENT,
    createdAt = createdAt,
    updatedAt = updatedAt
)
/**
 * Converte um `User` (modelo de domínio) para o DTO `UserDto` usado na camada remota.
 *
 * @receiver instância de `User` do domínio.
 * @return `UserDto` com os mesmos valores mapeados do modelo de domínio.
 */
fun User.toDto() = UserDto(
    id = id,
    name = name,
    email = email,
    phone = phone,
    photoUrl = photoUrl,
    role = role.name,
    createdAt = createdAt,
    updatedAt = updatedAt
)