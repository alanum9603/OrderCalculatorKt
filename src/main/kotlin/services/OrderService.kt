package com.ipeasa.services

import com.ipeasa.ddds.OrderAndDetail

interface OrderService {
    fun readOrdersByRuc(pageSize : Int, page : Long) : List<OrderAndDetail>
}