package br.com.plusapps.facilitaplus.domain.model

/**
 * Representa um serviço oferecido por um prestador.
 *
 * @property id Identificador único do serviço (por exemplo, UUID).
 * @property providerId Identificador do prestador que oferece este serviço.
 * @property title Título curto do serviço.
 * @property description Descrição detalhada do serviço.
 * @property price Preço do serviço (valor em unidades monetárias).
 * @property estimatedDuration Duração estimada do serviço, em minutos.
 * @property createdAt Timestamp (em milissegundos) indicando quando o serviço foi criado.
 * @property updatedAt Timestamp (em milissegundos) indicando a última atualização do serviço.
 *
 * @constructor Cria uma instância de Service com os campos fornecidos.
 */

data class Service (
    val id: String,
    val providerId: String,
    val title: String,
    val description: String,
    val price: Double,
    val estimatedDuration: Int, // em minutos
    val createdAt: Long,
    val updatedAt: Long
)