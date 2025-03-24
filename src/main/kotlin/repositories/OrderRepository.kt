package com.ipeasa.repositories

import com.ipeasa.ddds.Order
import com.ipeasa.ddds.OrderAndDetail

interface OrderRepository {
    fun getOrdersByRuc(ruc : String, pageSize : Int, page : Long) : List<Order>

    fun getOrderByUuid(id : String) : OrderAndDetail
}