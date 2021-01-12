package cl.alvaro.transbankapp.extensions

import cl.alvaro.transbankapp.models.datasources.Cast
import cl.alvaro.transbankapp.models.datasources.Crew
import cl.alvaro.transbankapp.repository.MovieRepositoryImpl.Companion.NOT_AVAILABLE_SHORT

fun String.formatStringDate(): String {
    val dateArray = this.split("-")
    return if (dateArray.size == 3) {
        "${dateArray[2]}/${dateArray[1]}/${dateArray[0]}"
    } else {
        NOT_AVAILABLE_SHORT
    }
}

fun List<Crew>.getDirectorsString(): String {
    return this
        .filter { it.department == "Directing" && it.job == "Director" }
        .map { it.name }
        .joinToString()
}

fun List<Cast>.getCastString(): String {
    return this.map {it.name}.joinToString()
}