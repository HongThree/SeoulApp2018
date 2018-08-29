package com.example.kihunahn.seoulapp2018.Fragment

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Animatable
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.ActivityCompat.finishAfterTransition
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.content.ContextCompat.getSystemService
import android.support.v7.app.AppCompatActivity
import android.support.v7.graphics.Palette
import android.transition.Transition
import android.view.LayoutInflater
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Toast
import com.example.kihunahn.seoulapp2018.Place
import com.example.kihunahn.seoulapp2018.PlaceData
import com.example.kihunahn.seoulapp2018.R
import com.example.kihunahn.seoulapp2018.R.id.*
import kotlinx.android.synthetic.main.fragment_detail.*
import java.util.*

class DetailFragment : Fragment() {

    lateinit private var inputManager: InputMethodManager
    lateinit private var place: Place
    lateinit private var todoList: ArrayList<String>
    lateinit private var toDoAdapter: ArrayAdapter<*>

    private var isEditTextVisible: Boolean = false
    private var defaultColor: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater!!.inflate(R.layout.fragment_detail, container, false)
        Toast.makeText(activity, "ok", Toast.LENGTH_LONG).show()

        var position = arguments!!.getSerializable("position")

        //Toast.makeText(activity, arguments!!.getSerializable("position").toString(), Toast.LENGTH_LONG).show()
        return view

    }


    override fun onStart() {
        super.onStart()
        setupValues()
        loadPlace()
        windowTransition()
        getPhoto()
    }
    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDetach() {
        super.onDetach()
    }
    private fun setupValues() {
        place = PlaceData.placeList()[arguments!!.getSerializable("position").toString().toInt()]
        //addButton.setOnClickListener(activity)
        defaultColor = ContextCompat.getColor(activity!!, R.color.primary_dark)
        //inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        //inputManager = getSystemService(activity!!.INPUT_METHOD_SERVICE) as InputMethodManager

        revealView.visibility = View.INVISIBLE
        isEditTextVisible = false
    }


    private fun loadPlace() {
        placeTitle.text = place.name
        placeImage.setImageResource(place.getImageResourceId(activity!!))
    }

    private fun getPhoto() {
        val photo = BitmapFactory.decodeResource(resources, place.getImageResourceId(activity!!))
        colorize(photo)
    }

    private fun colorize(photo: Bitmap) {
        val palette = Palette.from(photo).generate()
        applyPalette(palette)
    }
    private fun applyPalette(palette: Palette) {

        activity!!.window.setBackgroundDrawable(ColorDrawable(palette.getDarkMutedColor(defaultColor)))
        placeNameHolder.setBackgroundColor(palette.getMutedColor(defaultColor))
        revealView.setBackgroundColor(palette.getLightVibrantColor(defaultColor))
    }

    private fun windowTransition() {
        activity!!.window.enterTransition.addListener(object : Transition.TransitionListener {
            override fun onTransitionEnd(transition: Transition) {
                addButton.animate().alpha(1.0f)
                activity!!.window.enterTransition.removeListener(this)
            }

            override fun onTransitionResume(transition: Transition) {}
            override fun onTransitionPause(transition: Transition) {}
            override fun onTransitionCancel(transition: Transition) {}
            override fun onTransitionStart(transition: Transition) {}
        })
    }
    private fun addToDo(todo: String) {
        todoList.add(todo)
    }

    fun onClick(v: View) {
        when (v.id) {
            R.id.addButton -> if (!isEditTextVisible) {
                revealEditText(revealView)
                todoText.requestFocus()
                inputManager.showSoftInput(todoText, InputMethodManager.SHOW_IMPLICIT)
                addButton.setImageResource(R.drawable.icn_morph)
                val animatable = addButton.drawable as Animatable
                animatable.start()
            } else {
                addToDo(todoText.text.toString())
                toDoAdapter.notifyDataSetChanged()
                inputManager.hideSoftInputFromWindow(todoText.windowToken, 0)
                hideEditText(revealView)
                addButton.setImageResource(R.drawable.icn_morph_reverse)
                val animatable = addButton.drawable as Animatable
                animatable.start()
            }
        }
    }

    private fun revealEditText(view: LinearLayout) {
        val cx = view.right - 30
        val cy = view.bottom - 60
        val finalRadius = Math.max(view.width, view.height)
        val anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, 0f, finalRadius.toFloat())
        view.visibility = View.VISIBLE
        isEditTextVisible = true
        anim.start()
    }

    private fun hideEditText(view: LinearLayout) {
        val cx = view.right - 30
        val cy = view.bottom - 60
        val initialRadius = view.width
        val anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, initialRadius.toFloat(), 0f)
        anim.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                view.visibility = View.INVISIBLE
            }
        })
        isEditTextVisible = false
        anim.start()
    }

    fun onBackPressed() {
        val alphaAnimation = AlphaAnimation(1.0f, 0.0f)
        alphaAnimation.duration = 100
        addButton.startAnimation(alphaAnimation)
        alphaAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {

            }

            override fun onAnimationEnd(animation: Animation) {
                //addButton.visibility = View.GONE
                //finishAfterTransition()
            }

            override fun onAnimationRepeat(animation: Animation) {

            }
        })
    }


    /*

    private fun windowTransition() {
        window.enterTransition.addListener(object : Transition.TransitionListener {
            override fun onTransitionEnd(transition: Transition) {
                addButton.animate().alpha(1.0f)
                window.enterTransition.removeListener(this)
            }

            override fun onTransitionResume(transition: Transition) {}
            override fun onTransitionPause(transition: Transition) {}
            override fun onTransitionCancel(transition: Transition) {}
            override fun onTransitionStart(transition: Transition) {}
        })
    }

    private fun addToDo(todo: String) {
        todoList.add(todo)
    }

    private fun getPhoto() {
        val photo = BitmapFactory.decodeResource(resources, place.getImageResourceId(this))
        colorize(photo)
    }

    private fun colorize(photo: Bitmap) {
        val palette = Palette.from(photo).generate()
        applyPalette(palette)
    }

    private fun applyPalette(palette: Palette) {
        window.setBackgroundDrawable(ColorDrawable(palette.getDarkMutedColor(defaultColor)))
        placeNameHolder.setBackgroundColor(palette.getMutedColor(defaultColor))
        revealView.setBackgroundColor(palette.getLightVibrantColor(defaultColor))
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.addButton -> if (!isEditTextVisible) {
                revealEditText(revealView)
                todoText.requestFocus()
                inputManager.showSoftInput(todoText, InputMethodManager.SHOW_IMPLICIT)
                addButton.setImageResource(R.drawable.icn_morph)
                val animatable = addButton.drawable as Animatable
                animatable.start()
            } else {
                addToDo(todoText.text.toString())
                toDoAdapter.notifyDataSetChanged()
                inputManager.hideSoftInputFromWindow(todoText.windowToken, 0)
                hideEditText(revealView)
                addButton.setImageResource(R.drawable.icn_morph_reverse)
                val animatable = addButton.drawable as Animatable
                animatable.start()
            }
        }
    }

    private fun revealEditText(view: LinearLayout) {
        val cx = view.right - 30
        val cy = view.bottom - 60
        val finalRadius = Math.max(view.width, view.height)
        val anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, 0f, finalRadius.toFloat())
        view.visibility = View.VISIBLE
        isEditTextVisible = true
        anim.start()
    }

    private fun hideEditText(view: LinearLayout) {
        val cx = view.right - 30
        val cy = view.bottom - 60
        val initialRadius = view.width
        val anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, initialRadius.toFloat(), 0f)
        anim.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                view.visibility = View.INVISIBLE
            }
        })
        isEditTextVisible = false
        anim.start()
    }

    override fun onBackPressed() {
        val alphaAnimation = AlphaAnimation(1.0f, 0.0f)
        alphaAnimation.duration = 100
        addButton.startAnimation(alphaAnimation)
        alphaAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {

            }

            override fun onAnimationEnd(animation: Animation) {
                addButton.visibility = View.GONE
                finishAfterTransition()
            }

            override fun onAnimationRepeat(animation: Animation) {

            }
        })
    }
    */
}