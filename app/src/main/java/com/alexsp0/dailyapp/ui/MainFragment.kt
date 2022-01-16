package com.alexsp0.dailyapp.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.alexsp0.dailyapp.MainActivity
import com.alexsp0.dailyapp.R
import com.alexsp0.dailyapp.data.NasaImageResponse
import com.alexsp0.dailyapp.presenters.MainPresenterImpl
import com.alexsp0.dailyapp.ui.mainViewPager.DateFragment
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainFragment(presenter:MainPresenterImpl) : Fragment() {
    private var presenter : MainPresenterImpl = presenter
    private lateinit var image : ImageView
    private lateinit var  bottomSheetBehavior : BottomSheetBehavior<ConstraintLayout>
    private lateinit var bottomSheetHeader : TextView
    private lateinit var bottomSheetDescription : TextView
    private lateinit var input_wiki_text_layout : TextInputLayout
    private lateinit var input_text_search_wiki : TextInputEditText
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.attach(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBottomSheetBehavior(view.findViewById(R.id.bottom_sheet_container))
        initSearchWikiIcon(view)
        val viewPager = view.findViewById<ViewPager>(R.id.view_pager)
        viewPager.adapter = MainFragmentPagerAdapter(this.childFragmentManager, context!!)
        viewPager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position : Int) {
                if(position == 1 ) {
                    val fr =
                        (viewPager.adapter as MainFragmentPagerAdapter).getItem(position) as DateFragment
                    fr.checkDate()
                }
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })
        //presenter.getDailyImage()
    }

    private fun initSearchWikiIcon(view : View) {
        input_wiki_text_layout = view.findViewById<TextInputLayout>(R.id.input_text_search_wiki_layout)
        input_text_search_wiki = view.findViewById<TextInputEditText>(R.id.input_edit_text_search_wiki)
        input_wiki_text_layout.setEndIconOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            val url = "https://en.wikipedia.org/wiki/${input_text_search_wiki.text.toString()}"
            val uri = Uri.parse(url)
            intent.data = uri
            startActivity(intent)
        }
    }

    fun setBottomSheetBehavior(bottomSheet : ConstraintLayout) {
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        bottomSheetHeader = bottomSheet.findViewById(R.id.bottom_sheet_description_header)
        bottomSheetDescription = bottomSheet.findViewById(R.id.bottom_sheet_description)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    fun setImage(imageInfo : NasaImageResponse) {
        Glide.with(this).load(imageInfo.url).placeholder(R.drawable.film).into(image)
        bottomSheetHeader.text = imageInfo.title
        bottomSheetDescription.text = imageInfo.explanation
//        image.setImageResource(R.drawable.film)
//        bottomSheetHeader.text = "Header"
//        bottomSheetDescription.text = "Descriptiofgjkdfhg;lsdfh;lgjdl;sfgjj;lsdfg sdlfijhsdlkf lkjdsfh sdghjj sdgh;jsghjslkhg  sdgh;j;jgh;lkghpoit;glkd;fhjlkgitjgosndfonbsgdbn sdlfk"
//        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detach()
    }

    companion object {
        @JvmStatic
        fun newInstance(presenter: MainPresenterImpl) = MainFragment(presenter)
    }
    fun changeTheme(isDark : Boolean) {
        val act = activity as MainActivity
        act.reloadAll()
    }
}