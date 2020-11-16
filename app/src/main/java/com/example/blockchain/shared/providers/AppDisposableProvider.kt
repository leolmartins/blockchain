package com.example.blockchain.shared.providers

import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

/**
 * @author Leonardo Martins on 15/11/20
 */
class AppDisposableProvider : DisposableProvider {

    private val disposable = CompositeDisposable()

    override fun add(disposable: Disposable) {
        this.disposable.add(disposable)
    }

    override fun clear() {
        this.disposable.clear()
    }

    override fun dispose() {
        this.disposable.dispose()
    }
}
