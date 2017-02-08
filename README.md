# VideoViewDemo
一个VideoView的简单使用
用到了：
Android-ScalableVideoView GitHub地址：https://github.com/yqritc/Android-ScalableVideoView

VideoView的缓存：AndroidVideoCache GitHub地址：https://github.com/danikula/AndroidVideoCache
特别注意：view控件添加一个post线程后在Activity结束的时候remove掉该runnable，同理，handler也可以通过mHandler.removeCallbacksAndMessages(null);来移除队列中的消息，不然Activity退出后可能会产生空指针异常！
