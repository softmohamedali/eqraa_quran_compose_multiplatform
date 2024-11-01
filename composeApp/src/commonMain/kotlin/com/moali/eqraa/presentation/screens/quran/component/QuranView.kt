package com.moali.eqraa.presentation.screens.quran.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.moali.eqraa.domain.models.Juza
import com.moali.eqraa.domain.models.Soura
import com.moali.kmm_sharingresources.SharedRes
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource

val tabs = listOf(
    SharedRes.strings.sour,
    SharedRes.strings.ajza
)

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun QuranView(
    sour: List<Soura>,
    ajza: List<Juza>,
    onSouraItemClick: (Soura) -> Unit,
    onJuzaItemClick: (Juza) -> Unit,
    onBackClick: () -> Unit,
    onArchiveClick: () -> Unit,
    lang:String,
    searchQuery:String,
    isSearchTopAppBar:Boolean,
    onSearchQueryChange:(String)->Unit,
    onSearchClick:()->Unit,
    onCloseClick:()->Unit
) {

    var selectedTabIndex by remember {
        mutableStateOf(0)
    }
    val pagerState = rememberPagerState (0)

    LaunchedEffect(selectedTabIndex){
        pagerState.animateScrollToPage(selectedTabIndex)
    }
    LaunchedEffect(pagerState.currentPage){
        selectedTabIndex=pagerState.currentPage
    }
    Scaffold(
        topBar = {
            QuranTopAppbar(
                title = stringResource(SharedRes.strings.alquran_alkarem),
                searchQuery =searchQuery ,
                onBackClick = { onBackClick() },
                isBack = true,
                actions = {
                    Image(
                        modifier = Modifier.size(25.dp)
                            .clickable { onArchiveClick() },
                        painter = painterResource(SharedRes.images.bookmark),
                        contentDescription = null,
                    )
                },
                onSearchQueryChange = onSearchQueryChange,
                onCloseClick =onCloseClick,
                onSearchClick =onSearchClick,
                isSearching=isSearchTopAppBar,
            )
        }
    ) {

        Column (
            modifier = Modifier.fillMaxSize().padding(it)
        ){
            TabRow(
                selectedTabIndex = selectedTabIndex,
                ) {
                tabs.forEachIndexed { index, stringResource ->
                    Tab(
                        modifier=Modifier.background(MaterialTheme.colorScheme.background),
                        selected = index == selectedTabIndex,
                        onClick ={
                            selectedTabIndex=index
                        },
                        text = {
                            Text(text = stringResource(stringResource))
                        }
                    )
                }
            }

            HorizontalPager(
                state=pagerState,
                modifier = Modifier.weight(1f),
                pageCount = tabs.size
            ){index->
                if (pagerState.currentPage==0){
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth().padding(10.dp)
                    ) {
                        items(sour) {soura->
                            SouraItem(
                                id = soura.id.toString(),
                                name = if (lang=="ar") soura.name_ar else soura.name,
                                onClick = {
                                    onSouraItemClick(soura)
                                }
                            )
                        }
                    }
                }else{
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth().padding(10.dp)
                    ) {
                        items(ajza) {juza->
                            JuzaItem(
                                id = juza.id.toString(),
                                onClick = {
                                    onJuzaItemClick(juza)
                                }
                            )
                        }
                    }
                }
            }
        }
    }

}