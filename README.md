# FireWork
直播间送礼物之烟花效果

#效果图
![image](https://github.com/jingtianxiaozhi/FireWork/blob/master/gif/iclauncher.gif?raw=true)


#Get it

```java  
compile 'com.jingtianxiaozhi.firework:firework-core:1.0.0'
```
#How To Use(Too Simple)

```java  
//1.新建一个烟花效果(第二个参数是礼物资源Id)
FireWorkView fireWorkView = new FireWorkView(MainActivity.this, R.drawable.heart);
//2.在布局上增加烟花效果
LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
linearlayout.addView(fireWorkView, layoutParams);
//3.播放动画
fireWorkView.playAnim();
```

#Tanks For Leonids
https://github.com/plattysoft/Leonids
