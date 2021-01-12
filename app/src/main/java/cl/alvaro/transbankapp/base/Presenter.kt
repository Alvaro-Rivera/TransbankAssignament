package cl.alvaro.transbankapp.base

interface Presenter<V:ViewPresenter> {
    fun attachView(view:V)
    fun removeView()
}