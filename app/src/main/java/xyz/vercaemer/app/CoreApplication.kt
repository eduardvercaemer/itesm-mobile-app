package xyz.vercaemer.app

import android.util.Log
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import xyz.vercaemer.app.rxjava.RxJavaDispatcherFactory
import xyz.vercaemer.app.state.Dispatcher
import xyz.vercaemer.app.x.example.ExampleController
import xyz.vercaemer.app.x.example.ExamplePresenter
import xyz.vercaemer.app.x.example.ExampleStateMachine
import xyz.vercaemer.app.x.example.ExampleViewModel

class CoreApplication private constructor() : DependencyManager {
    private fun start() {
        Log.d("CoreApplication", "start")
    }

    override val exampleController: ExampleController by lazy {
        exampleStateMachine
    }

    override val exampleViewModel: ExampleViewModel by lazy {
        val exampleViewModel = ExampleViewModel()
        exampleStateMachine.addStateChangedListener(
            ExamplePresenter(
                mainDispatcher,
                exampleViewModel
            )
        )
        exampleViewModel
    }

    private val exampleStateMachine: ExampleStateMachine by lazy {
        // this initializes the state machine on the main thread
        ExampleStateMachine(mainDispatcher)
        // this initializes the state machine on a background thread
        //ExampleStateMachine(dispatcherFactory.createSerialDispatcher("example"))
    }

    private val mainDispatcher: Dispatcher by lazy {
        dispatcherFactory.createMainDispatcher()
    }

    private val dispatcherFactory: DispatcherFactory by lazy {
        RxJavaDispatcherFactory(publishScheduler = AndroidSchedulers.mainThread())
    }

    class Builder {
        /*
            Use this builder to defined injected dependencies

            private var ___ : Lazy<___>? = null

            fun register___(___ : Lazy<___>): Builder {
                this.___ = ___
                return this
            }
         */

        fun start(): CoreApplication {
            val application = CoreApplication()
            application.start()
            return application
        }
    }
}