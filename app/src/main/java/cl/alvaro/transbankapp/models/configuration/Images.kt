package cl.alvaro.transbankapp.models.configuration

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Images(
    @Expose
    @SerializedName("backdrop_sizes")
    val backdropSizes: List<String>?,
    @Expose
    @SerializedName("base_url")
    val baseUrl: String?,
    @Expose
    @SerializedName("logo_sizes")
    val logoSizes: List<String>?,
    @Expose
    @SerializedName("poster_sizes")
    val posterSizes: List<String>?,
    @Expose
    @SerializedName("profile_sizes")
    val profileSizes: List<String>?,
    @Expose
    @SerializedName("secure_base_url")
    val secureBaseUrl: String?,
    @Expose
    @SerializedName("still_sizes")
    val stillSizes: List<String>?
)

