package com.example.fitapp.domain.model

sealed class CardModel {
    data class Card(val title: String, val count: String, val icon: Int) : CardModel()
}