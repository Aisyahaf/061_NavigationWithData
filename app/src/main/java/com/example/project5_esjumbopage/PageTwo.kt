package com.example.project5_esjumbopage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.project5_esjumbopage.data.OrderUIState
import com.example.project5_esjumbopage.ui.komponen.FormatLabelHarga

@Composable
fun PageTwo(
    orderUiState : OrderUIState,
    onCancelButtonClicked: ()-> Unit,
    modifier: Modifier = Modifier
){
    val items = listOf(
        Pair(stringResource(R.string.nama), orderUiState.nama),
        Pair(stringResource(R.string.telpon), orderUiState.telepon),
        Pair(stringResource(R.string.alamat), orderUiState.alamat),
        Pair(stringResource(R.string.quantity), orderUiState.jumlah),
        Pair(stringResource(R.string.flavor), orderUiState.rasa)
    )

    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column (
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
        ) {
            items.forEach{ item ->
                Column {
                    Text(item.first.uppercase(), fontWeight = FontWeight.Bold)
                    Text(text = item.second.toString(), fontSize = 18.sp)
                }
                Divider(
                    thickness = dimensionResource(R.dimen.thickness_divider)
                )
            }

            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))
            FormatLabelHarga(
                subtotal = orderUiState.harga,
                modifier = Modifier.align(Alignment.End)
            )

            Row (
                modifier = Modifier
                    .weight(1f, false)
                    .padding(dimensionResource(R.dimen.padding_medium))
            ){
                Column (
                    verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
                ){
                    Button(
                        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.onErrorContainer),
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { }
                    ) {
                      Text(stringResource(R.string.send))
                    }
                    OutlinedButton(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = onCancelButtonClicked
                    ) {
                        Text(stringResource(R.string.cancel))
                    }
                }
            }
        }
    }
}