package cl.alvaro.transbankapp.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class RxBase {
    private val disposables = CompositeDisposable()

    fun addSubscription(disposable: Disposable) {
        disposables.add(disposable)
    }

    fun unSubscribeAll() {
        if (!disposables.isDisposed) {
            disposables.dispose()
        }
    }
}