package com.itsdf07.core.app.ui.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.itsdf07.core.app.R
import com.itsdf07.core.lib.alog.ALog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GalleryViewModel : ViewModel() {
    private var picDatas = arrayListOf<Int>()
    private var picRes: IntArray = intArrayOf(
        R.mipmap.pic_3,
        R.mipmap.pic_1,
        R.mipmap.pic_5,
        R.mipmap.pic_4,
        R.mipmap.pic_1,
        R.mipmap.pic_2,
        R.mipmap.pic_6,
        R.mipmap.pic_4,
        R.mipmap.pic_2,
        R.mipmap.pic_1,
        R.mipmap.pic_5
    )

    init {
        initData()

    }

    private fun initData() {
        picRes.forEach {
            picDatas.add(it)
        }
        changeRes()
    }

    val itemDatas = MutableLiveData<ArrayList<Int>>().apply {
        value = picDatas
    }

    fun changeRes() {
        GlobalScope.launch(Dispatchers.Main) {
            repeat(5) {
                delay(3000)
//                ALog.d("删除了。。。${itemDatas.value!!.removeAt(0)}")
                itemDatas.value=itemDatas.value
            }

        }
    }


}