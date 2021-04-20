package com.example.studyonline.data.bean

class NewsBean(val title: String, val text: String, val imageUri: String) {
    companion object {
        val testData2: List<NewsBean>
            get() {
                val list: MutableList<NewsBean> = ArrayList()
                list.add(
                    NewsBean(
                        "1111111111111",
                        "aaaaaaaaaaaaa",
                        "https://yunkai.com/20191213000031-4c90873b8c55b68ab52475f44253dcdb.png"
                    )
                )
                list.add(
                    NewsBean(
                        "222222222222222222",
                        "bbbbbbbbbbbbbbbbbb",
                        "https://pic4.zhimg.com/v2-f4ec9cf931dbb26eb637ded356c9f5c7_r.jpg"
                    )
                )
                list.add(
                    NewsBean(
                        "33333333333333",
                        "cccccccccccccc",
                        "http://www.120xjxc.com/wp-content/uploads/2020/08/184-2.jpg"
                    )
                )
                list.add(
                    NewsBean(
                        "44444444444444",
                        "ddddddddddddddd",
                        "https://pic4.zhimg.com/v2-4deefdab2c3018588c6cef893cb9e9cb_1200x500.jpg"
                    )
                )
                list.add(
                    NewsBean(
                        "555555555555555",
                        "eeeeeeeeeeeeeeee",
                        "https://pic1.zhimg.com/v2-8ce59a6f96f9210b9e2206321262b54d_r.jpg?source=1940ef5c"
                    )
                )
                return list
            }
    }
}