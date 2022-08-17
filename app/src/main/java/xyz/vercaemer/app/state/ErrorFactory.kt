package xyz.vercaemer.app.state

interface ErrorFactory<E> {
    fun create(throwable: Throwable): E

}

class DefaultErrorFactory : ErrorFactory<Unit> {
    override fun create(throwable: Throwable): Unit {
        throwable.printStackTrace()
    }
}
