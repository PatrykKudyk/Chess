package com.partos.chess.logic.logic

import android.view.View
import android.widget.Button
import androidx.fragment.app.FragmentManager
import com.partos.chess.logic.helpers.MovesHelper
import com.partos.chess.logic.listeners.MainMenuListeners
import kotlin.system.measureNanoTime

class MainMenuLogic {

    fun initFragment(rootView: View, fragmentManager: FragmentManager) {
        MainMenuListeners().initListeners(rootView, fragmentManager)
//
//        val array2dim = Array(8) {Array(8) {false} }
//        val array1dim = Array (64) {false}
//        var times = 0L
//        for (i in 0..100000){
//            val time = measureNanoTime{
//                val sth = getSth2dim(array2dim)
//            }
//            times += time
//        }
//        val srednia2dim: Double = times/100000000.0
//
//        var times2 = 0L
//        for (i in 0..100000){
//            val time = measureNanoTime{
//                val sth = getSth1dim(array1dim)
//            }
//            times2 += time
//        }
//        val srednia1dim: Double = times2/100000000.0
//
////        var times3 = 0L
////        for (i in 0..10000){
////            val time = measureNanoTime{
////                val array = Array(8) {Array (8) {false} }
////            }
////            times3 += time
////        }
////        val sredniaBezFun = times3/10000
////
////        var times4 = 0L
////        for (i in 0..10000){
////            val time = measureNanoTime{
////                val array = MovesHelper().createMovesList()
////            }
////            times4 += time
////        }
////        val sredniaZFunZKlasy = times4/10000
//
//
//        val cokolwiek = "scze"
    }

}