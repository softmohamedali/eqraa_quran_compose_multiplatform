package com.moali.eqraa.presentation.screens.juza

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.moali.eqraa.core.shared.services.ServicesUtils
import com.moali.eqraa.core.shared.utils.Dispatchers
import com.moali.eqraa.core.utils.Constants
import com.moali.eqraa.core.utils.Constants.ARCHIVE_SCROLL_POSITION_KEY
import com.moali.eqraa.core.utils.Constants.ARCHIVE_ID_KEY
import com.moali.eqraa.core.utils.Constants.ARCHIVE_TYPE_JUZA
import com.moali.eqraa.core.utils.Constants.ARCHIVE_TYPE_SURA
import com.moali.eqraa.core.utils.Constants.ARCHIVE_TYPE_SURA_JUZA_KEY
import com.moali.eqraa.core.utils.ResultState
import com.moali.eqraa.domain.usecases.GetQuranAsJuzaUseCase
import com.russhwolf.settings.Settings
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class JuzaViewModel: ViewModel(), KoinComponent {
    private val getQuranJuzaUseCase: GetQuranAsJuzaUseCase by inject()
    private val dispatchers: Dispatchers by inject()
    private val  settings: Settings by inject()
    private val servicesUtils: ServicesUtils by inject()
    var state by mutableStateOf(JuzaState())

    init {
        state=state.copy(lang = servicesUtils.getCurrentLanguage())
    }

    fun onEvent(events: JuzaEvents) {
        when (events) {
            is JuzaEvents.OnInit -> {
                state = state.copy(
                    juzaId = events.souraId,
                    isLoading = true
                )
                getQuranAsJuza()
            }
            is JuzaEvents.OnGetArchive->{
                state = state.copy(
                    juzaId = settings.getInt(ARCHIVE_ID_KEY,1),
                    isLoading = true
                )
                getQuranScrollPostion()
            }

            is JuzaEvents.OnAddReferenceClick->{
                settings.putInt(ARCHIVE_SCROLL_POSITION_KEY,events.scrollValue)
                settings.putInt(ARCHIVE_ID_KEY,events.juzaId)
                settings.putString(ARCHIVE_TYPE_SURA_JUZA_KEY,ARCHIVE_TYPE_JUZA)
            }

        }
    }

    private fun getQuranScrollPostion() {
        viewModelScope.launch(dispatchers.main) {
            getQuranJuzaUseCase().collect{
                when{
                    it is ResultState.IsSucsses ->{
                        state=state.copy(
                            juza = it.data!![state.juzaId-1],
                            scrollPotion = settings.getInt(ARCHIVE_SCROLL_POSITION_KEY,0),
                            isLoading = false
                        )
                    }
                    it is ResultState.IsLoading ->{

                    }
                    it is ResultState.IsError ->{

                    }
                }

            }
        }
    }

    private fun getQuranAsJuza(){
        viewModelScope.launch(dispatchers.main) {
            getQuranJuzaUseCase().collect{
                when{
                    it is ResultState.IsSucsses ->{
                        state=state.copy(
                            juza = it.data!![state.juzaId-1],
                            isLoading = false
                        )

                    }
                    it is ResultState.IsLoading ->{

                    }
                    it is ResultState.IsError ->{

                    }
                }

            }
        }
    }






}