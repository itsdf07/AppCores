import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.itsdf07.core.app.R
import com.itsdf07.core.app.common.GridSpacingItemDecoration
import com.itsdf07.core.app.common.utils.DeviceUtils


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var tabLayout: TabLayout
    lateinit var tabViewPage: ViewPager
    lateinit var root: View

    private val tabs = arrayOf("推荐", "最新")
    private val tabFragmentList = arrayListOf<Fragment>()


    /**
     * 头部金刚区
     */
    private lateinit var headBannersList: RecyclerView
    private lateinit var headBannersAdapter: JKHeadBannersAdapter

    /**
     * 头部蛋蛋区
     */
    private lateinit var headEggsList: RecyclerView
    private lateinit var headEggsAdapter: JKHeadEggsAdapter

    /**
     * 头部大图区（亦是活动区）
     */
    private lateinit var headActivitysLis: RecyclerView

    /**
     * 头部方块区
     */
    private lateinit var headBlocksLis: RecyclerView
    private lateinit var headBlocksAdapter: JKHeadBlocksAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_home, container, false)
        return root
    }

    private fun initList() {
        //添加tab

        //添加tab
        for (element in tabs) {
            tabLayout.addTab(tabLayout.newTab().setText(element))
        }
        tabFragmentList.add(StaggeredGridFragment())
        tabFragmentList.add(StaggeredGridFragment())
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        initHeadBlock()
        initView()
        initList()

        // TODO: Use the ViewModel
    }

    /**
     * 初始化发现也头部相关UI
     */
    private fun initHeadBlock() {
        headBannersList = root.findViewById(R.id.banners_list)
        headBannersList.itemAnimator = DefaultItemAnimator()
        headBannersList.addItemDecoration(
            GridSpacingItemDecoration(
                2,
                DeviceUtils.dp2px(context, 11f),
                false
            )
        )
        headBannersList.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        headBannersAdapter =
            context?.let {
                JKHeadBannersAdapter(
                    it,
                    homeViewModel.headBeanData.value!!.banners,
                    R.layout.jk_item_home_head_banner
                )
            }!!
        headBannersList.adapter = headBannersAdapter
        //-----------------------------------------------------------

        var column = homeViewModel.headBeanData.value!!.eggs.size
        if (column > 5) {
            column = 5
        }
        headEggsList = root.findViewById(R.id.eggs_list)
        headEggsList.itemAnimator = DefaultItemAnimator()
        headEggsList.addItemDecoration(
            GridSpacingItemDecoration(
                column,
                DeviceUtils.dp2px(context, 11f),
                false
            )
        )
        headEggsList.layoutManager =
            StaggeredGridLayoutManager(
                column,
                StaggeredGridLayoutManager.VERTICAL
            )
        headEggsAdapter =
            context?.let {
                JKHeadEggsAdapter(
                    it,
                    homeViewModel.headBeanData.value!!.eggs,
                    R.layout.jk_item_home_head_egg
                )
            }!!
        headEggsList.adapter = headEggsAdapter

        //-----------------------------------------------------------
        headBlocksLis = root.findViewById(R.id.blocks_list)
        headBlocksLis.itemAnimator = DefaultItemAnimator()
        headBlocksLis.addItemDecoration(
            GridSpacingItemDecoration(
                homeViewModel.headBeanData.value!!.eggs.size,
                DeviceUtils.dp2px(context, 11f),
                false
            )
        )
        headBlocksLis.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        headBlocksAdapter =
            context?.let {
                JKHeadBlocksAdapter(
                    it,
                    homeViewModel.headBeanData.value!!.eggs,
                    R.layout.jk_item_home_head_block
                )
            }!!
        headBlocksLis.adapter = headBlocksAdapter

    }


    private fun initView() {
        tabLayout = root.findViewById(R.id.tab_layout)
        tabViewPage = root.findViewById(R.id.view_pager)


        tabViewPage.adapter = object : FragmentPagerAdapter(
            childFragmentManager,
            BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        ) {
            override fun getItem(position: Int): Fragment {
                return tabFragmentList[position]
            }

            override fun getCount(): Int {
                return tabs.size
            }

            @Nullable
            override fun getPageTitle(position: Int): CharSequence? {
                return tabs[position]
            }
        }
        tabViewPage.currentItem = 0
//        tabLayout.setupWithViewPager(tabViewPage)
    }
}