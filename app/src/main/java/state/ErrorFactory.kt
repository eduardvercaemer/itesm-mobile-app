package state

interface ErrorFactory<E> {
    fun create(throwable: Throwable): E
}