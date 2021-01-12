package cl.alvaro.transbankapp.base

open class BasePresenter<V:ViewPresenter>: Presenter<V> {
    protected var view:V? = null

    override fun attachView(view: V) {
        this.view = view
    }

    override fun removeView() {
        view = null
    }
}