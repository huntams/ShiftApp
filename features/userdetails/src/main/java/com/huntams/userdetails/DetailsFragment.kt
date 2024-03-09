package com.huntams.userdetails

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.google.android.material.snackbar.Snackbar
import com.huntams.data.base.ResultLoader
import com.huntams.data.base.toastError
import com.huntams.userdetails.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.fragment_details) {

    private val binding by viewBinding(FragmentDetailsBinding::bind)
    private val args: DetailsFragmentArgs by navArgs()
    private val viewModel by viewModels<DetailsViewModel>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel.getUser(args.seed)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.userLiveData.observe(viewLifecycleOwner) { result ->
            when (result) {
                is ResultLoader.Loading -> {
                    binding.progressbar.visibility = View.VISIBLE
                    binding.profileLayout.root.visibility = View.GONE
                }

                is ResultLoader.Failure -> {
                    toastError({ viewModel.getUser(args.seed) }, binding.root, requireContext())
                }

                is ResultLoader.Success -> {
                    with(binding) {
                        progressbar.visibility = View.GONE
                        profileLayout.root.visibility = View.VISIBLE
                        profileLayout.textViewProfile.text =
                            getString(R.string.age, result.value.dob.age.toString(), result.value.dob.date)
                        profileLayout.imageViewProfile.load(result.value.picture.large)
                        profileLayout.textViewId.text =
                            getString(R.string.id_profile, result.value.userId.value)
                        profileLayout.textViewName.text = result.value.name.nameBuilder()
                        profileLayout.textViewPhone.text =
                            getString(R.string.phone_cell, result.value.phone, result.value.cell)
                        profileLayout.textViewPhone.setOnClickListener {
                            val uri =
                                "tel:${result.value.phone}".toUri()
                            val intent = Intent(Intent.ACTION_DIAL, uri)
                            startActivity(intent)
                        }
                        profileLayout.textViewEmail.text = result.value.email
                        profileLayout.textViewEmail.setOnClickListener {
                            val intent = Intent(Intent.ACTION_SEND)
                            intent.data = Uri.parse("mailto:")

                            intent.type = "text/plain"
                            val data = arrayOf(result.value.email)
                            intent.putExtra(Intent.EXTRA_EMAIL, data)
                            startActivity(
                                Intent.createChooser(
                                    intent,
                                    getString(R.string.choice_email)
                                )
                            )
                        }
                        profileLayout.textViewAddress.text =
                            result.value.location.streetStringBuilder()
                        profileLayout.textViewAddress.setOnClickListener {
                            val uri =
                                "geo:${result.value.location.coordinates.latitude},${result.value.location.coordinates.longitude}".toUri()
                            val intent = Intent(Intent.ACTION_VIEW, uri)
                            startActivity(
                                Intent.createChooser(
                                    intent,
                                    getString(R.string.choice_map)
                                )
                            )

                        }
                    }
                }
            }


        }


        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDetach() {
        val snackbar = Snackbar.make(binding.root, "", Snackbar.LENGTH_SHORT)
        snackbar.show()
        snackbar.dismiss()
        super.onDetach()
    }
}