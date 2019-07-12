package com.egorshustov.navigationcomponenttest


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_specify_amount.*
import java.math.BigDecimal


class SpecifyAmountFragment : Fragment(), View.OnClickListener {
    private val args: SpecifyAmountFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_specify_amount, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button_send.setOnClickListener(this)
        button_cancel.setOnClickListener(this)
        val message = "Sending money to ${args.recipient}"
        text_recipient.text = message
    }

    override fun onClick(v: View?) {
        v ?: return
        when (v.id) {
            R.id.button_send -> {
                val inputAmountText = input_amount.text?.toString()
                if (!inputAmountText.isNullOrBlank()) {
                    val money = Money(BigDecimal(inputAmountText))
                    // In general, we should strongly prefer passing only the minimal amount of data between destinations.
                    // In this case passing Money Parcelable object is just for demo example.
                    val direction = SpecifyAmountFragmentDirections.actionSpecifyAmountFragmentToConfirmationFragment(
                        args.recipient,
                        money
                    )
                    findNavController().navigate(direction)
                } else {
                    Toast.makeText(activity, "Enter an amount", Toast.LENGTH_SHORT).show()
                }
            }
            R.id.button_cancel -> {
                activity?.onBackPressed()
            }
        }
    }
}
