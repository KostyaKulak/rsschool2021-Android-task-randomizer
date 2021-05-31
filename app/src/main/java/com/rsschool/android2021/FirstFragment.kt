package com.rsschool.android2021

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.rsschool.android2021.utils.isItInt

class FirstFragment : Fragment() {

    private var generateButton: Button? = null
    private var previousResult: TextView? = null
    private var minInput: EditText? = null
    private var maxInput: EditText? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previousResult = view.findViewById(R.id.previous_result)
        generateButton = view.findViewById(R.id.generate)
        minInput = view.findViewById(R.id.min_value)
        maxInput = view.findViewById(R.id.max_value)

        val result = arguments?.getInt(PREVIOUS_RESULT_KEY)
        previousResult?.text = resources.getString(R.string.previous_result, result)

        var min: Int
        var max: Int

        generateButton?.setOnClickListener {
            if (minInput?.text?.isEmpty()!! || maxInput?.text?.isEmpty()!!) {
                Toast.makeText(view.context, getString(R.string.empty_fields), Toast.LENGTH_SHORT).show()
            } else if (maxInput?.text.toString().isItInt(view.context) &&
                minInput?.text.toString().isItInt(view.context)) {
                if (maxInput?.text.toString().toInt() <= minInput?.text.toString().toInt()) {
                    Toast.makeText(view.context, getString(R.string.max_less_min), Toast.LENGTH_SHORT).show()
                } else {
                    min = minInput?.text.toString().toInt()
                    max = maxInput?.text.toString().toInt()
                    (requireActivity() as MainActivity).openSecondFragment(min, max)
                }
            }
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(previousResult: Int): FirstFragment {
            val fragment = FirstFragment()
            val args = Bundle()
            args.putInt(PREVIOUS_RESULT_KEY, previousResult)
            fragment.arguments = args
            return fragment
        }

        private const val PREVIOUS_RESULT_KEY = "PREVIOUS_RESULT"
    }
}