package com.nikola.notessync.presentation.scenes.note_detail

import android.Manifest
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import com.nikola.notessync.R
import com.nikola.notessync.presentation.scenes.components.CameraPreview
import com.nikola.notessync.presentation.ui.theme.NotesSyncTheme
import java.util.Arrays


@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun NoteDetailScreen(
    navController: NavController,
    noteId: String?,
    viewModel: NoteDetailViewModel = hiltViewModel()
) {

    val permissionState = rememberPermissionState(permission = Manifest.permission.CAMERA)

    var showCam by remember {
        mutableStateOf(false)
    }

    var contentFieldValue by remember {
        mutableStateOf(
            TextFieldValue(text = "")
        )
    }

    var titleFieldValue by remember {
        mutableStateOf(
            TextFieldValue(text = "")
        )
    }

    LaunchedEffect(key1 = true) {
        viewModel.getNote(noteId?.toInt()) {
            contentFieldValue = contentFieldValue.copy(text = it.content)
            titleFieldValue = titleFieldValue.copy(text = it.title)
        }
    }

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        IconButton(
            onClick = {
                if (permissionState.status.isGranted) {
                    showCam = !showCam
                } else {
                    permissionState.launchPermissionRequest()
                }
            },
            modifier = Modifier.padding(4.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_scan),
                contentDescription = "Scan text from document"
            )
        }

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = titleFieldValue,
            onValueChange = {
                viewModel.onEvent(NoteDetailEvent.UpdateTitle(it.text))
                titleFieldValue = it
            },
            placeholder = {
                Text(text = "Enter title")
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = Color.Transparent,
                textColor = MaterialTheme.colorScheme.onBackground
            ),
            textStyle = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            value = contentFieldValue,
            onValueChange = {
                viewModel.onEvent(NoteDetailEvent.UpdateContent(it.text))
                contentFieldValue = it
            },
            placeholder = {
                Text(text = "Enter content")
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = Color.Transparent,
                textColor = MaterialTheme.colorScheme.onBackground
            ),
            textStyle = TextStyle(fontSize = 14.sp)
        )

        AndroidView(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(Color.Black),
            factory = { context ->
                val adView = AdView(context)
                adView.setAdSize(AdSize.BANNER)
                //TODO change this for production id should be replaced with one from console and test device code removed
                adView.adUnitId = "ca-app-pub-3940256099942544/6300978111"

                val testDeviceIds = Arrays.asList("5E4BDA8F2E396586E373C5DB3C8142D4")
                val configuration =
                    RequestConfiguration.Builder().setTestDeviceIds(testDeviceIds).build()
                MobileAds.setRequestConfiguration(configuration)

                val adRequest: AdRequest = AdRequest.Builder().build()
                adView.loadAd(adRequest)
                adView.adListener = object : AdListener() {
                    override fun onAdClicked() {
                        // Code to be executed when the user clicks on an ad.
                    }

                    override fun onAdClosed() {
                        // Code to be executed when the user is about to return
                        // to the app after tapping on an ad.
                    }

                    override fun onAdFailedToLoad(adError: LoadAdError) {
                        Log.d("AdGoogle", "Error ${adError}")
                        // Code to be executed when an ad request fails.
                    }

                    override fun onAdImpression() {
                        // Code to be executed when an impression is recorded
                        // for an ad.
                    }

                    override fun onAdLoaded() {
                        // Code to be executed when an ad finishes loading.
                        Log.d("AdGoogle", "Success")
                    }

                    override fun onAdOpened() {
                        // Code to be executed when an ad opens an overlay that
                        // covers the screen.
                    }
                }
                adView
            })

    }

    if (showCam) {
        AnimatedVisibility(visible = showCam, enter = fadeIn(), exit = fadeOut()) {
            CameraPreview(modifier = Modifier.fillMaxSize()) { bitmap ->
                showCam = !showCam
                bitmap?.let { btm ->
                    viewModel.getTextFromImage(btm) {
                        contentFieldValue = contentFieldValue.copy(text = it.content)
                    }
                }
            }
        }
    }


    BackHandler(true) {
        if (!showCam) {
            viewModel.onEvent(NoteDetailEvent.AddNote)
            navController.navigateUp()
        } else {
            showCam = !showCam
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    NotesSyncTheme {
        NoteDetailScreen(rememberNavController(), "2")
    }
}