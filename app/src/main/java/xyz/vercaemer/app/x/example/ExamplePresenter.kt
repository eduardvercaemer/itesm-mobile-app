package xyz.vercaemer.app.x.example

import xyz.vercaemer.app.state.Dispatcher
import xyz.vercaemer.app.state.State

/// The presenter mutates the view model based on current state
class ExamplePresenter(
    private val dispatcher: Dispatcher,
    private val viewModel: ExampleViewModel
) : (State<Example, Unit>) -> Unit {
    override fun invoke(state: State<Example, Unit>) {
        dispatcher.dispatch {
            when (state.name) {
                State.Name.IDLE -> {}
                State.Name.LOADING -> {}
                State.Name.LOADED -> {}
                State.Name.ERROR -> {}
            }

            viewModel.notifyObserver()
        }
    }
}