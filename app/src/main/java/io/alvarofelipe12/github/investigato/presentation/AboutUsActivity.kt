package io.alvarofelipe12.github.investigato.presentation

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import io.alvarofelipe12.github.investigato.MainActivity
import io.alvarofelipe12.github.investigato.R
import io.alvarofelipe12.github.investigato.databinding.ActivityAboutUsBinding


class AboutUsActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityAboutUsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAboutUsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        // Handle back press using OnBackPressedDispatcher
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Log.d("CustomLogs", "Handle the back button press")
                val intent = Intent(this@AboutUsActivity, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                finish()
            }
        })
        binding.btnSend.setOnClickListener(this)
        binding.btnFacebook.setOnClickListener(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                // Handle the "Up" button click
                Log.d("CustomLogs", "Handle the \"Up\" button click")
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onClick(view: View?) {
        if (view != null) {
            when (view.id) {
                R.id.btn_send -> {
                    Log.d("CustomLog", "Clicked send btn")
                    validateData()
                }

                R.id.btn_facebook -> {
                    openFacebookPage()
                }
            }
        }
    }

    private fun validateData() {
        val validData = validateFields()

        if (validData) {
            val fullName = binding.etName.text.toString()
            val email = binding.etEmail.text.toString()
            val phoneNumber = binding.etPhone.text.toString()
            val message = binding.etMessage.text.toString()
            Log.d("CustomLog", "Valid data")
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.putExtra(Intent.EXTRA_SUBJECT, "Contact us Form")
            intent.putExtra(
                Intent.EXTRA_TEXT,
                "Name: $fullName\nEmail: $email\nPhone number: $phoneNumber\nMessage: $message"
            )
            intent.setData(Uri.parse("mailto:alvarofelipe_1@hotmail.com"))
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }
        } else {
            Log.d("CustomLog", "Invalid data")
            Toast.makeText(this, "Please provide correct inputs", Toast.LENGTH_SHORT).show()
        }
    }

    private fun validateFields(): Boolean {
        var validData = true
        if (binding.etName.text.toString().isEmpty()) {
            binding.etName.error = "Full Name is required"
            validData = false
        }
        if (binding.etEmail.text.toString().isEmpty()) {
            binding.etEmail.error = "Email is required"
            validData = false
        }
        if (binding.etPhone.text.toString().isEmpty()) {
            binding.etPhone.error = "Phone is required"
            validData = false
        }
        if (binding.etMessage.text.toString().isEmpty()) {
            binding.etMessage.error = "Message is required"
            validData = false
        }
        return validData
    }

    private fun openFacebookPage() {
        try {
            val facebookAppUri = Uri.parse("fb://page/alvarofelipe")
            val intent = Intent(Intent.ACTION_VIEW, facebookAppUri)
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            val facebookUri = Uri.parse("https://www.facebook.com/alvarofelipe")
            val intent = Intent(Intent.ACTION_VIEW, facebookUri)
            startActivity(intent)
        }
    }
}