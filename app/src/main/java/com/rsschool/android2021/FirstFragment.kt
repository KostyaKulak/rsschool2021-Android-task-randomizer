package com.rsschool.android2021

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import com.rsschool.android2021.utils.isItInt

class FirstFragment : Fragment() {

    private var generateButton: Button? = null
    private var previousResult: TextView? = null
    private var minInput: EditText? = null
    private var maxInput: EditText? = null
    private var openFragmentListener: OpenFragment? = null

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

        generateButton?.setOnClickListener {
            val error = checkNumbers(minInput?.text.toString(), maxInput?.text.toString())

            if (error.isEmpty()) {
                openFragmentListener?.openSecondFragment(minInput?.text.toString().toInt(), minInput?.text.toString().toInt())
            } else Toast.makeText(view.context, error, Toast.LENGTH_SHORT).show()
        }

        activity?.onBackPressedDispatcher?.addCallback() {
            activity?.finish()
        }
    }

    private fun checkNumbers(minStr: String, maxStr: String): String = when {
        minStr.isEmpty() || maxStr.isEmpty() -> {
            getString(R.string.empty_fields)
        }
        !minStr.isItInt() || !maxStr.isItInt() -> {
            getString(R.string.max_allowed, Int.MAX_VALUE)
        }
        maxStr.toInt() <= minStr.toInt() -> {
            getString(R.string.max_less_min)
        }
        else -> ""
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        openFragmentListener = context as? OpenFragment
    }

    override fun onDetach() {
        super.onDetach()
        openFragmentListener = null
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