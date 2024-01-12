package com.example.studentmanagement_it21822544

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.studentmanagement_it21822544.data.StudentEntity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {
    private var tvStdId: TextView? = null
    private var tvStdName: TextView? = null
    private var tvMail: TextView? = null
    private var tvstdProgram: TextView? = null
    private var tvstdFaculty: TextView? = null
    private var tvstdNic: TextView? = null
    private var tvstdMobile: TextView? = null

    private lateinit var viewModel: FragmentViewModel


    private var param1: String? = null
    private var param2: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[FragmentViewModel::class.java]
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
        Log.d("ProfileFragment", "View is being created")
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        tvStdId = view?.findViewById(R.id.tvModule1)
        tvStdName = view?.findViewById(R.id.tvModule2)
        tvMail = view?.findViewById(R.id.tvMail)
        tvstdProgram = view?.findViewById(R.id.tvGPA)
        tvstdFaculty = view?.findViewById(R.id.tvstdFaculty)
        tvstdNic = view?.findViewById(R.id.tvstdNic)
        tvstdMobile = view?.findViewById(R.id.tvstdMobile)

        val viewModel = ViewModelProvider(requireActivity())[FragmentViewModel::class.java]
        viewModel.studentLiveData.observe(viewLifecycleOwner, Observer { student ->
            setStudentInfo(student)
        })


        return view
    }

    fun setStudentInfo(student: StudentEntity?) {
        student?.let {
            tvStdId?.text = it.studentId
            tvStdName?.text = it.studentName
            tvMail?.text = it.email
            tvstdProgram?.text = it.spinnerProgram
            tvstdFaculty?.text = it.spinnerFaculty
            tvstdNic?.text = it.nic
            tvstdMobile?.text = it.mobile
        }
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProfileFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}