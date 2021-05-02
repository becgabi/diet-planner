package com.ptma.ui.login

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import co.zsmb.rainbowcake.base.OneShotEvent
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import co.zsmb.rainbowcake.base.ViewModelScope
import co.zsmb.rainbowcake.dagger.getViewModelFromFactory
import com.google.android.material.navigation.NavigationView
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.logEvent
import com.ptma.R
import com.ptma.data.network.security.Permission
import com.ptma.databinding.FragmentLoginBinding
import com.ptma.ui.login.LoginViewModel.Companion.menuVisibilities
import com.ptma.ui.util.TextChangedWatcher
import com.ptma.ui.util.showSnackbar

class LoginFragment : RainbowCakeFragment<LoginViewState, LoginViewModel>() {

    private lateinit var binding: FragmentLoginBinding

    private lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun provideViewModel() = getViewModelFromFactory(scope = ViewModelScope.Activity)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initForms()

        firebaseAnalytics = FirebaseAnalytics.getInstance(requireContext())

        if (viewModel.isUserLoggedIn) {
            handleLogin()
        }
    }

    override fun render(viewState: LoginViewState) {
        when (viewState) {
            ValidData -> binding.btnLogin.isEnabled = true
            Loading -> binding.pbLoading.visibility = View.VISIBLE
            is FaultyData -> {
                binding.btnLogin.isEnabled = false
                if (viewState.invalidUsername) {
                    binding.etUsername.error = getString(R.string.invalid_data)
                }
                if (viewState.invalidPassword) {
                    binding.etPassword.error = getString(R.string.invalid_data)
                }
            }
            is LoggedIn -> {
                binding.pbLoading.visibility = View.GONE
                showSnackbar(R.string.successful_login)
                logLoginEvent()
                handleLogin()
            }
        }
    }

    private fun logLoginEvent() {
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.LOGIN) {
            param("email", binding.etUsername.text.toString())
        }
    }

    private fun handleLogin() {
        setVisibilityOfMenuItems()
        redirectToAppointmentList()
    }

    override fun onEvent(event: OneShotEvent) {
        when (event) {
            LoginViewModel.LoginFailedEvent -> {
                binding.pbLoading.visibility = View.GONE
                showSnackbar(R.string.login_failed)
            }
        }
    }

    private fun initForms() {
        val afterTextChangedListener = createLoginFormWatcher()
        binding.etUsername.addTextChangedListener(afterTextChangedListener)
        binding.etPassword.addTextChangedListener(afterTextChangedListener)
        binding.etPassword.setOnEditorActionListener(createActionDoneListener())
        binding.btnLogin.setOnClickListener { login() }
    }

    private fun createActionDoneListener(): TextView.OnEditorActionListener {
        return TextView.OnEditorActionListener { _: TextView?, actionId: Int, _: KeyEvent? ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                login()
            }
            false
        }
    }

    private fun createLoginFormWatcher(): TextChangedWatcher {
        return TextChangedWatcher {
            viewModel.loginDataChanged(
                binding.etUsername.text.toString(),
                binding.etPassword.text.toString()
            )
        }
    }

    private fun login() {
        binding.pbLoading.visibility = View.VISIBLE
        viewModel.login(
            binding.etUsername.text.toString(),
            binding.etPassword.text.toString()
        )
    }

    private fun setVisibilityOfMenuItems() {
        menuVisibilities.forEach { (menuItemId: Int, permission: Permission) ->
            setMenuItemVisibility(menuItemId, permission)
        }
    }

    private fun setMenuItemVisibility(menuItemId: Int, permission: Permission) {
        val hasPermission = viewModel.hasPermission(permission)
        requireActivity()
            .findViewById<NavigationView>(R.id.nav_view)
            .menu
            .findItem(menuItemId).isVisible = hasPermission
    }

    private fun redirectToAppointmentList() {
        findNavController().navigate(R.id.action_login_to_main)
    }

}