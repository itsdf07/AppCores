# AppCores
Android原始Cores

----shape的各个属性
<?xml version="1.0" encoding="utf-8"?>  
<shape xmlns:android="http://schemas.android.com/apk/res/android"  
    android:shape="rectangle">  
    <!-- rectangle：矩形、圆角矩形、弧形等  
        oval：圆、椭圆  
        line：线、实线、虚线  
        ring：环形 -->  
  
    <corners  <!-- 圆角 只适用于rectangle类型-->  
        android:radius="integer"    <!-- 圆角半径 -->  
        android:bottomLeftRadius="integer"  
        android:bottomRightRadius="integer"  
        android:topLeftRadius="integer"  
        android:topRightRadius="integer" />  
  
    <gradient  <!-- 渐变色 -->  
        <!-- 渐变的角度，线性渐变时才有效，必须是45的倍数 -->  
        android:angle="integer"  
        <!-- 渐变中心的相对X、Y坐标，放射渐变时才有效 -->  
        android:centerX="integer"  
        android:centerY="integer"  
        <!-- 渐变的半径，放射渐变(radial)时才有效-->  
        android:gradientRadius="integer"  
        <!-- 渐变开始、中心、结束的颜色 -->  
        android:startColor="color"  
        android:centerColor="integer"  
        android:endColor="color"  
        <!-- 渐变的类型 linear线性、radial放射、sweep扫描-->  
        android:type=["linear" | "radial" | "sweep"]  
        <!--  是否可在LevelListDrawable中使用 -->  
        android:useLevel=["true" | "false"] />  
  
    <padding  <!-- 设置内容与形状边界的内间距 -->  
        android:left="integer"  
        android:top="integer"  
        android:right="integer"  
        android:bottom="integer" />  
  
    <size  <!-- 大小 -->  
        android:width="integer"  
        android:height="integer" />  
  
    <solid  <!-- 填充的颜色 -->  
        android:color="color" />  
  
    <stroke  <!-- 刻画边线 -->  
        android:width="integer"  
        android:color="color"  
        android:dashWidth="integer"   <!-- 虚线长度 -->  
        android:dashGap="integer" />  <!-- 虚线间隔 -->  
</shape> 
