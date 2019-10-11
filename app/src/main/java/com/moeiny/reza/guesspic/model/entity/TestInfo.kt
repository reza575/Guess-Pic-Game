package com.moeiny.reza.guesspic.model.entity

data class TestInfo(
    val items: List<Item>,
    val product: String,
    val resultSize: Int,
    val version: Int
)