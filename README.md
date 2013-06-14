s-tea3.0
========

这是S-Tea的重构版本
-------------------


#基于s-tea3.0的简单介绍：

#s-tea1.0版本在设计过程中，没有考虑到无界面运行的情况和前端自动化测试的情况，针对于javascript的测试很吃力，并且核心
架构封装的特别死板，所以在1.0的基础上我又重构了3.0的版本。3.0的版本主要在基于当前页面编程的基础上提供了一些可扩展的接口
比如结果类的接口result和资源类的source，并且s-tea3.0只提供了部分默认的接口功能，针对于运行器来说，还是基于junit的二次开发
。提供了基于JUnit的基础运行器，支持参数化和失败重试等功能，目前的版本中会加入一个结果过滤器，在针对测试结果中基于程序的异
常，会自动的划归为error结果中，并且支持测试失败的case结果，这些失败的case会划归为fail结果中。


#目前的s-tea3.0的开发工作值做完了部分核心功能。并没有过深的涉及结果分析阶段，并且s-tea3.0在着力于针对接口和http的
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
}
