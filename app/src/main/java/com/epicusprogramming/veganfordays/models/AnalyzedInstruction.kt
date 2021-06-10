package com.epicusprogramming.veganfordays.models

import java.io.Serializable

data class AnalyzedInstruction(
//    name not needed for type converter for room
    val name: String,
    val steps: List<Step>
): Serializable