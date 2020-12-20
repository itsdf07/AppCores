import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.itsdf07.core.app.R
import com.itsdf07.core.app.common.adapter.BaseRecyclerViewAdapter
import com.itsdf07.core.lib.alog.ALog

/**
 * @Description:
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2020/11/14
 */
class ShortVideoFragment : Fragment() {
    var shortVideoViewModel: ShortVideoViewModel? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        shortVideoViewModel = ViewModelProvider(this).get(ShortVideoViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_shortvideo, container, false)
        var shortVideoRecycleView = root.findViewById<RecyclerView>(R.id.id_shortvideo)
        shortVideoRecycleView.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        var datas = shortVideoViewModel!!.loadData()

        var shortVideoAdapter = activity?.let {
            ShortVideoAdapter(
                it,
                datas.value!!,
                R.layout.item_shortvideo
            )
        }
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
        return root
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