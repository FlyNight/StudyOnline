package com.example.studyonline.data.bean

class DataBean {
    var imageRes: Int? = null
    var imageUrl: String? = null
    var title: String?
    var viewType: Int

    constructor(imageRes: Int?, title: String?, viewType: Int) {
        this.imageRes = imageRes
        this.title = title
        this.viewType = viewType
    }

    constructor(imageUrl: String?, title: String?, viewType: Int) {
        this.imageUrl = imageUrl
        this.title = title
        this.viewType = viewType
    }

    companion object {

        //测试数据，如果图片链接失效请更换
        val testData3: List<DataBean>
            get() {
                val list: MutableList<DataBean> = ArrayList()
                list.add(
                    DataBean(
                        "https://yunkai.com/20191213000031-4c90873b8c55b68ab52475f44253dcdb.png",
                        null,
                        1
                    )
                )
                list.add(
                    DataBean(
                        "https://pic4.zhimg.com/v2-f4ec9cf931dbb26eb637ded356c9f5c7_r.jpg",
                        null,
                        1
                    )
                )
                list.add(
                    DataBean(
                        "http://images.wenku.it168.com/pdf/2019-10-11/1765191/FEADD8A50854BA7C020B31B6FC71A5AE.png",
                        null,
                        1
                    )
                )
                list.add(
                    DataBean(
                        "https://pic4.zhimg.com/v2-4deefdab2c3018588c6cef893cb9e9cb_1200x500.jpg",
                        null,
                        1
                    )
                )
                list.add(
                    DataBean(
                        "https://pic1.zhimg.com/v2-8ce59a6f96f9210b9e2206321262b54d_r.jpg?source=1940ef5c",
                        null,
                        1
                    )
                )
                return list
            }
    }
}