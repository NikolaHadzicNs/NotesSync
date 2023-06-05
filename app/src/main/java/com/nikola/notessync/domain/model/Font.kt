package com.nikola.notessync.domain.model

data class Font(
    var color: String,
    var style: FontStyle
    )

enum class FontStyle {
    ITALIC,
    BOLD
}