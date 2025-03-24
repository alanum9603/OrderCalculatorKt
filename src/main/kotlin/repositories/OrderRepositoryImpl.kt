package com.ipeasa.repositories

import com.ipeasa.ddds.Order
import com.ipeasa.ddds.OrderAndDetail
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

    private fun rowToOrderAndDetail(row: ResultRow) : Order {
        return OrderAndDetail(
            row[OrderTable.orderId].toString(),
            row[OrderTable.ruc],
            row[OrderTable.date].toString(),
            row[OrderTable.total],
            row[OrderTable.currency],
            row[OrderTable.exchange],
            row[OrderTable.address],

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

    override fun getOrderByUuid(id: String): OrderAndDetail {
        val uuid = uuidService.toValidUuid(id)

        return transaction {
            (OrderTable leftJoin OrderProductTable leftJoin OrderProductMaterialTable)
                .selectAll()
                .where { OrderTable.orderId eq uuid}
                .orderBy(OrderTable.id to SortOrder.ASC)
                .firstOrNull()
                ?.let {  }
        }
        TODO("Not yet implemented")
    }
}