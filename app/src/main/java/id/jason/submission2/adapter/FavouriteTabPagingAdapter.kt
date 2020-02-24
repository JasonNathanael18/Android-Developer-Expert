package id.jason.submission2.adapter

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import id.jason.submission2.R
import id.jason.submission2.view.FavouriteFragment

class FavouriteTabPagingAdapter(
    private val mContext: Context,
    fm: FragmentManager
) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    @StringRes
    private val tabTitles = intArrayOf(R.string.tab_title1, R.string.tab_title2)

    override fun getItem(position: Int): Fragment {
       return FavouriteFragment.newInstance(position+1)
    }

    override fun getCount(): Int {
      return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mContext.resources.getString(tabTitles[position])
    }
}