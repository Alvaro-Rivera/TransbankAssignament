package cl.alvaro.transbankapp.models.datasources

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieDetail(
    @Expose
    @SerializedName("adult")
    val adult: Boolean?,
    @Expose
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @Expose
    @SerializedName("belongs_to_collection")
    val belongsToCollection: BelongsToCollection?,
    @Expose
    @SerializedName("budget")
    val budget: Int?,
    @Expose
    @SerializedName("genres")
    val genres: List<Genre>?,
    @Expose
    @SerializedName("homepage")
    val homepage: String?,
    @Expose
    @SerializedName("id")
    val id: Int?,
    @Expose
    @SerializedName("imdb_id")
    val imdbId: String?,
    @Expose
    @SerializedName("original_language")
    val originalLanguage: String?,
    @Expose
    @SerializedName("original_title")
    val originalTitle: String?,
    @Expose
    @SerializedName("overview")
    val overview: String?,
    @Expose
    @SerializedName("popularity")
    val popularity: Double?,
    @Expose
    @SerializedName("poster_path")
    val posterPath: String?,
    @Expose
    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompany>?,
    @Expose
    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountry>?,
    @Expose
    @SerializedName("release_date")
    val releaseDate: String?,
    @Expose
    @SerializedName("revenue")
    val revenue: Int?,
    @Expose
    @SerializedName("runtime")
    val runtime: Int?,
    @Expose
    @SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguage>?,
    @Expose
    @SerializedName("status")
    val status: String?,
    @Expose
    @SerializedName("tagline")
    val tagLine: String?,
    @Expose
    @SerializedName("title")
    val title: String?,
    @Expose
    @SerializedName("video")
    val video: Boolean?,
    @Expose
    @SerializedName("vote_average")
    val voteAverage: Double?,
    @Expose
    @SerializedName("vote_count")
    val voteCount: Int?
)
