package cl.alvaro.transbankapp.models.datasources

data class MovieCredits(
    val cast: List<Cast>?,
    val crew: List<Crew>?,
    val id: Int?
)
