package com.github.carvaldo.fimo.api.datasource.repository

import com.github.carvaldo.fimo.api.datasource.repository.local.dao.PersonDao
import com.github.carvaldo.fimo.api.datasource.repository.local.entity.Person
import com.github.carvaldo.fimo.api.datasource.repository.local.entity.transform
import com.github.carvaldo.fimo.api.datasource.repository.service.ServiceGenerator
import com.github.carvaldo.fimo.api.datasource.repository.service.case.PersonUseCase
import com.github.carvaldo.fimo.api.datasource.repository.service.imdb.entity.Person as PersonImdb
import com.github.carvaldo.fimo.api.datasource.repository.service.imdb.service.PersonService
import com.github.carvaldo.fimo.api.exception.LimitReachedException
import org.apache.logging.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import retrofit2.Response
import javax.servlet.UnavailableException

/**
 * Busca dados prioritamente na base local. Em caso de dados inexistentes, a busca é efetuada em outras fontes.
 */

@Service
class PersonRepository (
    private val dao: PersonDao,
    private val useCase: PersonUseCase
){
    @Autowired
    private lateinit var logger: Logger

    // TODO: Definir validade dos dados para atualização.
    fun getProfile(apiId: String): Person? = dao.findPersonByApiId(apiId)
        ?: useCase.getPersonFromImdb(apiId).transform().let { dao.save(it) }
}