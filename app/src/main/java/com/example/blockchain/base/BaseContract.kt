package com.example.blockchain.base

/**
 * @author Leonardo Martins on 15/11/20
 */
interface BaseContract {

    interface View

    interface Presenter {

        fun interrupt()

        fun detach()
    }

    interface Interactor
}
