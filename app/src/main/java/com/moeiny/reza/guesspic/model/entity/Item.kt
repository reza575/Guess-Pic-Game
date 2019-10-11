package com.moeiny.reza.guesspic.model.entity

data class Item(
    val correctAnswerIndex: Int,
    val headlines: List<String>,
    val imageUrl: String,
    val section: String,
    val standFirst: String,
    val storyUrl: String
)