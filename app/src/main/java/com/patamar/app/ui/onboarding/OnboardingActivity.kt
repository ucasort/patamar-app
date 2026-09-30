package com.patamar.app.ui.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.patamar.app.R
import com.patamar.app.core.security.EncryptedPrefsManager
import com.patamar.app.databinding.ActivityOnboardingBinding
import com.patamar.app.ui.auth.AuthActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

// Tela de boas-vindas: marca, Google/Apple e entrada por e-mail.
// Google e Apple ainda não têm integração (exigem chaves OAuth): só avisam "em breve".
@AndroidEntryPoint
class OnboardingActivity : AppCompatActivity() {

    @Inject lateinit var prefsManager: EncryptedPrefsManager

    private lateinit var binding: ActivityOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGoogle.setOnClickListener { comingSoon(R.string.welcome_google) }
        binding.btnApple.setOnClickListener { comingSoon(R.string.welcome_apple) }
        binding.btnEmail.setOnClickListener { openAuth(startAtRegister = false) }
        binding.tvCreateAccount.setOnClickListener { openAuth(startAtRegister = true) }
    }

    private fun comingSoon(providerLabel: Int) {
        Snackbar.make(binding.root, getString(R.string.welcome_coming_soon, getString(providerLabel)), Snackbar.LENGTH_SHORT).show()
    }

    private fun openAuth(startAtRegister: Boolean) {
        prefsManager.setOnboardingComplete(true)
        startActivity(AuthActivity.intent(this, startAtRegister))
        finish()
    }
}
