package com.moali.eqraa.data.resource

import co.touchlab.kermit.Logger
import com.moali.eqraa.domain.models.Aya
import com.moali.eqraa.domain.models.Soura
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.resource

class QuranJsonRecourse {

    @OptIn(ExperimentalResourceApi::class)
    suspend fun getQuranFromResources():List<Soura>{
        val str = resource("assets/quran.json").readBytes().decodeToString()
        val json=Json.parseToJsonElement(str).jsonArray
        val quran= mutableListOf<Soura>()
        val sour= mutableListOf<Aya>()
            for (i in 0 until json.size)
            {
                val jsonObject=json.get(i).jsonObject
                sour.add(
                    Aya(
                        jsonObject["sura_id"].toString().toInt(),
                        jsonObject["aya_id"].toString().toInt(),
                        jsonObject["sura_name"].toString().drop(1).dropLast(1),
                        jsonObject["standard_full"].toString().drop(1).dropLast(1)
                    )
                )
            }
            for (i in 1..114)
            {
                val soura= Soura(1, "", mutableListOf())
                for (j in sour)
                {
                    if (j.sura_id==i)
                    {
                        if (j.sura_id==i)
                        {
                            soura.name=j.sura_name
                            soura.id=j.sura_id
                        }
                        soura.soura.add(j)
                    }
                }
                quran.add(soura)
            }

        return quran
    }


}




