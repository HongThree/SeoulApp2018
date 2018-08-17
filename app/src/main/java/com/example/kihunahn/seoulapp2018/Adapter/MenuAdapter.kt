package com.example.kihunahn.seoulapp2018.Adapter

import android.view.View
import nl.psdcompany.duonavigationdrawer.views.DuoOptionView
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.kihunahn.seoulapp2018.OptionView
import kotlinx.android.synthetic.main.view_option.view.*


internal class MenuAdapter(options: ArrayList<String>) : BaseAdapter() {
    private var mOptions : ArrayList<String> = ArrayList()
    private val mOptionViews : ArrayList<OptionView> = ArrayList()

    init {
        mOptions = options
    }

    override fun getCount(): Int {
        return mOptions.size
    }

    override fun getItem(position: Int): Any {
        return mOptions.get(position)
    }

    fun setViewSelected(position: Int, selected: Boolean) {

        // Looping through the options in the menu
        // Selecting the chosen option
        for (i in 0 until mOptionViews.size) {
            if (i == position) {
                mOptionViews.get(i).setSelected(selected)
            } else {
                mOptionViews.get(i).setSelected(!selected)
            }
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val option = mOptions.get(position)

        // Using the DuoOptionView to easily recreate the demo
        val optionView: OptionView

        if (convertView == null) {

            optionView = OptionView(parent.context)
        } else {
            optionView = convertView as OptionView
        }

        // Using the DuoOptionView's default selectors
        optionView.bind(option, null, null)

        // Adding the views to an array list to handle view selection
        mOptionViews.add(optionView)

        return optionView
    }
}