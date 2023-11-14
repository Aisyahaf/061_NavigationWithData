@file:OptIn(ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class)

package com.example.project5_esjumbopage

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.project5_esjumbopage.data.DataSource.flavors

enum class PengelolaHalaman{
    Home,
    Data,
    Rasa,
    Summary
}

@Composable
fun EsJumboAppBar(
    bisaNavigasiBack: Boolean,
    navigasiUp: () -> Unit,
    modifier: Modifier = Modifier
){
    TopAppBar(
        title = { Text(stringResource(id = R.string.app_name), fontFamily = FontFamily.Serif) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.errorContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (bisaNavigasiBack){
                IconButton(onClick = navigasiUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@Composable
fun EsJumboApp(
    viewModel : OrderViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
){
    Scaffold (
        topBar = {
            EsJumboAppBar(
                bisaNavigasiBack = false,
                navigasiUp = {}
            )
        }
    ){ innerPadding ->
        val uiState by viewModel.stateUI.collectAsState()
        NavHost(
            navController = navController,
            startDestination = PengelolaHalaman.Home.name,
            modifier = Modifier.padding(innerPadding)
        )
        {
            composable(route = PengelolaHalaman.Home.name){
                HomePage (
                    onNextButtonClicked = {
                        navController.navigate(PengelolaHalaman.Data.name)
                    }
                )
            }

            composable(route = PengelolaHalaman.Data.name){
                PageDataPelanggan(
                    onCancelButtonClicked = {
                        cancelOrderAndNavigateToHome(
                            viewModel,
                            navController
                        )
                    },
                    onNextButtonClicked = {
                        viewModel.setDataPel(it)
                        navController.navigate(PengelolaHalaman.Rasa.name)
                    })
            }

            composable(route = PengelolaHalaman.Rasa.name){
                val context = LocalContext.current
                PageOne(
                    pilihanRasa = flavors.map {id -> context.resources.getString(id)},
                    onSelectionChanged = {viewModel.setRasa(it)},
                    onConfirmButtonClicked = {viewModel.setJumlah(it)},
                    onNextButtonClicked = { navController.navigate(PengelolaHalaman.Summary.name)},
                    onCancelButtonClicked = {
                        cancelOrderAndNavigateToData(
                            viewModel,
                            navController
                        )
                    }
                )
            }
            composable(route = PengelolaHalaman.Summary.name){
                PageTwo(
                    orderUiState = uiState,
                    onCancelButtonClicked = { cancelOrderAndNavigateToRasa(navController) })
            }
        }
    }
}


private fun cancelOrderAndNavigateToHome(
    viewModel: OrderViewModel,
    navController: NavHostController
){
    viewModel.resetDataPel()
    navController.popBackStack(PengelolaHalaman.Home.name,
        inclusive = false)
}

private fun cancelOrderAndNavigateToData(
    viewModel: OrderViewModel,
    navController: NavHostController
){
    viewModel.resetOrder()
    navController.popBackStack(PengelolaHalaman.Data.name,
        inclusive = false)
}

private fun cancelOrderAndNavigateToRasa(
    navController: NavHostController
){
    navController.popBackStack(PengelolaHalaman.Rasa.name,
        inclusive = false)
}