package br.com.plusapps.facilitaplus.domain.model

/**
 * Representa um prestador de serviços no sistema.
 *
 * @property id Identificador único do prestador (por exemplo, UUID).
 * @property userId Identificador do usuário associado a este prestador.
 * @property profession Profissão ou área de atuação do prestador.
 * @property description Breve descrição ou apresentação do prestador.
 * @property rating Avaliação média do prestador (de 0.0 a 5.0), padrão 0.0.
 * @property totalReviews Quantidade total de avaliações recebidas, padrão 0.
 * @property isVerified Indica se o prestador foi verificado pelo sistema, padrão false.
 * @property createdAt Timestamp (em milissegundos) indicando quando o prestador foi criado.
 * @property updatedAt Timestamp (em milissegundos) indicando a última atualização do prestador.
 *
 * @constructor Cria uma instância de Provider com os campos fornecidos.
 */
data class Provider (
    val id: String,
    val userId: String,
    val profession: String,
    val description: String,
    val rating: Double = 0.0,
    val totalReviews: Int = 0,
    val isVerified: Boolean = false,
    val createdAt: Long,
    val updatedAt: Long
)