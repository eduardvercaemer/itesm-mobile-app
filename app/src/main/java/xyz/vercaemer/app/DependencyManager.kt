package xyz.vercaemer.app

import xyz.vercaemer.app.x.example.ExampleController
import xyz.vercaemer.app.x.example.ExampleViewModel

interface DependencyManager {
    val exampleController: ExampleController
    val exampleViewModel: ExampleViewModel
}