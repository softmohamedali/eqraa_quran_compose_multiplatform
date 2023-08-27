package com.moali.eqraa.data.remote

import com.moali.eqraa.core.utils.Constants
import com.moali.eqraa.domain.abstractions.remote.QuranAudioService
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.url

class QuranAudioServiceImp(
    private val client:HttpClient
):QuranAudioService {
    override suspend fun getSoura(id:String) {
         client.get {
             url(Constants.AFISI_SOUR+"/${id}.mp3")
        }
    }
}