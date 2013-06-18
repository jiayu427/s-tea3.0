s-tea3.0
========

这是S-Tea的重构版本
-------------------


###基于s-tea3.0的简单介绍：

<p>  <a href="http://www.github.com/celeskyking/s-tea"><strong>s-tea1.0<strong></a>版本在设计过程中，没有考虑到无界面运行的情况和前端自动化测试的情况，针对于javascript的测试很吃力，并且核心
架构封装的特别死板，所以在1.0的基础上我又重构了3.0的版本。3.0的版本主要在基于当前页面编程的基础上提供了一些可扩展的接口
比如结果类的接口result和资源类的source，并且s-tea3.0只提供了部分默认的接口功能，针对于运行器来说，还是基于junit的二次开发
。提供了基于JUnit的基础运行器，支持参数化和失败重试等功能，目前的版本中会加入一个结果过滤器，在针对测试结果中基于程序的异
常，会自动的划归为error结果中，并且支持测试失败的case结果，这些失败的case会划归为fail结果中。</p>


   目前的s-tea3.0的开发工作值做完了部分核心功能。并没有过深的涉及结果分析阶段，并且s-tea3.0在着力于针对接口和http的
交互为主要实现。鉴于java和js的交互复杂性，可能会提供了一个基于javascript的Http的浏览器版本。这个版本主要用来测试Http方面
或者请求方面的处理，现在的处理方式是通过phantomjs来提供脚本，通过java来解析结果来实现HAR的规范解析。来获取自动化过程中的
连贯性和持续集成性。

s-tea3.0的风格：

    @Test
    public void runjsTest(){
        Auto.require(Browser.PhantomJS);
        Auto.open("http://www.baidu.com");
        Auto.browser().runJavaScript("alert(\"hello,world\")");
        Auto.closeAllWindows();
    }

现在提交的s-tea3.0的代码中，提供的功能相对于webdriver自身有几点优化：
1、不需要自定义frame元素，现在实现的方式是现在当前页面中查找，找不到的话会自动的去frame中查找元素。
2、提供了xml的定位元素的方式，里面只有三个元素<browser>,<page>,<element>。browser代表当前浏览器下的元素，<page>内的元素会
通过page的commit属性来自动的归档到page类中。无需手动加载资源。提供了全注解的元素定义方式。可以把元素定义在page类中，可以不
使用xml来定义。
3、提供了JUnit的运行器，JUnitBaseRunner,这个运行器可以运行多线程，参数化，以及失败重试的功能。多线程需要借助@ThreadRunner注解
失败重试需要借助@Retry的注解，参数化需要借助@Source的注解。
4、s-tea3.0支持Phantomjs来运行我们的case，phantomjs是一个js编写的webkit的内核浏览器，没有界面，但是支持CSS3和HTML5，它要比
htmlunit的功能更加的强大。
5、s-tea3.0支持动作监听功能，它能够监听我们一般经常操作的鼠标和键盘操作。在操作前和操作后进行一些监听功能。
6、s-tea3.0提供了一个窗口自动监听的系统。它会分析当前页面的窗口是否变化，如果有变化会有提示。便于我们通过日志能够更清晰的
了解程序运行的过程。并且可以通过索引来进行窗口之间的切换。
