package com.example.project5_esjumbopage

import android.icu.text.NumberFormat
import androidx.lifecycle.ViewModel
import com.example.project5_esjumbopage.data.OrderUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

private const val HARGA_PER_CUP = 5000

class OrderViewModel : ViewModel() {
    private  val _stateUI = MutableStateFlow(OrderUIState())
    val stateUI: StateFlow<OrderUIState> = _stateUI.asStateFlow()

    fun setDataPel(list: MutableList<String>){
        _stateUI.update {
            stateUISaatIni -> stateUISaatIni.copy(
                nama = list[0],
                telepon = list[1],
                alamat = list[2]
            )
        }
    }

    fun resetDataPel(){
        _stateUI.value = OrderUIState()
    }

    fun setJumlah(jmlEsJumbo:Int){
        _stateUI.update { stateSaatIni ->
            stateSaatIni.copy(
                jumlah = jmlEsJumbo,
                harga = hitungHarga(jumlah = jmlEsJumbo)
            )
        }
    }

    fun setRasa(rasaPilihan: String) {
        _stateUI.update { stateSaatIni ->
            stateSaatIni.copy(rasa = rasaPilihan)
        }
    }

    fun resetOrder(){
        _stateUI.value = OrderUIState()
    }

    private fun hitungHarga(
        jumlah: Int = _stateUI.value.jumlah,
    ): String{
        val kalkulasiHarga = jumlah * HARGA_PER_CUP

        return NumberFormat.getNumberInstance().format(kalkulasiHarga)
    }


}