package com.example.studyonline.data.bean

import android.text.GetChars
import com.example.studyonline.R
import com.orient.me.data.ITimeItem
import java.io.Serializable


class LessonBean(
    val id: Int,
    val name: String,
    val teacher: String,
    val introduction: String,
    val overview: String,
    val time: String,
    val startWeek: Int,
    val duration: Int,
    val tag: String,
    val outline: ArrayList<Outline>,
    val task: ArrayList<Task>
): Serializable {

    companion object {
        val testData1: List<LessonBean>
            get() {
                val list: MutableList<LessonBean> = ArrayList()
                list.add(
                    LessonBean(
                        1,
                        "Java",
                        "zhao",
                        "Java 是由 Sun Microsystems 公司于 1995 年 5 月推出的 Java 面向对象程序设计语言和 Java 平台的总称。",
                        "Java是一门面向对象编程语言，不仅吸收了C++语言的各种优点，" +
                                "还摒弃了C++里难以理解的多继承、指针等概念，因此Java语言具有功" +
                                "能强大和简单易用两个特征。Java语言作为静态面向对象编程语言的代表" +
                                "，极好地实现了面向对象理论，允许程序员以优雅的思维方式进行复杂的编程 [1]  。\n" +
                                "Java具有简单性、面向对象、分布式、健壮性、安全性、平台独立与可移植性、多线程、动" +
                                "态性等特点 [2]  。Java可以编写桌面应用程序、Web应用程序、分布式系统和嵌入式系统应用程序等 [3]  。",
                        "08:00_1",
                        1,
                        8,
                        "推荐",
                        ArrayList(listOf(
                            Outline(true,"Java 教程1"),
                            Outline(true,"Java 教程2"),
                            Outline(true,"Java 教程3"),
                            Outline(true,"Java 教程4"),
                            Outline(true,"Java 教程5"),
                            Outline(true,"Java 教程6"),
                            Outline(false,"Java 教程7"),
                            Outline(false,"Java 教程8"),
                            Outline(false,"Java 教程9"),
                            Outline(false,"Java 教程10"),
                            Outline(false,"Java 教程11"),
                            Outline(false,"Java 教程12")
                        )),
                        ArrayList(listOf(
                            Task("2021/04/16 10:00:00","Java 作业1", "第1次作业", R.color.purple_700,R.drawable.timeline_dot_first),
                            Task("2021/04/16 11:00:00","Java 作业2", "第2次作业", R.color.purple_700,R.drawable.timeline_dot_first),
                            Task("2021/04/16 12:00:00","Java 作业3", "第3次作业", R.color.purple_700,R.drawable.timeline_dot_first),
                            Task("2021/04/16 13:00:00","Java 作业4", "第4次作业", R.color.purple_700,R.drawable.timeline_dot_first),
                            Task("2021/04/16 14:00:00","Java 作业5", "第5次作业", R.color.purple_700,R.drawable.timeline_dot_first),
                            Task("2021/04/16 15:00:00","Java 作业6", "第6次作业", R.color.purple_700,R.drawable.timeline_dot_first),
                            Task("2021/04/16 16:00:00","Java 作业7", "第7次作业", R.color.purple_700,R.drawable.timeline_dot_first),
                            Task("2021/04/16 17:00:00","Java 作业8", "第8次作业", R.color.purple_700,R.drawable.timeline_dot_first),
                            Task("2021/04/16 18:00:00","Java 作业9", "第9次作业", R.color.purple_700,R.drawable.timeline_dot_first),
                            Task("2021/04/16 19:00:00","Java 作业10","第10次作业",  R.color.purple_700,R.drawable.timeline_dot_first)
                        ))
                    )
                )
                list.add(
                    LessonBean(
                        2,
                        "C/C++/C#",
                        "qian",
                        "C++ 是一种中级语言，它是由 Bjarne Stroustrup 于 1979 年在贝尔实验室开始设计开发的。",
                        "C语言是在70年代初问世的。一九七八年由美国电话电报公司(AT&T)贝尔实验室正式发表了C语言。同时由B.W.Kernighan和D.M.Ritchit合著了著名的“THE C PROGRAMMING LANGUAGE”一书。通常简称为《K&R》，也有人称之为《K&R》标准。但是，在《K&R》中并没有定义一个完整的标准C语言，后来由美国国家标准学会在此基础上制定了一个C 语言标准，于一九八三年发表。通常称之为ANSI C。",
                        "10:00_3",
                        1,
                        8,
                        "推荐",
                        ArrayList(listOf(
                            Outline(true,"C/C++/C# 教程1"),
                            Outline(true,"C/C++/C# 教程2"),
                            Outline(false,"C/C++/C# 教程3"),
                            Outline(false,"C/C++/C# 教程4"),
                            Outline(false,"C/C++/C# 教程5"),
                            Outline(false,"C/C++/C# 教程6"),
                            Outline(false,"C/C++/C# 教程7"),
                            Outline(false,"C/C++/C# 教程8"),
                            Outline(false,"C/C++/C# 教程9"),
                            Outline(false,"C/C++/C# 教程10"),
                            Outline(false,"C/C++/C# 教程11"),
                            Outline(false,"C/C++/C# 教程12")
                        )),
                        ArrayList(listOf(
                            Task("2021/04/16 10:00:00","C/C++/C# 作业1", "第1次作业", R.color.purple_700,R.drawable.timeline_dot_first),
                            Task("2021/04/16 11:00:00","C/C++/C# 作业2", "第2次作业", R.color.purple_700,R.drawable.timeline_dot_first),
                            Task("2021/04/16 12:00:00","C/C++/C# 作业3", "第3次作业", R.color.purple_700,R.drawable.timeline_dot_first),
                            Task("2021/04/16 13:00:00","C/C++/C# 作业4", "第4次作业", R.color.purple_700,R.drawable.timeline_dot_first),
                            Task("2021/04/16 14:00:00","C/C++/C# 作业5", "第5次作业", R.color.purple_700,R.drawable.timeline_dot_first),
                            Task("2021/04/16 15:00:00","C/C++/C# 作业6", "第6次作业", R.color.purple_700,R.drawable.timeline_dot_first),
                            Task("2021/04/16 16:00:00","C/C++/C# 作业7", "第7次作业", R.color.purple_700,R.drawable.timeline_dot_first),
                            Task("2021/04/16 17:00:00","C/C++/C# 作业8", "第8次作业", R.color.purple_700,R.drawable.timeline_dot_first),
                            Task("2021/04/16 18:00:00","C/C++/C# 作业9", "第9次作业", R.color.purple_700,R.drawable.timeline_dot_first),
                            Task("2021/04/16 19:00:00","C/C++/C# 作业10", "第10次作业", R.color.purple_700,R.drawable.timeline_dot_first)
                        ))
                    )
                )
                list.add(
                    LessonBean(
                        3,
                        "python",
                        "sun",
                        "Python 由 Guido van Rossum 于 1989 年底发明，第一个公开发行版发行于 1991 年。",
                        "Python由荷兰数学和计算机科学研究学会的Guido van Rossum 于1990 年代初设计，作为一门叫做ABC语言的替代品。 [1]  Python提供了高效的高级数据结构，还能简单有效地面向对象编程。Python语法和动态类型，以及解释型语言的本质，使它成为多数平台上写脚本和快速开发应用的编程语言， [2]  随着版本的不断更新和语言新功能的添加，逐渐被用于独立的、大型项目的开发。 [3] \n" +
                                "Python解释器易于扩展，可以使用C或C++（或者其他可以通过C调用的语言）扩展新的功能和数据类型。 [4]  Python 也可用于可定制化软件中的扩展程序语言。Python丰富的标准库，提供了适用于各个主要系统平台的源码或机器码。",
                        "14:00_2",
                        1,
                        8,
                        "推荐",
                        ArrayList(listOf(
                            Outline(true,"python 教程1"),
                            Outline(true,"python 教程2"),
                            Outline(true,"python 教程3"),
                            Outline(true,"python 教程4"),
                            Outline(true,"python 教程5"),
                            Outline(true,"python 教程6"),
                            Outline(true,"python 教程7"),
                            Outline(true,"python 教程8"),
                            Outline(true,"python 教程9"),
                            Outline(false,"python 教程10"),
                            Outline(false,"python 教程11"),
                            Outline(false,"python 教程12")
                        )),
                        ArrayList(listOf(
                            Task("2021/04/16 10:00:00","python 作业1", "第1次作业", R.color.purple_700,R.drawable.timeline_dot_first),
                            Task("2021/04/16 11:00:00","python 作业2", "第2次作业", R.color.purple_700,R.drawable.timeline_dot_first),
                            Task("2021/04/16 12:00:00","python 作业3", "第3次作业", R.color.purple_700,R.drawable.timeline_dot_first),
                            Task("2021/04/16 13:00:00","python 作业4", "第4次作业", R.color.purple_700,R.drawable.timeline_dot_first),
                            Task("2021/04/16 14:00:00","python 作业5", "第5次作业", R.color.purple_700,R.drawable.timeline_dot_first),
                            Task("2021/04/16 15:00:00","python 作业6", "第6次作业", R.color.purple_700,R.drawable.timeline_dot_first),
                            Task("2021/04/16 16:00:00","python 作业7", "第7次作业", R.color.purple_700,R.drawable.timeline_dot_first),
                            Task("2021/04/16 17:00:00","python 作业8", "第8次作业", R.color.purple_700,R.drawable.timeline_dot_first),
                            Task("2021/04/16 18:00:00","python 作业9", "第9次作业", R.color.purple_700,R.drawable.timeline_dot_first),
                            Task("2021/04/16 19:00:00","python 作业10","第10次作业", R.color.purple_700,R.drawable.timeline_dot_first)
                        ))
                    )
                )
                list.add(
                    LessonBean(
                        4,
                        "javascript",
                        "li",
                        "JavaScript 是 Web 的编程语言。所有现代的 HTML 页面都使用 JavaScript。",
                        "JavaScript（简称“JS”） 是一种具有函数优先的轻量级，解释型或即时编译型的编程语言。虽然它是作为开发Web页面的脚本语言而出名，但是它也被用到了很多非浏览器环境中，JavaScript 基于原型编程、多范式的动态脚本语言，并且支持面向对象、命令式和声明式（如函数式编程）风格。 [1] \n" +
                                "JavaScript在1995年由Netscape公司的Brendan Eich，在网景导航者浏览器上首次设计实现而成。因为Netscape与Sun合作，Netscape管理层希望它外观看起来像Java，因此取名为JavaScript。但实际上它的语法风格与Self及Scheme较为接近。 [2] \n" +
                                "JavaScript的标准是ECMAScript 。截至 2012 年，所有浏览器都完整的支持ECMAScript 5.1，旧版本的浏览器至少支持ECMAScript 3 标准。2015年6月17日，ECMA国际组织发布了ECMAScript的第六版，该版本正式名称为 ECMAScript 2015，但通常被称为ECMAScript 6 或者ES2015。 [1]",
                        "16:00_5",
                        1,
                        8,
                        "推荐",
                        ArrayList(listOf(
                            Outline(true,"javascript 教程1"),
                            Outline(true,"javascript 教程2"),
                            Outline(true,"javascript 教程3"),
                            Outline(true,"javascript 教程4"),
                            Outline(true,"javascript 教程5"),
                            Outline(true,"javascript 教程6"),
                            Outline(true,"javascript 教程7"),
                            Outline(true,"javascript 教程8"),
                            Outline(true,"javascript 教程9"),
                            Outline(true,"javascript 教程10"),
                            Outline(true,"javascript 教程11"),
                            Outline(true,"javascript 教程12")
                        )),
                        ArrayList(listOf(
                            Task("2021/04/16 10:00:00","javascript 作业1", "第1次作业", R.color.purple_700,R.drawable.timeline_dot_first),
                            Task("2021/04/16 11:00:00","javascript 作业2", "第2次作业", R.color.purple_700,R.drawable.timeline_dot_first),
                            Task("2021/04/16 12:00:00","javascript 作业3", "第3次作业", R.color.purple_700,R.drawable.timeline_dot_first),
                            Task("2021/04/16 13:00:00","javascript 作业4", "第4次作业", R.color.purple_700,R.drawable.timeline_dot_first),
                            Task("2021/04/16 14:00:00","javascript 作业5", "第5次作业", R.color.purple_700,R.drawable.timeline_dot_first),
                            Task("2021/04/16 15:00:00","javascript 作业6", "第6次作业", R.color.purple_700,R.drawable.timeline_dot_first),
                            Task("2021/04/16 16:00:00","javascript 作业7", "第7次作业", R.color.purple_700,R.drawable.timeline_dot_first),
                            Task("2021/04/16 17:00:00","javascript 作业8", "第8次作业", R.color.purple_700,R.drawable.timeline_dot_first),
                            Task("2021/04/16 18:00:00","javascript 作业9", "第9次作业", R.color.purple_700,R.drawable.timeline_dot_first),
                            Task("2021/04/16 19:00:00","javascript 作业10","第10次作业", R.color.purple_700,R.drawable.timeline_dot_first)
                        ))
                    )
                )
                list.add(
                    LessonBean(
                        5,
                        "php",
                        "zhou",
                        "PHP 是一种创建动态交互性站点的强有力的服务器端脚本语言。",
                        "PHP（Pre Hypertext Preprocessor）即“超文本预处理器”，是在服务器端执行的脚本语言，尤其适用于Web开发并可嵌入HTML中。PHP语法利用了C、Java和Perl，该语言的主要目标是允许web开发人员快速编写动态网页。 [1-2] \n" +
                                "PHP脚本程序主要应用于Web服务端开发，命令行和编写桌面应用程序。PHP支持和所有web开发语言之间的WDDX复杂数据交换。关于相互连接，PHP 已经支持了对Java对象的即时连接，并且可以透明地将其用作PHP对象。 [2]",
                        "20:00_4",
                        1,
                        8,
                        "推荐",
                        ArrayList(listOf(
                            Outline(true,"php 教程1"),
                            Outline(false,"php 教程2"),
                            Outline(false,"php 教程3"),
                            Outline(false,"php 教程4"),
                            Outline(false,"php 教程5"),
                            Outline(false,"php 教程6"),
                            Outline(false,"php 教程7"),
                            Outline(false,"php 教程8"),
                            Outline(false,"php 教程9"),
                            Outline(false,"php 教程10"),
                            Outline(false,"php 教程11"),
                            Outline(false,"php 教程12")
                        )),
                        ArrayList(listOf(
                            Task("2021/04/16 10:00:00","php 作业1", "第1次作业", R.color.purple_700,R.drawable.timeline_dot_first),
                            Task("2021/04/16 11:00:00","php 作业2", "第2次作业", R.color.purple_700,R.drawable.timeline_dot_first),
                            Task("2021/04/16 12:00:00","php 作业3", "第3次作业", R.color.purple_700,R.drawable.timeline_dot_first),
                            Task("2021/04/16 14:00:00","php 作业4", "第4次作业", R.color.purple_700,R.drawable.timeline_dot_first),
                            Task("2021/04/16 15:00:00","php 作业5", "第5次作业", R.color.purple_700,R.drawable.timeline_dot_first),
                            Task("2021/04/16 16:00:00","php 作业6", "第6次作业", R.color.purple_700,R.drawable.timeline_dot_first),
                            Task("2021/04/16 17:00:00","php 作业7", "第7次作业", R.color.purple_700,R.drawable.timeline_dot_first),
                            Task("2021/04/16 18:00:00","php 作业8", "第8次作业", R.color.purple_700,R.drawable.timeline_dot_first),
                            Task("2021/04/16 19:00:00","php 作业9","第9次作业", R.color.purple_700,R.drawable.timeline_dot_first)
                        ))
                    )
                )
                return list
            }
    }
}
class Outline(
    private val done: Boolean,
    val name: String
): Serializable, ITimeItem {

    override fun getTitle(): String {
        return ""
    }


    override fun getColor(): Int {
        return if (done)
            1
        else
            0
    }


    override fun getResource(): Int {
        return R.drawable.btn_round
    }
}

class Task(
    val commitTime: String,
    private val taskName: String,
    val taskOverview: String,
    private val color_: Int,
    private val res_: Int
): Serializable, ITimeItem {
    override fun getTitle(): String {
        return commitTime.substring(0, 10)
    }

    override fun getColor(): Int {
        return color_
    }

    override fun getResource(): Int {
        return res_
    }
}