package com.jmenmar.githubusers.ui.screens.home

sealed interface HomeEvent {
    data class ChangeQuery(val query: String): HomeEvent
    data object ClearQuery: HomeEvent
}