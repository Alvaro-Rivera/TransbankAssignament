package cl.alvaro.transbankapp.models.datasources

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProductionCompany(
    @Expose
    @SerializedName("id")
    val id: Int?,
    @Expose
    @SerializedName("logo_path")
    val logoPath: String?,
    @Expose
    @SerializedName("name")
    val name: String?,
    @Expose
    @SerializedName("origin_country")
    val originCountry: String?
)
