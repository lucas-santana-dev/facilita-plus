package br.com.plusapps.facilitaplus.domain.model

/**
 * Representa um usuário do sistema.
 *
 * @property id Identificador único do usuário (por exemplo, UUID).
 * @property name Nome completo do usuário.
 * @property email Endereço de e-mail principal do usuário.
 * @property phone Telefone de contato; pode ser nulo se não informado.
 * @property photoUrl URL da foto de perfil; pode ser nulo.
 * @property role Papel do usuário no sistema (veja `UserRole`).
 * @property createdAt Timestamp (em milissegundos) indicando quando o usuário foi criado.
 * @property updatedAt Timestamp (em milissegundos) indicando a última atualização do usuário.
 *
 * @constructor Cria uma instância de User com os campos fornecidos.
 */
data class User(
    val id: String,
    val name: String,
    val email: String,
    val phone: String?,
    val photoUrl: String?,
    val role: UserRole,
    val createdAt: Long,
    val updatedAt: Long
)


/**
 * Enumeração dos papéis possíveis de um usuário no sistema.
 */
enum class UserRole {
    /** Cliente do aplicativo. */
    CLIENT,
    /** Prestador de serviços / fornecedor. */
    PROVIDER,
    /** Administrador do sistema com permissões elevadas. */
    ADMIN
}