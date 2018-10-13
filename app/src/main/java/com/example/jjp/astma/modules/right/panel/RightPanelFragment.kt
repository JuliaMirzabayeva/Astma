package com.example.jjp.astma.modules.right.panel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jjp.astma.R
import kotlinx.android.synthetic.main.fragment_right_panel.*
import nucleus.factory.RequiresPresenter
import nucleus.view.NucleusFragment

@RequiresPresenter(RightPanelFragmentPresenter::class)
class RightPanelFragment : NucleusFragment<RightPanelFragmentPresenter>() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_right_panel, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTimeSelected(true)

        morningButton.setOnClickListener { _ ->
            setTimeSelected(true)
        }
        eveningButton.setOnClickListener { _ ->
            setTimeSelected(false)
        }
    }

    private fun setTimeSelected(isMorning : Boolean){
        morningButton.isSelected = isMorning
        eveningButton.isSelected = !isMorning
    }
}