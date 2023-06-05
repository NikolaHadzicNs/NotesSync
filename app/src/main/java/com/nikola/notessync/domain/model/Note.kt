package com.nikola.notessync.domain.model

data class Note(
    var title: String,
    var content: String,
    var font: Font? = null,
    var theme: Theme? = null
)