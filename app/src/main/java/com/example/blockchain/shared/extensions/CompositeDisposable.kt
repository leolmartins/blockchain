package com.example.blockchain.shared.extensions

import com.example.blockchain.shared.providers.DisposableProvider
import io.reactivex.rxjava3.disposables.Disposable

/**
 * @author Leonardo Martins on 15/11/20
 */

fun Disposable.addTo(disposableProvider: DisposableProvider) = apply { disposableProvider.add(this) }
