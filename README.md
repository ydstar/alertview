## 一、简介
各种精仿iOS的弹框

动图演示如下：

<img src="https://github.com/ydstar/alertview/blob/master/preview/alertView.gif" alt="动图演示效果" width="250px">



## 二、导入方式
### 将JitPack存储库添加到您的构建文件中(项目根目录下build.gradle文件)
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

### 添加依赖项
仅支持`AndroidX`
```
dependencies {
    implementation 'com.github.ydstar:alertview:1.0.0'
}
```