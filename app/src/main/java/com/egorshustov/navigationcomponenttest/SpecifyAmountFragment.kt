package com.egorshustov.navigationcomponenttest


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_specify_amount.*
import java.math.BigDecimal


class SpecifyAmountFragment : Fragment(), View.OnClickListener {
    private lateinit var navController: NavController
    private var recipient: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recipient = arguments?.getString("recipient")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_specify_amount, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        button_send.setOnClickListener(this)
        button_cancel.setOnClickListener(this)
        val message = "Sending money to $recipient"
        text_recipient.text = message
    }

    override fun onClick(v: View?) {
        v ?: return
        when (v.id) {
            R.id.button_send -> {
                val inputAmountText = input_amount.text?.toString()
                if (!inputAmountText.isNullOrBlank()) {
                    val money = Money(BigDecimal(inputAmountText))
                    val bundle = bundleOf("recipient" to recipient, "money" to money)
                    navController.navigate(R.id.action_specifyAmountFragment_to_confirmationFragment, bundle)
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
