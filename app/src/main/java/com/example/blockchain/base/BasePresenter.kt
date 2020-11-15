package com.example.blockchain.base

import com.example.blockchain.shared.providers.DisposableProvider
import com.example.blockchain.shared.providers.SchedulerProvider

/**
 * @author Leonardo Martins on 15/11/20
 */
abstract class BasePresenter<ViewType : BaseContract.View, InteractorType>(
    protected val view: ViewType,
    protected val interactor: InteractorType,
    protected val disposable: DisposableProvider,
    protected val schedulerProvider: SchedulerProvider
) : BaseContract.Presenter {

    override fun interrupt() {
        disposable.clear()
    }

    override fun detach() {
        disposable.dispose()
    }
}
