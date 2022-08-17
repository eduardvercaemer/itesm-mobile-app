package xyz.vercaemer.app.x.example

import xyz.vercaemer.app.state.ViewModel

/// The view model contains the data for UI presentation.
data class ExampleViewModel(
    val showText: Boolean = false,
    val text: String = ""
) : ViewModel()