package com.moali.eqraa.data.resource

import com.moali.eqraa.domain.models.Aya
import com.moali.eqraa.domain.models.Juza
import com.moali.eqraa.domain.models.Soura
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.resource

class QuranJsonRecourse {

    @OptIn(ExperimentalResourceApi::class)
    suspend fun getQuranFromResourcesAsSoura():List<Soura>{
        val str = resource("assets/quran.json").readBytes().decodeToString()
        val json=Json.parseToJsonElement(str).jsonArray
        val quran= mutableListOf<Soura>()
        val sour= mutableListOf<Aya>()
            for (i in 0 until json.size)
            {
                val jsonObject=json.get(i).jsonObject
                sour.add(
                    Aya(
                        sura_id=jsonObject["sura_id"].toString().toInt(),
                        aya_id=jsonObject["aya_id"].toString().toInt(),
                        aya_id_ar=jsonObject["aya_id_display"].toString(),
                        sura_name=jsonObject["sura_name_en"].toString().drop(1).dropLast(1),
                        standard_full=jsonObject["standard_full"].toString().drop(1).dropLast(1),
                        juz_id=jsonObject["juz_id"].toString().toInt(),
                        sura_name_ar =jsonObject["sura_name"].toString().drop(1).dropLast(1),
                    )
                )
            }
            for (i in 1..114)
            {
                val soura= Soura(1, "", "",mutableListOf())
                for (j in sour)
                {
                    if (j.sura_id==i)
                    {
                        soura.name_ar=j.sura_name_ar
                        soura.name=j.sura_name
                        soura.id=j.sura_id
                        soura.soura.add(j)
                    }
                }
                quran.add(soura)
            }

        return quran
    }


    @OptIn(ExperimentalResourceApi::class)
    suspend fun getQuranFromResourcesAsJuza():List<Juza>{
        val str = resource("assets/quran.json").readBytes().decodeToString()
        val json=Json.parseToJsonElement(str).jsonArray
        val quranAjza= mutableListOf<Juza>()
        val ayat= mutableListOf<Aya>()
        for (i in 0 until json.size)
        {
            val jsonObject=json.get(i).jsonObject
            ayat.add(
                Aya(
                    sura_id=jsonObject["sura_id"].toString().toInt(),
                    aya_id=jsonObject["aya_id"].toString().toInt(),
                    aya_id_ar=jsonObject["aya_id_display"].toString(),
                    sura_name=jsonObject["sura_name_en"].toString().drop(1).dropLast(1),
                    standard_full=jsonObject["standard_full"].toString().drop(1).dropLast(1),
                    juz_id=jsonObject["juz_id"].toString().toInt(),
                    sura_name_ar =jsonObject["sura_name"].toString().drop(1).dropLast(1),
                )
            )
        }

        // Group by juz_id
        val groupedByJuz = ayat.groupBy { it.juz_id }
        // Iterate over each juz group
        for ((juzId, ayatInJuz) in groupedByJuz) {
            val juza=Juza(juzId, mutableListOf())
            // Group by sura_id within each juz group
            val groupedBySura = ayatInJuz.groupBy { it.sura_id }
            // Iterate over each sura group within the juz
            for ((suraId, ayatInSura) in groupedBySura) {
                val soura=Soura(
                    id = suraId,
                    name_ar = ayatInSura[0].sura_name_ar,
                    name =  ayatInSura[0].sura_name,
                    soura = ayatInSura as MutableList<Aya>
                )
                juza.sour.add(soura)
            }
            quranAjza.add(juza)
        }
        return quranAjza
    }
}




