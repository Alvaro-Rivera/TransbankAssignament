package cl.alvaro.transbankapp.models.datasources

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Cast(
    @Expose
    @SerializedName("adult")
    val adult: Boolean?,
    @Expose
    @SerializedName("cast_id")
    val castId: Int?,
    @Expose
    @SerializedName("character")
    val character: String?,
    @Expose
    @SerializedName("credit_id")
    val creditId: String?,
    @Expose
    @SerializedName("gender")
    val gender: Int?,
    @Expose
    @SerializedName("id")
    val id: Int?,
    @Expose
    @SerializedName("known_for_department")
    val knownForDepartment: String?,
    @Expose
    @SerializedName("name")
    val name: String?,
    @Expose
    @SerializedName("order")
    val order: Int?,
    @Expose
    @SerializedName("original_name")
    val originalName: String?,
    @Expose
    @SerializedName("popularity")
    val popularity: Double?,
    @Expose
    @SerializedName("profile_path")
    val profilePath: String?
)
