package xyz.vercaemer.app

import xyz.vercaemer.app.state.Dispatcher

interface DispatcherFactory {
    fun createSerialDispatcher(name: String): Dispatcher
    fun createMainDispatcher(): Dispatcher
}