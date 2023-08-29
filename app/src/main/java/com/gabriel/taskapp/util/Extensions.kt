package com.gabriel.taskapp.util

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.gabriel.taskapp.R
import com.gabriel.taskapp.databinding.BottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

fun androidx.fragment.app.Fragment.initToolbar(toolbar:Toolbar){
    (activity as AppCompatActivity).setSupportActionBar(toolbar)
    (activity as AppCompatActivity).title = ""
    (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    toolbar.setNavigationOnClickListener { activity?.onBackPressedDispatcher?.onBackPressed() }
}

fun androidx.fragment.app.Fragment.showBottomSheet(
    titleDialog: Int? = null,
    message: String,
    titleButton: Int? = null,
    onClick: () -> Unit = {}
){
    val bottomSheetDialog = BottomSheetDialog(requireContext(), R.style.bottomSheetDialog)
    val binding: BottomSheetBinding =
        BottomSheetBinding.inflate(layoutInflater, null, false)

    binding.txtTitle.text = getText(titleDialog ?: R.string.text_title_warning)
    binding.txtMessage.text = message
    binding.btnOk.text = getText( titleButton?: R.string.text_button_warning)
    binding.btnOk.setOnClickListener {
        onClick()
        bottomSheetDialog.dismiss()
    }
    bottomSheetDialog.setContentView(binding.root)
    bottomSheetDialog.show()
}