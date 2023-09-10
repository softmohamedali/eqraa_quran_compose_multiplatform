package com.moali.eqraa.core.services.floating_services.floating_component

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.PixelFormat
import android.util.Log
import android.view.MotionEvent
import android.view.WindowManager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.unit.IntOffset
import com.moali.eqraa.core.services.floating_services.SebhaFloatingServices
import com.moali.eqraa.domain.abstractions.local.DataStoreOper
import com.moali.eqraa.presentation.screens.sebha.component.FloatingSebhaContent
import com.torrydo.screenez.ScreenEz
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.math.max
import kotlin.math.min

class OverlayController(
    private val service: SebhaFloatingServices,
):KoinComponent{
    private val sebhaFloatingState = SebhaFloatingState()
    val configurationChanged = MutableSharedFlow<Unit>()
    val pref: DataStoreOper by inject()
    private val windowManager = service.getSystemService(Context.WINDOW_SERVICE) as WindowManager

    init {
        initViewControllers()

    }

    private fun initViewControllers() {
        val sebha=createCountdownClickTarget()
        service.scope.launch(Dispatchers.Main){
            pref.getSebhaPrefAsFlow().collect {
                sebhaFloatingState.counter.value=it?:0

            }
        }
        OverlayViewController(
            sebha,
            sebhaFloatingState.overlayState.isVisible.filterNotNull(),
            windowManager,
            service.scope
        )
        OverlayViewController(
            createFullscreenOverlay {
                FullTrachDraggingOverlay(sebhaFloatingState.overlayState)
            },
            sebhaFloatingState.overlayState.isDragging,
            windowManager, service.scope
        )
    }


    private fun createFullscreenOverlay(overlayContent: @Composable () -> Unit): OverlayViewHolder {
        val fullscreenOverlay = OverlayViewHolder(
            WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
//        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE or
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT
            ), service
        )

        fullscreenOverlay.view.setContent {
            overlayContent()
        }

        return fullscreenOverlay
    }

    private fun createCountdownClickTarget(): OverlayViewHolder {
        return createFloatingSebha(
            sebhaFloatingState.overlayState,
            floatingView = {
                FloatingSebhaContent(
                    width = TIMER_SIZE_PX * 2,
                    onclick = {
                        service.scope.launch {
                            pref.saveSebhaCounter(pref.getSebhaCounter()?.plus(1) ?: 1)
                        }
                    },
                    count = sebhaFloatingState.counter.value
                )
            },
            exitFloating = { exitCountdown() },
        )
    }

    private fun createFloatingSebha(
        overlayState: OverlayState,
        floatingView: @Composable () -> Unit,
        exitFloating: () -> Unit,
    ): OverlayViewHolder {
        val viewHolder = OverlayViewHolder(
            WindowManager.LayoutParams(
                TIMER_SIZE_PX,
                TIMER_SIZE_PX,
                0,
                0,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT
            ), service
        )
        viewHolder.view.setContent {
            floatingView()
            LaunchedEffect(Unit) {
                configurationChanged.collect {
                    updateClickTargetParamsWithinScreenBounds(
                        viewHolder,
                        overlayState
                    )
                }
            }
        }
        clickTargetSetOnTouchListener(
            viewHolder,
            sebhaFloatingState.overlayState,
            exitFloating,
        )
        return viewHolder
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun clickTargetSetOnTouchListener(
        viewHolder: OverlayViewHolder,
        overlayState: OverlayState,
        exitTimer: () -> Unit,
    ) {
        var paramStartDragX: Int = 0
        var paramStartDragY: Int = 0
        var startDragRawX: Float = 0F
        var startDragRawY: Float = 0F
        viewHolder.view.setOnTouchListener { _, event ->
            overlayState.isDragging.value = false
            val params = viewHolder.params
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    paramStartDragX = params.x
                    paramStartDragY = params.y
                    startDragRawX = event.rawX
                    startDragRawY = event.rawY
                }

                MotionEvent.ACTION_MOVE -> {
                    overlayState.isDragging.value = true
                    params.x = (paramStartDragX + (event.rawX - startDragRawX)).toInt()
                    params.y = (paramStartDragY + (event.rawY - startDragRawY)).toInt()
                    updateClickTargetParamsWithinScreenBounds(viewHolder, overlayState)
                }

                MotionEvent.ACTION_UP -> {
                    overlayState.isDragging.value = false
                    if (overlayState.isTimerHoverTrash) {
                        overlayState.isTimerHoverTrash = false
                        exitTimer()
                    }
                }
            }
            false
        }
    }

    private fun updateClickTargetParamsWithinScreenBounds(
        viewHolder: OverlayViewHolder,
        overlayState: OverlayState
    ) {
        val params = viewHolder.params
        var x = params.x
        var y = params.y
        x = max(x, 0)
        x = min(x, ScreenEz.safeWidth - TIMER_SIZE_PX)
        y = max(y, 0)
        y = min(y, ScreenEz.safeHeight - TIMER_SIZE_PX)
        params.x = x
        params.y = y
        try {
            windowManager.updateViewLayout(viewHolder.view, params)
        } catch (e: IllegalArgumentException) {
            // this was happening in prod, can't reproduce
            Log.e("OverlayController", "IllegalArgumentException: $e")
        }
        overlayState.timerOffset = IntOffset(params.x, params.y)
    }


    fun exitCountdown() {
        sebhaFloatingState.overlayState.isVisible.value = false
        sebhaFloatingState.overlayState.reset()
        service.stopSelf()
    }

    fun startCountDown() {
        sebhaFloatingState.overlayState.isVisible.value = true
    }

    fun isCountDownVisible(): Boolean {
        return sebhaFloatingState.overlayState.isVisible.value ?: false
    }

}
