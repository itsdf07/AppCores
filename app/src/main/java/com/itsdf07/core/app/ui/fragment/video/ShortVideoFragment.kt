import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.itsdf07.core.app.R
import com.itsdf07.core.app.common.adapter.BaseRecyclerViewAdapter
import com.itsdf07.core.app.databinding.FragmentShortvideoBinding
import com.itsdf07.core.app.ui.fragment.video.ShortVideoViewModel
import com.itsdf07.core.lib.alog.ALog
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener
import com.scwang.smart.refresh.layout.listener.OnRefreshListener

/**
 * @Description:
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2020/11/14
 */
class ShortVideoFragment : Fragment() {
    private lateinit var refreshLayout: SmartRefreshLayout
    private lateinit var shortVideoRecycleView: RecyclerView
    private lateinit var shortVideoAdapter: ShortVideoAdapter
    private lateinit var dataBinding: FragmentShortvideoBinding
    var shortVideoViewModel: ShortVideoViewModel? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        shortVideoViewModel = ViewModelProvider(this).get(ShortVideoViewModel::class.java)

        dataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_shortvideo, container, false)
//        dataBinding = FragmentShortvideoBinding.inflate(inflater)
        dataBinding.svViewModel = shortVideoViewModel
        dataBinding.lifecycleOwner = this
        initView()

        var datas = shortVideoViewModel!!.loadData()

        shortVideoAdapter = context?.let {
            ShortVideoAdapter(
                it,
                datas.value!!,
                R.layout.item_shortvideo
            )
        }!!
        datas.observe(viewLifecycleOwner, Observer {
            shortVideoAdapter!!.notifyDataSetChanged()
        })

        shortVideoAdapter?.onItemClickListner = this.onItemClickListner
        shortVideoRecycleView.adapter = shortVideoAdapter
        shortVideoRecycleView.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )
        return dataBinding.root
    }

    private fun initView() {
        shortVideoRecycleView = dataBinding.idShortvideo
        shortVideoRecycleView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)


        refreshLayout = dataBinding.refreshLayout
        refreshLayout.setRefreshHeader(ClassicsHeader(context))
        refreshLayout.setRefreshFooter(ClassicsFooter(context))
        refreshLayout.setOnRefreshListener(onRefreshListener)
        refreshLayout.setOnLoadMoreListener(onLoadMoreListener)
    }

    private val onRefreshListener = OnRefreshListener {
        Toast.makeText(context, "下拉刷新", Toast.LENGTH_SHORT).show()
        refreshLayout.finishRefresh(2000)
    }
    private val onLoadMoreListener = OnLoadMoreListener {
        Toast.makeText(context, "上拉加载成功", Toast.LENGTH_SHORT).show()
        refreshLayout.finishLoadMore(2000)
    }

    /**
     * 短视频列表点击事件
     */
    var onItemClickListner = object : BaseRecyclerViewAdapter.OnItemClickListner {
        override fun onItemClickListner(v: View?, position: Int) {
            ALog.i("点击了短视频列表:${position}")
        }

    }

}