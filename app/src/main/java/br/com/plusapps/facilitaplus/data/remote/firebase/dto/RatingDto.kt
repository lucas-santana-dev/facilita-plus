package br.com.plusapps.facilitaplus.data.remote.firebase.dto

/**
 * DTO usado na integração com Firebase que representa uma avaliação.
 *
 * @property id Identificador único da avaliação (valor padrão vazio).
 * @property appointmentId Identificador do agendamento associado (valor padrão vazio).
 * @property clientId Identificador do cliente que realizou a avaliação (valor padrão vazio).
 * @property providerId Identificador do prestador avaliado (valor padrão vazio).
 * @property stars Número de estrelas atribuídas (padrão 0).
 * @property comment Comentário opcional da avaliação; pode ser nulo.
 * @property createdAt Timestamp em milissegundos indicando quando a avaliação foi criada (padrão 0).
 *
 * @constructor Cria uma instância de RatingDto com valores padrão, útil para desserialização pela Firebase.
 */
data class RatingDto(
    val id: String = "",
    val appointmentId: String = "",
    val clientId: String = "",
    val providerId: String = "",
    val stars: Int = 0,
    val comment: String? = null,
    val createdAt: Long = 0
)