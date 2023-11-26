package com.moali.eqraa.presentation.screens.juza

sealed class JuzaEvents {
    class OnInit(val souraId:Int):JuzaEvents()
    object OnGetArchive:JuzaEvents()
    class OnAddReferenceClick(val scrollValue: Int,val souraId: Int) : JuzaEvents()


}