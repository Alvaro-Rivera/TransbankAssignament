package cl.alvaro.transbankapp.models.configuration

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ConfigurationMovieDb(
    @Expose
    @SerializedName("change_keys")
    val changeKeys: List<String>?,
    @Expose
    @SerializedName("images")
    val images: Images?
)
