package com.ipeasa.repositories

import com.ipeasa.ddds.*
import com.ipeasa.exceptions.ObjectNotFoundException
import com.ipeasa.models.OrderProductMaterialTable
import com.ipeasa.models.OrderProductTable
import com.ipeasa.models.OrderTable
import com.ipeasa.models.ProductTable
import com.ipeasa.utils.UuidService
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.andWhere
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class OrderRepositoryImpl(
    private val uuidService: UuidService,
    private val productRepository: ProductRepository
) : OrderRepository {
    private fun rowToOrder(row: ResultRow) : Order {
        return Order(
            row[OrderTable.orderId].toString(),
            row[OrderTable.ruc],
            row[OrderTable.date].toString(),
            row[OrderTable.total],
            row[OrderTable.currency],
            row[OrderTable.exchange],
            row[OrderTable.address]
        )
    }

    private fun rowToOrderProduct(productUuid: String, quantity: Double) : OrderProduct? {
        val product = productRepository.getProductByUuid(productUuid)
        return product?.let {
            return OrderProduct(
                product,
                quantity
            )
        }
    }

    private fun rowsToOrderAndDetail(rows: List<ResultRow>) : OrderAndDetail? {
        val products = mutableListOf<OrderProduct>()

        rows.forEach {
            val product: OrderProduct? = rowToOrderProduct(it[ProductTable.productId].toString(), it[OrderProductTable.quantity])
            product?.let {
                products.add(product)
            }
        }

        return if (rows.isNotEmpty()) {
            OrderAndDetail(
                rows.first()[OrderTable.orderId].toString(),
                rows.first()[OrderTable.ruc],
                rows.first()[OrderTable.date].toString(),
                rows.first()[OrderTable.total],
                rows.first()[OrderTable.currency],
                rows.first()[OrderTable.exchange],
                rows.first()[OrderTable.address],
                products
            )
        } else {
            null
        }

    }

    override fun getOrdersByRuc(ruc: String, pageSize: Int, page: Long) : List<Order> {
        return transaction {
            OrderTable
                .selectAll()
                .where { OrderTable.ruc eq ruc }
                .andWhere { OrderTable.state eq true }
                .orderBy(OrderTable.id to SortOrder.ASC)
                .limit(pageSize).offset((page - 1) * pageSize)
                .toList()
                .map { rowToOrder(it) }
        }
    }

    override fun getOrderByUuid(id: String): OrderAndDetail? {
        val uuid = uuidService.toValidUuid(id)

        val order =  transaction {
            (OrderTable leftJoin OrderProductTable)
                .selectAll()
                .where { OrderTable.orderId eq uuid }
                .andWhere { OrderTable.state eq true }
                .orderBy(OrderTable.id to SortOrder.ASC)
                .toList()
                .let { rowsToOrderAndDetail(it) }
        }

        return order?: throw ObjectNotFoundException("orden $id")
    }
}