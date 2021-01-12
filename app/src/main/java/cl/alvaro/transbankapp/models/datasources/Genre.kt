package cl.alvaro.transbankapp.models.datasources

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Genre(
    @Expose
    @SerializedName("id")
    val id: Int?,
    @Expose
    @SerializedName("name")
    val name: String?
)
