package cl.alvaro.transbankapp

import org.mockito.Mockito
import java.io.InputStreamReader

fun gsonReader(fileName:String): InputStreamReader {
    val inputStream = ClassLoader
        .getSystemClassLoader()
        .getResourceAsStream(fileName)
    return InputStreamReader(inputStream)
}

fun <T> anyMockito(type: Class<T>): T = Mockito.any<T>(type)