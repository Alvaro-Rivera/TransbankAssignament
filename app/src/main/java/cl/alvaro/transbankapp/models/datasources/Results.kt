package cl.alvaro.transbankapp.models.datasources

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Results(
    @Expose
    @SerializedName("page")
    val page:Int?,
    @Expose
    @SerializedName("results")
    val results: List<MovieResult>?,
    @Expose
    @SerializedName("total_pages")
    val totalPages: Int?,
    @Expose
    @SerializedName("total_results")
    val totalResults: Int?
)
