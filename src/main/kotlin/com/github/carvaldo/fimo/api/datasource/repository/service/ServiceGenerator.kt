package com.github.carvaldo.fimo.api.datasource.repository.service

import com.google.gson.*
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Service generator
 *
 */
class ServiceGenerator {
    companion object {
        const val API_KEY = "k_bljoq3nq"
        const val PRINCING_URL_IMDB_API = "https://imdb-api.com/pricing"

        private var client = OkHttpClient.Builder()
            .also {
                it.connectTimeout(60, TimeUnit.SECONDS)
                it.readTimeout(60, TimeUnit.SECONDS)
            }.build()
        var retrofit: Retrofit = Retrofit.Builder().also {
            it.baseUrl("https://imdb-api.com/pt-BR/API/")
            it.client(client)
            it.addConverterFactory(
                GsonConverterFactory.create(GsonBuilder()
                    .registerTypeAdapter(Date::class.java, DateDeserializer())
                    .enableComplexMapKeySerialization().create())
            )
        }.build()

        inline fun <reified T> create(): T = retrofit.create(T::class.java)
    }

    /**
     * Date deserializer to avoid breaking in case of empty date field.
     */
    private class DateDeserializer: JsonDeserializer<Date?> {
        @Throws(JsonParseException::class)

        override fun deserialize(
            json: JsonElement?,
            typeOfT: Type?,
            context: JsonDeserializationContext?
        ): Date? {
            try {
                if (!json?.asString.isNullOrBlank()) {
                    return SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(json!!.asString)
                }
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            return null
        }
    }
}