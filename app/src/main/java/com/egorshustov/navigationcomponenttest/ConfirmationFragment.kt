package com.egorshustov.navigationcomponenttest


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_confirmation.*


class ConfirmationFragment : Fragment() {
    private var recipient: String? = null
    private var money: Money? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recipient = arguments?.getString("recipient")
        money = arguments?.getParcelable("money")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_confirmation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val confirmationMessageText = "$${money?.amount} was sent to $recipient"
        text_confirmation_message.text = confirmationMessageText
    }
}
