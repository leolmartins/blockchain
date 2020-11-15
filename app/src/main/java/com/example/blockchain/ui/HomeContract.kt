package com.example.blockchain.ui

import com.example.blockchain.base.BaseContract

/**
 * @author Leonardo Martins on 15/11/20
 */
interface HomeContract: BaseContract {

    interface View: BaseContract.View

    interface Presenter: BaseContract.Presenter

    interface Interactor: BaseContract.Interactor
}
