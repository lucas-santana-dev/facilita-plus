package br.com.plusapps.facilitaplus.data.remote.firebase.dto
/**
 * DTO usado na integração com Firebase que representa um agendamento.
 *
 * @property id Identificador único do agendamento (valor padrão vazio).
 * @property clientId Identificador do cliente associado ao agendamento (valor padrão vazio).
 * @property providerId Identificador do prestador responsável pelo agendamento (valor padrão vazio).
 * @property serviceId Identificador do serviço solicitado (valor padrão vazio).
 * @property scheduledAt Timestamp em milissegundos indicando quando o agendamento ocorrerá (padrão 0).
 * @property status Estado do agendamento representado como `String` (padrão "PENDING").
 * @property notes Observações opcionais relacionadas ao agendamento; pode ser nulo.
 * @property createdAt Timestamp em milissegundos indicando quando o registro foi criado (padrão 0).
 * @property updatedAt Timestamp em milissegundos indicando a última atualização do registro (padrão 0).
 *
 * @constructor Cria uma instância de AppointmentDto com valores padrão, útil para desserialização pela Firebase.
 */
data class AppointmentDto(
    val id: String = "",
    val clientId: String = "",
    val providerId: String = "",
    val serviceId: String = "",
    val scheduledAt: Long = 0,
    val status: String = "PENDING",
    val notes: String? = null,
    val createdAt: Long = 0,
    val updatedAt: Long = 0
)