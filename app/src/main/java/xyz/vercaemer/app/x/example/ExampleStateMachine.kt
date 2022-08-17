package xyz.vercaemer.app.x.example

import xyz.vercaemer.app.state.DefaultErrorFactory
import xyz.vercaemer.app.state.Dispatcher
import xyz.vercaemer.app.state.StateMachine

class ExampleStateMachine(
    private val dispatcher: Dispatcher,
) : ExampleController,
    StateMachine<Example, Unit>(errorFactory = DefaultErrorFactory()) {

    override fun start() {
        loadExample()
    }

    override fun loadExample() {
        dispatcher.dispatch({
            moveToLoading()
            moveToLoaded(Example("This is an example string!"))
        }, {
            moveToError(it)
        })
    }
}