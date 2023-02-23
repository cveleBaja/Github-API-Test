package com.cvetkovicmilan.githubapitest.data.mapper

interface DomainMapper<T, DomainModel>{

    fun mapToDomainModel(model: T): DomainModel

    fun mapFromDomainModel(domainModel: DomainModel): T
}