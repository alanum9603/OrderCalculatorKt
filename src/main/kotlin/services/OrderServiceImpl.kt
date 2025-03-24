package com.ipeasa.services

import com.ipeasa.ddds.OrderAndDetail
import com.ipeasa.repositories.OrderRepository

class OrderServiceImpl(
    orderRepository: OrderRepository
) : OrderService {
    override fun readOrdersByRuc(pageSize: Int, page: Long): List<OrderAndDetail> {
        TODO("Not yet implemented")
    }
}