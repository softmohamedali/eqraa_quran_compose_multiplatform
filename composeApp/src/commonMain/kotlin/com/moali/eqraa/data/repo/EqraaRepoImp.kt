package com.moali.eqraa.data.repo

import com.moali.eqraa.core.shared.utils.Dispatchers
import com.moali.eqraa.core.utils.ResultState
import com.moali.eqraa.core.utils.safeCall
import com.moali.eqraa.data.resource.QuranJsonRecourse
import com.moali.eqraa.domain.models.Soura
import com.moali.eqraa.domain.abstractions.repo.EqraaRepo
import com.moali.eqraa.domain.models.Juza

class EqraaRepoImp (
    private val qoranResource:QuranJsonRecourse,
    private val dispatcher: Dispatchers
): EqraaRepo {


    override suspend fun getQuran(): ResultState<List<Soura>> = safeCall {
            ResultState.IsSucsses(qoranResource.getQuranFromResourcesAsSoura())
    }
    override suspend fun getQuranAsJuza(): ResultState<List<Juza>> = safeCall {
            ResultState.IsSucsses(qoranResource.getQuranFromResourcesAsJuza())
    }



}