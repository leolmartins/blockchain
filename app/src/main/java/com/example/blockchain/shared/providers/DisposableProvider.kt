package com.example.blockchain.shared.providers

import io.reactivex.rxjava3.disposables.CompositeDisposable

/**
 * @author Leonardo Martins on 15/11/20
 */
interface DisposableProvider {

    fun add(disposable: CompositeDisposable)

    fun clear()

    fun dispose()
}
