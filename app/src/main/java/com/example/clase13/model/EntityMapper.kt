package com.example.clase13.model

interface EntityMapper<T,V> {

    fun mapFromCached(type: T): V

    fun mapToCached(type: V): T
}