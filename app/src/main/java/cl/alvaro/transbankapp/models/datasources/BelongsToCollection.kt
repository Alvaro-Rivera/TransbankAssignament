package cl.alvaro.transbankapp.models.datasources

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BelongsToCollection(
    @Expose
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @Expose
    @SerializedName("id")
    val id: Int?,
    @Expose
    @SerializedName("name")
    val name: String?,
    @Expose
    @SerializedName("poster_path")
    val posterPath: String?
)
