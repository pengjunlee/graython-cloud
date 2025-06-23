import { ArticleType } from '@/api/model/articleModel'
import { PaginationResult, BaseResult } from '@/types/axios'
import cover1 from '@imgs/cover/img1.jpg'
import cover2 from '@imgs/cover/img2.jpg'
import cover3 from '@imgs/cover/img3.jpg'
import cover4 from '@imgs/cover/img4.jpg'
import cover5 from '@imgs/cover/img5.jpg'
import cover6 from '@imgs/cover/img6.jpg'
import cover7 from '@imgs/cover/img7.jpg'
import cover8 from '@imgs/cover/img8.jpg'
import cover9 from '@imgs/cover/img9.jpg'
import cover10 from '@imgs/cover/img10.jpg'

const ArticleList: ArticleType[] = [
  {
    id: 452,
    blog_class: '42',
    title: 'Node.js + Docker自动化部署',
    count: 56,
    create_time: '2024-08-26T00:00:00.000Z',
    home_img: cover1,
    brief:
      '本章将介绍 Node.js 使用 Docker 、Webhook 自动化部署、蓝绿部署、项目到服务器。1、Mac os 安装 Docker 客户端 OrbStack我这里使用的是第三方客户端，相比于官方的，较轻量，启动速度快OrbStack 是一种快速、轻便且简单的运行 Docker 容器和 Linux 的方法。使用我们的 Docker Desktop 替代方案以光速进行开发。下载地址： http',
    type_name: 'Node.js',
    html_content: ''
  },
  {
    id: 451,
    blog_class: '36',
    title: 'HTTP 协议',
    count: 109,
    create_time: '2024-02-22T00:00:00.000Z',
    home_img: cover2,
    brief:
      '概念HTTP（hypertext transport protocol）协议；中文叫超文本传输协议是一种基于TCP/IP的应用层通信协议这个协议详细规定了 浏览器 和万维网 服务器 之间互相通信的规则。协议中主要规定了两个方面的内容客户端：用来向服务器发送数据，可以被称之为请求报文服务端：向客户端返回数据，可以被称之为响应报文报文：可以简单理解为就是一堆字符串请求报文的组成请求行请求头空行请求体H',
    type_name: '浏览器',
    html_content: ''
  },
  {
    id: 450,
    blog_class: '40',
    title: 'MongoDB 数据库基本操作',
    count: 66,
    create_time: '2023-11-30T00:00:00.000Z',
    home_img: cover3,
    brief:
      '简介Mongodb 是什么MongoDB 是一个基于分布式文件存储的数据库，官方地址 https://www.mongodb.com/ 数据库是什么数据库（DataBase）是按照数据结构来组织、存储和管理数据的 应用程序数据库的作用数据库的主要作用就是 管理数据 ，对数据进行 增（c）、删（d）、改（u）、查（r）数据库管理数据的特点相比于纯文件管理数据，数据库管理数据有如下特点：1. 速度更快',
    type_name: 'MongoDB',
    html_content: ''
  },
  {
    id: 449,
    blog_class: '40',
    title: 'Mac os 安装 MongoDB',
    count: 59,
    create_time: '2023-11-15T00:00:00.000Z',
    home_img: cover4,
    brief:
      '下载MongoDB安装包官网下载地址：https://www.mongodb.com/try/download/community?tck=docs_server安装MongoDB# 将压缩包解压到 /usr/local/目录下\nsudo tar -zxvf mongodb-macos-x86_64-5.0.24.tgz -C /usr/local\n\n# 重命名\nsudo mv mongodb-o',
    type_name: 'MongoDB',
    html_content: ''
  },
  {
    id: 448,
    blog_class: '42',
    title: 'npm、yarn、nrm 常用命令',
    count: 91,
    create_time: '2023-11-07T00:00:00.000Z',
    home_img: cover5,
    brief:
      '设置镜像源#1,淘宝镜像源\nnpm config set registry https://registry.npmmirror.com\nnpm config set registry https://registry.npm.taobao.org\n\n#2,腾讯云镜像源\nnpm config set registry http://mirrors.cloud.tencent.com/npm/\n\n#',
    type_name: 'Node.js',
    html_content: ''
  },
  {
    id: 447,
    blog_class: '42',
    title: 'Node.js 包管理工具',
    count: 53,
    create_time: '2023-10-31T00:00:00.000Z',
    home_img: cover6,
    brief:
      '介绍包是什么『包』英文单词是 package ，代表了一组特定功能的源码集合包管理工具管理『包』的应用软件，可以对「包」进行 下载安装 ， 更新 ， 删除 ， 上传 等操作借助包管理工具，可以快速开发项目，提升开发效率包管理工具是一个通用的概念，很多编程语言都有包管理工具，所以 掌握好包管理工具非常重要常用的包管理工具下面列举了前端常用的包管理工具npmyarncnpmnpmnpm 全称 Node',
    type_name: 'Node.js',
    html_content: ''
  },
  {
    id: 446,
    blog_class: '42',
    title: 'Node.js 模块化',
    count: 40,
    create_time: '2023-10-25T00:00:00.000Z',
    home_img: cover7,
    brief:
      '介绍什么是模块化与模块 ?将一个复杂的程序文件依据一定规则（规范）拆分成多个文件的过程称之为其中拆分出的 每个文件就是一个模块 ，模块的内部数据是私有的，不过模块可以暴露内部数据以便其他模块使用什么是模块化项目 ?编码时是按照模块一个一个编码的， 整个项目就是一个模块化的项目模块化好处下面是模块化的一些好处：1.防止命名冲突2.高复用性3.高维护性模块暴露数据模块初体验可以通过下面的操作步骤，快速',
    type_name: 'Node.js',
    html_content: ''
  },
  {
    id: 445,
    blog_class: '42',
    title: 'Node.js学习笔记',
    count: 198,
    create_time: '2023-10-15T00:00:00.000Z',
    home_img: cover8,
    brief:
      'fs 模块fs 全称为 file system ，称之为 文件系统 ，是 Node.js 中的 内置模块 ，可以对计算机中的磁盘进行操作。例如文件的创建、删除、修改移动，文件内容的写入、读取，以及文件夹的相关操作// 1 ------------------------------------------------------\n/**\n * 需求\n * 新建一个文件，写入内容\n */\n\n// 1',
    type_name: 'Node.js',
    html_content: ''
  },
  {
    id: 444,
    blog_class: '41',
    title: '最好用的ChatGPT应用',
    count: 78,
    create_time: '2023-05-22T00:00:00.000Z',
    home_img: cover9,
    brief:
      '目前为止最好用的ChatGPT网站，支持6种AI模型和每日一次GPT4的使用机会，有网页版和ios应用程序，可以使用邮箱和手机号等方式注册，中国大陆可放心使用（需要科学上网），下面就介绍一下如何使用吧。网站地址：https://poe.com/进去之后可以选择邮箱或手机号等其他方式注册。除了网页版，你还可以到AppStore搜索poe下载ios客户端',
    type_name: 'GPT',
    html_content: ''
  },
  {
    id: 443,
    blog_class: '35',
    title: 'Nuxt 百度收录 robots 和 sitemap',
    count: 109,
    create_time: '2023-04-07T00:00:00.000Z',
    home_img: cover10,
    brief:
      '前言robots 和 sitemap 文件，前者的作用是减少百度蜘蛛在站内的无谓爬取，后者增加百度蜘蛛在站内的有效爬取，对百度收录和自己网站的SEO推广都十分重要。robots只有一个：robot.txt，这是一个文本文件，主要利用Allow（允许）和DisAllow（禁止）两个命令，（这两个重要的是禁止），禁止百度蜘蛛爬取一些无谓的文件和文件夹，增加百度搜录速度。具体原理和写法网上去搜，制作简单',
    type_name: 'Nuxt',
    html_content: ''
  },
  {
    id: 442,
    blog_class: '12',
    title: 'Vue3+TS+Vite 项目搭建笔记（更新中）',
    count: 516,
    create_time: '2023-04-03T00:00:00.000Z',
    home_img: cover4,
    brief:
      '介绍本章会教你在真实项目中如何搭建 VueRouter、Vuex、pinia、axios、主题切换等，你会见证一个后台管理系统的详细搭建过程。效果图：功能：后台管理系统常用模块登录加密多标签页全局面包屑国际化异常处理Utils工具包可配置的菜单栏徽标亮色 / 暗色 侧边栏浅色主题 / 暗黑主题丰富的个性化配置可折叠侧边栏支持内嵌页面重载当前页面动态路由支持自动重载支持多级路由嵌套及菜单栏嵌套分离路',
    type_name: 'Vue',
    html_content: ''
  },
  {
    id: 441,
    blog_class: '9',
    title: 'CSS 根据系统自动切换主题方案',
    count: 184,
    create_time: '2023-04-01T00:00:00.000Z',
    home_img: cover8,
    brief:
      '原理是改变 css 变量 + window.matchMedia 来监听系统主题变，从而实现点击改变主题和监听系统主题变化1、首先定义 css 全局变量创建 variables.scss 文件light color（浅色模式）定义浅色模式下 css 主题变量dark color（深色模式）定义深色模式下 css 主题变量// css全局变量\n:root {\n  // 文字大小\n  --art-fo',
    type_name: 'CSS',
    html_content: ''
  },
  {
    id: 440,
    blog_class: '36',
    title: '浏览器-安全',
    count: 116,
    create_time: '2023-03-28T00:00:00.000Z',
    home_img: cover2,
    brief:
      '通过这篇文章你可以了解到同源策略、跨站脚本攻击（xss）、跨域请求伪造（CSRF）以及安全沙箱相关知识；以下是本文的思维导图：（手机端可能看不清）获取高清 PDF，请在微信公众号【小狮子前端】回复【浏览器安全】同源策略什么是同源策略如果两个 URL 的协议、域名和端口都相同，我们就称这两个 URL 同源。两个不同的源之间若想要相互访问资源或者操作 DOM，那么会有一套基础的安全策略的制约，我们把这',
    type_name: '浏览器',
    html_content: ''
  },
  {
    id: 439,
    blog_class: '12',
    title: 'Vue-Router4',
    count: 135,
    create_time: '2023-02-08T00:00:00.000Z',
    home_img: cover3,
    brief:
      "路由模式构建 router.tsimport { createRouter, createWebHistory, createWebHashHistory, createMemoryHistory, createRouterMatcher } from 'vue-router'\nimport Home from '../views/home/index.vue'\nimport Login from",
    type_name: 'Vue',
    html_content: ''
  },
  {
    id: 438,
    blog_class: '10',
    title: 'Event Loop（事件循环）',
    count: 161,
    create_time: '2023-01-03T00:00:00.000Z',
    home_img: cover1,
    brief:
      'js是单线程的，一次只能执行一段代码。单线程会导致很多任务需要排队，一个个去执行，如果此时某个任务执行时间太长，就会出现阻塞，为了解决这个问题，js引入了事件循环机制。为什么要区分宏任务和微任务？js是单线程的，但是分同步异步微任务和宏任务皆为异步任务，它们都属于一个队列宏任务：script（整体代码）、setTimeout、setInterval、I/O、UI、 renderingsetImme',
    type_name: 'JavaScript',
    html_content: ''
  }
]

