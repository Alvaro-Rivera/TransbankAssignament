package cl.alvaro.transbankapp.base

interface ViewPresenter {
    fun showLoading()
    fun stopLoading()
    fun showError(errorMessage:String)
    fun hideError()
}