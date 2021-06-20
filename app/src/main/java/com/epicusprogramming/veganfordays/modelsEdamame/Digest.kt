package com.epicusprogramming.veganfordays.modelsEdamame

data class Digest(
    val daily: Int,
    val hasRDI: Boolean,
    val label: String,
    val schemaOrgTag: String,
    val sub: List<Sub>,
    val tag: String,
    val total: Int,
    val unit: String
)