package com.egorshustov.navigationcomponenttest


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : Fragment(), View.OnClickListener {
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        button_view_transactions.setOnClickListener(this)
        button_send_money.setOnClickListener(this)
        button_view_balance.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        v ?: return
        when (v.id) {
            R.id.button_view_transactions -> navController.navigate(R.id.action_mainFragment_to_viewTransactionFragment)
            R.id.button_send_money -> navController.navigate(R.id.action_mainFragment_to_chooseRecipientFragment)
            R.id.button_view_balance -> navController.navigate(R.id.action_mainFragment_to_viewBalanceFragment)
        }
    }
}
