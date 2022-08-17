package xyz.vercaemer.app.rxjava

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import xyz.vercaemer.app.state.Dispatcher

class RxJavaDispatcher(
    private val scheduler: Scheduler,
    private val disposable: CompositeDisposable = CompositeDisposable()
) : Dispatcher {

    override fun dispatch(execute: () -> Unit, error: (Throwable) -> Unit) {
        disposable.add(
            Completable.fromCallable(execute)
                .subscribeOn(scheduler)
                .subscribe({}, error)
        )
    }

    override fun dispatch(execute: () -> Unit) {
        dispatch(execute, {})
    }
}