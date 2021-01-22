
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.itsdf07.core.app.R
import com.itsdf07.core.lib.alog.ALog
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener
import com.scwang.smart.refresh.layout.listener.OnRefreshListener

/**
 * @Description: 瀑布流列表UI
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2021/1/20
 */
class StaggeredGridFragment : Fragment() {
    lateinit var staggeredGridList: RecyclerView
    private lateinit var staggeredGridAdapter: StaggeredGridAdapter
    private lateinit var root: View
    private lateinit var refreshLayout: SmartRefreshLayout

    companion object {
        fun newInstance() = StaggeredGridFragment()
    }

    private lateinit var staggeredGridViewModel: StaggeredGridViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.staggered_grid_fragment, container, false)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        staggeredGridViewModel = ViewModelProvider(this).get(StaggeredGridViewModel::class.java)
        staggeredGridViewModel.itemBean.observe(viewLifecycleOwner, Observer {
            ALog.v("瀑布流Item数据发生变化了：${it.size}")

        })
        initView()
    }

    fun initView() {
        staggeredGridList = root.findViewById(R.id.staggered_grid_list)
        staggeredGridList.itemAnimator = DefaultItemAnimator()
        staggeredGridList.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        staggeredGridAdapter =
            context?.let {
                StaggeredGridAdapter(
                    it,
                    staggeredGridViewModel.itemBean.value!!,
                    R.layout.item_saggered
                )
            }!!
        staggeredGridList.adapter = staggeredGridAdapter

        refreshLayout = root.findViewById(R.id.refreshLayout)
        refreshLayout.setRefreshHeader(ClassicsHeader(context))
        refreshLayout.setRefreshFooter(ClassicsFooter(context))
        refreshLayout.setOnRefreshListener(onRefreshListener)
        refreshLayout.setOnLoadMoreListener(onLoadMoreListener)
    }

    private val onRefreshListener = OnRefreshListener {
        Toast.makeText(activity, "下拉刷新", Toast.LENGTH_SHORT).show()
        refreshLayout.finishRefresh(2000)
    }
    private val onLoadMoreListener = OnLoadMoreListener {
        Toast.makeText(activity, "上滑加载数据", Toast.LENGTH_SHORT).show()
        refreshLayout.finishLoadMore(2000)
    }
}