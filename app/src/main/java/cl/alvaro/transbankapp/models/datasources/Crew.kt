package cl.alvaro.transbankapp.models.datasources

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Crew(
    @Expose
    @SerializedName("adult")
    val adult: Boolean?,
    @Expose
    @SerializedName("credit_id")
    val creditId: String?,
    @Expose
    @SerializedName("department")
    val department: String?,
    @Expose
    @SerializedName("gender")
    val gender: Int?,
    @Expose
    @SerializedName("id")
    val id: Int?,
    @Expose
    @SerializedName("job")
    val job: String?,
    @Expose
    @SerializedName("known_for_department")
    val knownForDepartment: String?,
    @Expose
    @SerializedName("name")
    val name: String?,
    @Expose
    @SerializedName("originalName")
    val originalName: String?,
    @Expose
    @SerializedName("popularity")
    val popularity: Double?,
    @Expose
    @SerializedName("profile_path")
    val profilePath: String?
)
