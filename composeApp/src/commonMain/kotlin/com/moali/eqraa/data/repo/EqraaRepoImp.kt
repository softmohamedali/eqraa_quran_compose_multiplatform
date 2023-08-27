package com.moali.eqraa.data.repo

import com.moali.eqraa.core.shared.Dispatchers
import com.moali.eqraa.core.utils.ResultState
import com.moali.eqraa.core.utils.safeCall
import com.moali.eqraa.data.resource.QuranJsonRecourse
import com.moali.eqraa.domain.models.Soura
import com.moali.eqraa.domain.abstractions.repo.EqraaRepo

class EqraaRepoImp (
    private val qoranResource:QuranJsonRecourse,
    private val dispatcher: Dispatchers
): EqraaRepo {


    override suspend fun getQuran(): ResultState<List<Soura>> = safeCall {
            ResultState.IsSucsses(qoranResource.getQuranFromResources())
    }



}