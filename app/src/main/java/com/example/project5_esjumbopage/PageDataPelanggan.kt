package com.example.project5_esjumbopage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PageDataPelanggan(
    onCancelButtonClicked: () -> Unit,
    onNextButtonClicked: (MutableList<String>) -> Unit,
){
    var namaText by remember {
        mutableStateOf("")
    }
    var tlpnText by remember {
        mutableStateOf("")
    }
    var alamatText by remember {
        mutableStateOf("")
    }

    var listData: MutableList<String> = mutableListOf(namaText,tlpnText,alamatText)

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ){
        OutlinedTextField(
            value = namaText, 
            onValueChange = {namaText = it},
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.padding_medium)),
            label = { Text(text = stringResource(id = R.string.nama))}
        )

        OutlinedTextField(
            value = tlpnText,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.padding_medium)),
            label = { Text(text = stringResource(id = R.string.telpon))},
            onValueChange = {tlpnText = it}
        )

        OutlinedTextField(
            value = alamatText,
            onValueChange = {alamatText = it},
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.padding_medium)),
            label = { Text(text = stringResource(id = R.string.alamat))}
        )
        
        Spacer(modifier = Modifier.height(15.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.padding_medium))
                .weight(1f, false),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium)),
            verticalAlignment = Alignment.Bottom
        ){
            OutlinedButton(
                modifier = Modifier.weight(1f),
                onClick = onCancelButtonClicked,
            ) {
                Text(stringResource(R.string.cancel))
            }
            Button(
                modifier = Modifier.weight(1f),
                enabled = alamatText.isNotEmpty(),
                onClick = {onNextButtonClicked(listData)}
            ) {
                Text(stringResource(R.string.next))
            }
        }

    }
}