// 模拟数据服务
class ArticleMockService {
  static async getArticleList(params: any): Promise<PaginationResult<ArticleType[]>> {
    const { page = 1, size = 40, searchVal = '', year = '' } = params
    let filteredData = ArticleList
    // 模拟搜索过滤
    if (searchVal) {
      filteredData = ArticleList.filter((item) =>
        item.title.toLowerCase().includes(searchVal.toLowerCase())
      )
    }
    // 模拟年份过滤
    if (year && year !== 'All') {
      filteredData = filteredData.filter(
        (item) => new Date(item.create_time).getFullYear().toString() === year
      )
    }
    // 模拟分页
    const total = filteredData.length
    const startIndex = (page - 1) * size
    const endIndex = startIndex + size
    const data = filteredData.slice(startIndex, endIndex)
    // 模拟异步请求延迟
    await new Promise((resolve) => setTimeout(resolve, 500))
    return {
      code: 200,
      message: 'success',
      data,
      currentPage: page,
      pageSize: size,
      total,
      lastPage: Math.ceil(total / size)
    }
  }
  // 获取文章详情
  static async getArticleDetail(id: number): Promise<BaseResult<ArticleType>> {
    // 模拟异步请求延迟
    await new Promise((resolve) => setTimeout(resolve, 500))
    return {
      code: 200,
      message: '获取文章成功',
      data: {
        id: id,
        title: 'MongoDB 数据库基本操作',
        blog_class: '40',
        count: 66,
        create_time: '2024-08-06T16:00:00.000Z',
        home_img: 'https://www.qiniu.lingchen.kim/1723131502897.jpg',
        html_content:
          "<h2>简介</h2><h3>Mongodb 是什么</h3><p>MongoDB 是一个基于分布式文件存储的数据库，官方地址 <a href=\"https://www.mongodb.com/ \" target=\"_blank\">https://www.mongodb.com/ </a></p><h3>数据库是什么</h3><p>数据库（DataBase）是按照数据结构来组织、存储和管理数据的 应用程序</p><h3>数据库的作用</h3><p>数据库的主要作用就是 管理数据 ，对数据进行 增（c）、删（d）、改（u）、查（r）</p><h3>数据库管理数据的特点</h3><p>相比于纯文件管理数据，数据库管理数据有如下特点：</p><p>1. 速度更快</p><p>2. 扩展性更强</p><p>3. 安全性更强</p><h3>为什么选择 Mongodb</h3><p>操作语法与 JavaScript 类似，容易上手，学习成本低</p><h2>核心概念</h2><p>Mongodb 中有三个重要概念需要掌握</p><p>数据库（database） 数据库是一个数据仓库，数据库服务下可以创建很多数据库，数据库中可以存</p><p>放很多集合</p><p>集合（collection） 集合类似于 JS 中的数组，在集合中可以存放很多文档</p><p>文档（document） 文档是数据库中的最小单位，类似于 JS 中的对象</p><p><img src=\"https://www.qiniu.lingchen.kim/iShot_2024-03-01_15.04.59.png\" alt=\"\" data-href=\"\" style=\"\"/></p><h2>命令行交互</h2><p>命令行交互一般是学习数据库的第一步，不过这些命令在后续用的比较少，所以大家了解即可</p><h3>数据库命令</h3><p>显示所有的数据库</p><pre><code >show dbs</code></pre><p>切换到指定的数据库，如果数据库不存在会自动创建数据库</p><pre><code >use 数据库名</code></pre><p>显示当前所在的数据库</p><pre><code >db</code></pre><p>删除当前数据库</p><pre><code >use 库名\ndb.dropDatabase()</code></pre><h3>集合命令</h3><p>1创建集合</p><pre><code >db.createCollection('集合名称')</code></pre><p>显示当前数据库中的所有集合</p><pre><code >show collections</code></pre><p>删除某个集合</p><pre><code >db.集合名.drop()</code></pre><p>重命名集合</p><pre><code >db.集合名.renameCollection('newName')</code></pre><h3>文档命令</h3><p>插入文档</p><pre><code >db.集合名.insert(文档对象);</code></pre><p>查询文档</p><pre><code >db.集合名.find(查询条件)</code></pre><p>_id 是 mongodb 自动生成的唯一编号，用来唯一标识文档</p><p>更新文档</p><pre><code >db.集合名.update(查询条件,新的文档)\ndb.集合名.update({name:'张三'},{$set:{age:19}})</code></pre><p>删除文档</p><pre><code >db.集合名.remove(查询条件)</code></pre><h2>Mongoose</h2><h3>介绍</h3><p>Mongoose 是一个对象文档模型库，官网 http://www.mongoosejs.net/</p><h3>作用</h3><p>方便使用代码操作 mongodb 数据库</p><h3>使用流程</h3><pre><code >//1. 安装 mongoose\n//2. 导入 mongoose\nconst mongoose = require('mongoose');\n//3. 连接数据库\nmongoose.connect('mongodb://127.0.0.1:27017/bilibili');\n//4. 设置连接回调\n//连接成功\nmongoose.connection.on('open', () =&gt; {\n  console.log('连接成功');\n  //5. 创建文档结构对象let BookSchema = new mongoose.Schema({\n    title: String,\n    author: String,\n    price: Number\n  });\n  //6. 创建文档模型对象let BookModel = mongoose.model('book', BookSchema);\n  //7. 插入文档\n  BookModel.create({\n    title: '西游记',\n    author: '吴承恩',\n    price: 19.9\n  }, (err, data) =&gt; {\n    if (err) throw err;\n    //输出 data 对象console.log(data);\n    //8. 断开连接\n    mongoose.disconnect();\n  });\n});\n//连接出错\nmongoose.connection.on('error', () =&gt; {\n  console.log('连接出错~~');\n})\n//连接关闭\nmongoose.connection.on('close', () =&gt; {\n  console.log('连接关闭');\n})</code></pre><h3>字段类型</h3><p>文档结构可选的常用字段类型列表</p><p>String 字符串</p><p>Number 数字</p><p>Boolean 布尔值</p><p>Array 数组，也可以使用 [] 来标识</p><p>Date 日期</p><p>Buffer Buffer 对象</p><p>Mixed 任意类型，需要使用 mongoose.Schema.Types.Mixed 指定</p><p>ObjectId 对象 ID，需要使用 mongoose.Schema.Types.ObjectId 指定</p><p>Decimal128 高精度数字，需要使用 mongoose.Schema.Types.Decimal128 指定</p><p>5.5 字段值验证</p><p>Mongoose 有一些内建验证器，可以对字段值进行验证</p><h3>必填项</h3><pre><code >title: {\n  type: String,\n  required: true // 设置必填项\n}</code></pre><p>默认值</p><pre><code >author: {\n  type: String,\n  default: '匿名' //默认值\n}</code></pre><h3>枚举值</h3><pre><code >gender: {\n  type: String,\n  enum: ['男','女'] //设置的值必须是数组中的\n}</code></pre><h3>唯一值</h3><pre><code >username: {\n  type: String,\n  unique: true\n}</code></pre><p>CURD</p><p>数据库的基本操作包括四个，增加（create），删除（delete），修改（update），查（read）</p><h3>增加</h3><p>插入一条</p><pre><code >SongModel.create({\n  title:'给我一首歌的时间',\n  author: 'Jay'\n}, function(err, data){\n  //错误console.log(err);\n  //插入后的数据对象console.log(data);\n});</code></pre><p>批量插入</p><pre><code >//1.引入mongoose\nconst mongoose = require('mongoose');\n//2.链接mongodb数据库 connect 连接\nmongoose.connect('mongodb://127.0.0.1:27017/project');\n//3.设置连接的回调\nmongoose.connection.on('open', () =&gt; {\n  //4.声明文档结构const PhoneSchema = new mongoose.Schema({\n    brand: String,\n    color: String,\n    price: Number,\n    tags: Array\n  })\n  //6.创建模型对象const PhoneModel = mongoose.model('phone', PhoneSchema);\n  PhoneModel.insertMany([\n    {\n      brand: '华为',\n      color: '灰色',\n      price: 2399,\n      tags: ['电量大', '屏幕大', '信号好']\n    },\n    {\n      brand: '小米',\n      color: '白色',\n      price: 2099,\n      tags: ['电量大', '屏幕大', '信号好']\n    }\n  ], (err, data) =&gt; {\n    if (err) throw err;\n    console.log('写入成功');\n    mongoose.connection.close();\n  })\n})</code></pre><h3>删除</h3><p>删除一条数据</p><pre><code >SongModel.deleteOne({ _id: '5dd65f32be6401035cb5b1ed' }, function (err) {\n  if (err) throw err;\n  console.log(' 删 除 成 功 '); mongoose.connection.close();\n});</code></pre><p>批量删除</p><pre><code >SongModel.deleteMany({author:'Jay'}, function(err){ if(err) throw err;\n  console.log('删除成功');\n  mongoose.connection.close();\n});</code></pre><h3>更新</h3><p>更新一条数据</p><pre><code >SongModel.updateOne({ author: 'JJ Lin' }, { author: '林俊杰' }, function (err) {\n  if (err) throw err;\n  mongoose.connection.close();\n});</code></pre><p>批量更新数据</p><pre><code >SongModel.updateMany({ author: 'Leehom Wang' }, { author: '王力宏' }, function (err) {\n  if (err) throw err;\n  mongoose.connection.close();\n});</code></pre><h3>查询</h3><h3>查询一条数据</h3><pre><code >SongModel.findOne({ author: '王力宏' }, function (err, data) {\n  if (err) throw err;\n  console.log(data); mongoose.connection.close();\n});\n//根据 id 查询数据\nSongModel.findById('5dd662b5381fc316b44ce167', function (err, data) {\n  if (err) throw err;\n  console.log(data);\n  mongoose.connection.close();\n});</code></pre><p>批量查询数据</p><pre><code >//不加条件查询\nSongModel.find(function (err, data) {\n  if (err) throw err;\n  console.log(data); mongoose.connection.close();\n});\n//加条件查询\nSongModel.find({ author: '王力宏' }, function (err, data) {\n  if (err) throw err;\n  console.log(data);\n  mongoose.connection.close();\n});</code></pre><h3>条件控制</h3><p>运算符</p><p>在 mongodb 不能 &gt; &lt; &gt;= &lt;= !== 等运算符，需要使用替代符号</p><pre><code >db.students.find({id:{$gt:3}}); id号比3大的所有的记录</code></pre><p>使 用 $gt</p><p>&lt; 使 用 $lt</p><p>= 使 用 $gte</p><p>&lt;= 使 用 $lte</p><p>!== 使 用 $ne</p><h3>逻辑或的情况</h3><pre><code >db.students.find({$or:[{age:18},{age:24}]});</code></pre><h3>逻辑与的情况</h3><pre><code >db.students.find({$and: [{age: {$lt:20}}, {age: {$gt: 15}}]});</code></pre><h3>正则匹配</h3><pre><code >db.students.find({name:/imissyou/});</code></pre><h3>性化读取</h3><pre><code >//0:不要的字段\n//1:要的字段\nSongModel.find().select({ _id: 0, title: 1 }).exec(function (err, data) {\n  if (err) throw err; console.log(data); mongoose.connection.close();\n});</code></pre><h3>数据排序</h3><pre><code >//sort 排序\n//1:升序\n//-1:倒序\nSongModel.find().sort({ hot: 1 }).exec(function (err, data) {\n  if (err) throw err;\n  console.log(data); mongoose.connection.close();\n});</code></pre><h3>数据截取</h3><pre><code >//skip 跳过\tlimit 限定\nSongModel.find().skip(10).limit(10).exec(function (err, data) {\n  if (err) throw err; console.log(data); mongoose.connection.close();\n});</code></pre><p><br></p><p><br></p><p><br></p>",
        brief: ''
      }
    }
  }

  // 新增文章
  // eslint-disable-next-line @typescript-eslint/no-unused-vars
  static async addArticle(params: any): Promise<BaseResult> {
    await new Promise((resolve) => setTimeout(resolve, 500))
    return {
      code: 200,
      message: '新增文章成功',
      data: {}
    }
  }

  // 编辑文章
  // eslint-disable-next-line @typescript-eslint/no-unused-vars
  static async editArticle(id: number, params: any): Promise<BaseResult> {
    await new Promise((resolve) => setTimeout(resolve, 500))
    return {
      code: 200,
      message: '编辑文章成功',
      data: {}
    }
  }
}

export default ArticleMockService
