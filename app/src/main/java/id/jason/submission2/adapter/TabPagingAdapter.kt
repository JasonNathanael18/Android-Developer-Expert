package id.jason.submission2.adapter

import android.content.Context
import androidx.annotation.Nullable
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import id.jason.submission2.R
import id.jason.submission2.view.MoviesFragment
import id.jason.submission2.view.TvShowFragment

class TabPagingAdapter (private val mContext: Context, fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    @StringRes
    private val TAB_TITLES = intArrayOf(R.string.tab_title1, R.string.tab_title2)

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = MoviesFragment()
            1 -> fragment = TvShowFragment()
        }
        return fragment as Fragment
    }

    @Nullable
    override fun getPageTitle(position: Int): CharSequence? {
        return mContext.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return 2
    }
}