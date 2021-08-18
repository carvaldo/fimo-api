package com.github.carvaldo.fimo.api.config

import com.github.carvaldo.fimo.api.datasource.repository.service.ServiceGenerator
import com.github.carvaldo.fimo.api.datasource.repository.service.imdb.service.MovieService
import com.github.carvaldo.fimo.api.datasource.repository.service.imdb.service.PersonService
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

// TODO: 09/08/2021 Implementar relacionamentos. 
// TODO: 09/08/2021 Melhorar scrapping para atualizar base de dados.

@Configuration
class BeanConfig {
    @Bean
    fun movieService(): MovieService = ServiceGenerator.create()

    @Bean
    fun personService(): PersonService = ServiceGenerator.create()

    @Bean
    fun logger(): Logger = LogManager.getLogger()
}