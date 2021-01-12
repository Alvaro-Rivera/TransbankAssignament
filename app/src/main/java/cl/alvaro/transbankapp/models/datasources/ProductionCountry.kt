package cl.alvaro.transbankapp.models.datasources

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProductionCountry(
    @Expose
    @SerializedName("iso_3166_1")
    val iso31661: String?,
    @Expose
    @SerializedName("name")
    val name: String?
)
