package cl.alvaro.transbankapp.models.datasources

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SpokenLanguage(
    @Expose
    @SerializedName("english_name")
    val englishName: String?,
    @Expose
    @SerializedName("iso_639_1")
    val iso6391: String?,
    @Expose
    @SerializedName("name")
    val name: String?
)
