package com.example.blockchain.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * @author Leonardo Martins on 15/11/20
 */
abstract class BaseActivity<PresenterType> : AppCompatActivity(), BaseContract.View
        where PresenterType : BaseContract.Presenter {

    abstract val presenter: PresenterType
    abstract val layoutRes: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(layoutRes)
        initialize()
    }

    abstract fun initialize()

    override fun onStop() {
        presenter.interrupt()
        super.onStop()
    }

    override fun onDestroy() {
        presenter.detach()
        super.onDestroy()
    }
}
