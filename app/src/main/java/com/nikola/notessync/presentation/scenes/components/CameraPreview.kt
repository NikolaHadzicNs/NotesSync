package com.nikola.notessync.presentation.scenes.components

import android.graphics.Bitmap
import android.util.Log
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.nikola.notessync.R
import io.fotoapparat.Fotoapparat
import io.fotoapparat.log.logcat
import io.fotoapparat.log.loggers
import io.fotoapparat.parameter.ScaleType
import io.fotoapparat.preview.Frame
import io.fotoapparat.selector.autoFocus
import io.fotoapparat.selector.back
import io.fotoapparat.selector.continuousFocusPicture
import io.fotoapparat.selector.firstAvailable
import io.fotoapparat.selector.fixed
import io.fotoapparat.selector.highestResolution
import io.fotoapparat.view.CameraView

@Composable
fun CameraPreview(
    modifier: Modifier = Modifier,
    onBitmap: (Bitmap?) -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    var fotoapparat: Fotoapparat? = null
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        AndroidView(
            modifier = modifier,
            factory = { context ->

                val cameraView = CameraView(context)

                fotoapparat = Fotoapparat.with(context)
                    .into(cameraView) // view which will draw the camera preview
                    .previewScaleType(ScaleType.CenterCrop) // we want the preview to fill the view
                    .lensPosition(back()) // we want back camera
                    .focusMode(
                        firstAvailable(
                            continuousFocusPicture(),  // (optional) use the first focus mode which is supported by device
                            autoFocus(),  // in case if continuous focus is not available on device, auto focus will be used
                            fixed() // if even auto focus is not available - fixed focus mode will be used
                        )
                    )
                    .build()

                fotoapparat?.start()
                cameraView
            })

        IconButton(onClick = {
            fotoapparat?.takePicture()?.toBitmap()?.whenAvailable {
                onBitmap(it?.bitmap)
            }
        }) {
            Icon(painter = painterResource(id = R.drawable.ic_scan), contentDescription = "Scan", Modifier.size(100.dp), tint = Color.White)
        }
    }

}