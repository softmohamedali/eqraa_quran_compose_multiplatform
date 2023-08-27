package com.moali.eqraa.domain.abstractions.remote

interface QuranAudioService {
    suspend fun getSoura(id:String)
}