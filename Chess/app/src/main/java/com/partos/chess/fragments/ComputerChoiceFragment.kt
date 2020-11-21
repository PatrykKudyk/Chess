package com.partos.chess.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.partos.chess.R
import com.partos.chess.logic.logic.ComputerChoiceLogic

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ComputerChoiceFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ComputerChoiceFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var gameType: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            gameType = it.getInt(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_computer_choice, container, false)
        ComputerChoiceLogic().initFragment(view, fragmentManager as FragmentManager, gameType as Int)
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ComputerChoiceFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(gameType: Int) =
            ComputerChoiceFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, gameType)
                }
            }
    }
}