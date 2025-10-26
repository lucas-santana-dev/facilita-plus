package br.com.plusapps.facilitaplus.domain.model
/**
 * Representa um agendamento entre um cliente e um prestador para a execução de um serviço.
 *
 * @property id Identificador único do agendamento (por exemplo, UUID).
 * @property clientId Identificador do cliente que solicitou o serviço.
 * @property providerId Identificador do prestador responsável pelo serviço.
 * @property serviceId Identificador do serviço contratado.
 * @property scheduledAt Timestamp (em milissegundos) indicando quando o serviço está agendado.
 * @property status Estado atual do agendamento (veja `AppointmentStatus`).
 * @property notes Observações opcionais fornecidas pelo cliente ou prestador; pode ser nulo.
 * @property createdAt Timestamp (em milissegundos) indicando quando o agendamento foi criado.
 * @property updatedAt Timestamp (em milissegundos) indicando a última atualização do agendamento.
 *
 * @constructor Cria uma instância de Appointment com os campos fornecidos.
 */
data class Appointment (
    val id: String,
    val clientId: String,
    val providerId: String,
    val serviceId: String,
    val scheduledAt: Long,
    val status: AppointmentStatus,
    val notes: String?,
    val createdAt: Long,
    val updatedAt: Long
)
/**
 * Enumeração dos possíveis estados de um agendamento.
 */
enum class AppointmentStatus {
    /** Agendamento criado e aguardando confirmação. */
    PENDING,
    /** Agendamento confirmado pelo prestador/cliente. */
    CONFIRMED,
    /** Serviço completado com sucesso. */
    COMPLETED,
    /** Agendamento cancelado. */
    CANCELED
}