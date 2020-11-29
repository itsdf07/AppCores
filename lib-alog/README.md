@[TOC](目录)
>以下`ALog`工具仅提供对`android.util.Log`的重新封装
# 依赖方式
`Gradle`引用方式
- 必要时可在`Project`的`build.gradle`增加工具库的目标地址
```bash
buildscript {
    repositories {
        maven {
            url 'https://bintray.com/itsdf07/core/'//指定长裤地址
        }
    }
}

allprojects {
    repositories {
        maven {
            url 'https://dl.bintray.com/itsdf07/core'
        }
    }
}
......

```
- 在需要使用该工具库的Module中的`build.gradle`中添加依赖
```bash
implementation 'com.itsdf07:lib-alog:0.0.1'
```
# 使用方式
## 初始化过程
```bash
ALog.init()
            .setLogLevel(ALogLevel.FULL) //是否打印log，默认开启
            .setTag("自定义Tag") //自定义tag，默认TAG = "itsdf07"
            .setDefineALogFilePath("xxx/xxx/xxx.log") //自定义log存储路径，默认SDCard根目录下的ALOG文件夹
            .setLog2Local(true) //设置是否本地存储log记录，默认关闭
            .setShowThreadInfo(true)//是否显示线程信息，默认不显示
            .setMethodCount(2); //显示函数栈中的方法数，默认显示2个
```
## 使用方式
```bash
ALog.d("这是第 %s 次 Log 输出", 6);
ALog.dTag("aso", "这是第 %s 次 Log 输出", 7);
```
关闭显示线程信息Log输出如下

```bash
03-25 10:51:15.327 18073-18073/com.itsdf07.app.alog D/itsdf07: ╔════════════════════════════════════════════════════════════════════════════════════════
03-25 10:51:15.331 18073-18073/com.itsdf07.app.alog D/itsdf07: ║ onCreate (MainActivity.java:22) [ALog-Content] 这是第 6 次 Log 输出
03-25 10:51:15.331 18073-18073/com.itsdf07.app.alog D/itsdf07: ╚════════════════════════════════════════════════════════════════════════════════════════
03-25 10:51:15.331 18073-18073/com.itsdf07.app.alog D/aso: ╔════════════════════════════════════════════════════════════════════════════════════════
03-25 10:51:15.332 18073-18073/com.itsdf07.app.alog D/aso: ║ onCreate (MainActivity.java:23) [ALog-Content] 这是第 7 次 Log 输出
03-25 10:51:15.332 18073-18073/com.itsdf07.app.alog D/aso: ╚════════════════════════════════════════════════════════════════════════════════════════

```

开启显示线程信息Log输出如下
```bash
03-25 10:50:08.064 17002-17002/com.itsdf07.app.alog D/itsdf07: ╔════════════════════════════════════════════════════════════════════════════════════════
03-25 10:50:08.065 17002-17002/com.itsdf07.app.alog D/itsdf07: ║Thread:main
03-25 10:50:08.065 17002-17002/com.itsdf07.app.alog D/itsdf07: ╟────────────────────────────────────────────────────────────────────────────────────────
03-25 10:50:08.065 17002-17002/com.itsdf07.app.alog D/itsdf07: ║ Activity.performCreate  (Activity.java:7372)
03-25 10:50:08.065 17002-17002/com.itsdf07.app.alog D/itsdf07: ║    MainActivity.onCreate  (MainActivity.java:22)
03-25 10:50:08.065 17002-17002/com.itsdf07.app.alog D/itsdf07: ╟────────────────────────────────────────────────────────────────────────────────────────
03-25 10:50:08.069 17002-17002/com.itsdf07.app.alog D/itsdf07: ║ [ALog-Content] 这是第 6 次 Log 输出
03-25 10:50:08.069 17002-17002/com.itsdf07.app.alog D/itsdf07: ╚════════════════════════════════════════════════════════════════════════════════════════
03-25 10:50:08.070 17002-17002/com.itsdf07.app.alog D/aso: ╔════════════════════════════════════════════════════════════════════════════════════════
03-25 10:50:08.070 17002-17002/com.itsdf07.app.alog D/aso: ║Thread:main
03-25 10:50:08.070 17002-17002/com.itsdf07.app.alog D/aso: ╟────────────────────────────────────────────────────────────────────────────────────────
03-25 10:50:08.071 17002-17002/com.itsdf07.app.alog D/aso: ║ Activity.performCreate  (Activity.java:7372)
03-25 10:50:08.071 17002-17002/com.itsdf07.app.alog D/aso: ║    MainActivity.onCreate  (MainActivity.java:23)
03-25 10:50:08.071 17002-17002/com.itsdf07.app.alog D/aso: ╟────────────────────────────────────────────────────────────────────────────────────────
03-25 10:50:08.071 17002-17002/com.itsdf07.app.alog D/aso: ║ [ALog-Content] 这是第 7 次 Log 输出
03-25 10:50:08.071 17002-17002/com.itsdf07.app.alog D/aso: ╚════════════════════════════════════════════════════════════════════════════════════════

```

开放功能如下
```bash
    /**
     * 当前是否打印Log
     *
     * @return
     */
    isLog();


    /**
     * ALog.v("isFIleExist = %s, innerBasePath = %s", isFileExist, innerBasePath);
     *
     * @param message 要打印的内容
     * @param args    打印信息中的动态数据
     */
    v(String message, Object... args);

    /**
     * ALog.v("itsdf07", "isFIleExist = %s, innerBasePath = %s", isFileExist, innerBasePath);
     *
     * @param tag     tag
     * @param message 要打印的内容
     * @param args    打印信息中的动态数据
     */
    vTag(String tag, String message, Object... args);

    d(String message, Object... args);

    dTag(String tag, String message, Object... args);

    i(String message, Object... args);

    iTag(String tag, String message, Object... args);

    w(String message, Object... args);

    wTag(String tag, String message, Object... args);

    e(String message, Object... args);

    eTag(String tag, String message, Object... args);

    eTag(String tag, Throwable throwable, String message, Object... args);

    wtf(String message, Object... args);

    wtfTag(String tag, String message, Object... args);

    wtfTag(String tag, Throwable throwable, String message, Object... args);

    /**
     * JSON格式数据打印
     *
     * @param json
     */
    json(String json);

    /**
     * XML格式数据打印
     *
     * @param xml the xml content
     */
    xml(String xml);
```