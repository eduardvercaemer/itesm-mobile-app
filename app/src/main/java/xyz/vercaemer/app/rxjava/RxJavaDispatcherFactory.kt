package xyz.vercaemer.app.rxjava

import android.os.HandlerThread
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import xyz.vercaemer.app.DispatcherFactory
import xyz.vercaemer.app.state.Dispatcher


class RxJavaDispatcherFactory(
    private val publishScheduler: Scheduler
) : DispatcherFactory {
    override fun createSerialDispatcher(name: String): Dispatcher {
        val thread = HandlerThread(name)
        thread.start()
        return RxJavaDispatcher(AndroidSchedulers.from(thread.looper))
    }

    override fun createMainDispatcher(): Dispatcher = RxJavaDispatcher(
        publishScheduler
    )
}