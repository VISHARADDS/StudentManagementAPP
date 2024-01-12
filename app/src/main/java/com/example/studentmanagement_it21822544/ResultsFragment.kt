package com.example.studentmanagement_it21822544

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.studentmanagement_it21822544.data.ResultEntity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ResultsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ResultsFragment : Fragment() {
    private var tvModule1: TextView? = null
    private var tvModule2: TextView? = null
    private var tvModule3: TextView? = null
    private var tvGPA: TextView? = null

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_results, container, false)
        tvModule1 = view?.findViewById(R.id.tvModule1)
        tvModule2 = view?.findViewById(R.id.tvModule2)
        tvModule3 = view?.findViewById(R.id.tvModule3)
        tvGPA = view?.findViewById(R.id.tvGPA)

        val viewModel = ViewModelProvider(requireActivity())[FragmentViewModel::class.java]
        viewModel.resultLiveData.observe(viewLifecycleOwner, Observer { result ->
            setResults(result)
        })
        return view
    }

    fun setResults(result: ResultEntity?) {
        result?.let {
            tvModule1?.text = it.module1
            tvModule2?.text = it.module2
            tvModule3?.text = it.module3
            tvGPA?.text = it.gpa.toString()

        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ResultsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ResultsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}