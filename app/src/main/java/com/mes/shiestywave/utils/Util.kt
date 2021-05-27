package com.mes.shiestywave.utils

import androidx.compose.ui.graphics.Color
import com.mes.shiestywave.ui.theme.* // ktlint-disable no-wildcard-imports
import java.util.* // ktlint-disable no-wildcard-imports

fun generateLocalID(): String = UUID.randomUUID().toString()

fun getCharacterBackground(initials: String): Color =
    when (initials.first()) {
        'A', 'B', 'C', 'D', 'E' -> Group1
        'F', 'G', 'H', 'I', 'J' -> Group2
        'K', 'L', 'M', 'N', 'O' -> Group3
        'P', 'Q', 'R', 'S', 'T' -> Group4
        'U', 'V', 'W', 'X', 'Y', 'Z' -> Group5
        else -> Group6
    }
