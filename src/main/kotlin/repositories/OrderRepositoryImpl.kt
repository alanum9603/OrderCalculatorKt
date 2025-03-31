package com.ipeasa.repositories

import com.ipeasa.ddds.Order
import com.ipeasa.ddds.OrderAndDetail
import com.ipeasa.ddds.OrderProduct
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
    private val uuidService: UuidService
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

    private fun rowsToOrderAndDetail(rows: List<ResultRow>) : OrderAndDetail {
        var product = rows.first()[ProductTable.productId]
        val productList = mutableListOf<>()

        return OrderAndDetail(
            rows.first()[OrderTable.orderId].toString(),
            rows.first()[OrderTable.ruc],
            rows.first()[OrderTable.date].toString(),
            rows.first()[OrderTable.total],
            rows.first()[OrderTable.currency],
            rows.first()[OrderTable.exchange],
            rows.first()[OrderTable.address],
            rows.forEach { (row) ->
                if (product == it[ProductTable.productId]) {
                    productList.add
                } else {
                    product = ""
                }
            }
        )
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

        return transaction {
            (OrderTable leftJoin OrderProductTable leftJoin OrderProductMaterialTable)
                .selectAll()
                .where { OrderTable.orderId eq uuid }
                .andWhere { OrderTable.state eq true }
                .orderBy(OrderTable.id to SortOrder.ASC)
                .toList()
                .let { rowsToOrderAndDetail(it) }
        }
    }
}