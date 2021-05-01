package com.ptma.ui.util

import android.content.Context
import android.os.Bundle
import android.os.VibrationEffect.DEFAULT_AMPLITUDE
import android.os.VibrationEffect.createOneShot
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import com.ptma.R

abstract class PTMAFragment<VS : Any, VM : RainbowCakeViewModel<VS>, VB : ViewBinding>
    : RainbowCakeFragment<VS, VM>() {

    private var _binding: VB? = null
    protected val binding get() = _binding ?: throw IllegalStateException("Binding is not set")

    private lateinit var progressBar: ProgressBar
    protected lateinit var noElement: TextView

    protected val navigator: NavController
        get() = findNavController()

    protected abstract fun inflateViewBinding(inflater: LayoutInflater, container: ViewGroup?): VB

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = inflateViewBinding(inflater, container)
        progressBar = requireActivity().findViewById(R.id.progressBar)
        noElement = requireActivity().findViewById(R.id.tvNoElement)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

}