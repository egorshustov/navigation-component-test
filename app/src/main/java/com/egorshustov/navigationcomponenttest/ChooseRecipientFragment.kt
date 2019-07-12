package com.egorshustov.navigationcomponenttest


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_choose_recipient.*


class ChooseRecipientFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_choose_recipient, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button_next.setOnClickListener(this)
        button_cancel.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        v ?: return
        when (v.id) {
            R.id.button_next -> {
                val inputRecipientText = input_recipient.text?.toString()
                if (!inputRecipientText.isNullOrBlank()) {
                    val direction =
                        ChooseRecipientFragmentDirections.actionChooseRecipientFragmentToSpecifyAmountFragment(
                            inputRecipientText
                        )
                    findNavController().navigate(direction)
                } else {
                    Toast.makeText(activity, "Enter a recipient", Toast.LENGTH_SHORT).show()
                }
            }
            R.id.button_cancel -> {
                activity?.onBackPressed()
            }
        }
    }
}
