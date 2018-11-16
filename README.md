[ ![Download](https://api.bintray.com/packages/subsub/maven/buttoncustomshadow/images/download.svg) ](https://bintray.com/subsub/maven/buttoncustomshadow/_latestVersion)

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Beauty%20Color%20Shadow%20Button-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/7165)

# What's New (1.1.0)
- **Beauty Layout, so you can make beautiful shadow on layout**
- **Accessible attributes through java/kotlin, so you can call custom shadow attributes inside your java/kotlin code**






# Beauty Shadow Button


![](capture.png)

## Usage
Add dependency into app build.gradle
```
compile "com.subsub.libs:buttoncustomshadow:{latestVersion}"
```

If gradle failed to sync, try adding maven repository into project build.gradle
```
buildscript{
    repositories {
        maven {
            url  "https://dl.bintray.com/subsub/maven" 
        }
    }
}
```

## Sample
See `app` module

#### Add BeautyButton or BeautyLayout into layout
```xml
<com.subsub.library.BeautyLayout
    android:id="@+id/l_layout"
    android:layout_width="0dp"
    android:layout_height="150dp"
    android:layout_margin="20dp"
    android:background="@drawable/bg_round_30dp_blue"
    android:elevation="15dp"
    android:padding="10dp"
    android:textColor="@color/colorWhite"
    app:layout_constraintEnd_toEndOf="parent"
    app:layout_constraintStart_toStartOf="parent"
    app:layout_constraintTop_toTopOf="parent"
    app:shadowColor="@color/colorDarkBlue"
    app:shadowPercentHeight="0.8"
    app:shadowPercentWidth="0.8"
    app:shadowRadius="30dp"
    app:shadowYOffset="30"
    app:text="Button1">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:gravity="center"
        android:orientation="vertical">

        <ImageView
            android:layout_width="40dp"
            android:layout_height="40dp"
            android:src="@drawable/ic_launcher_background" />

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Some other views" />
    </LinearLayout>
</com.subsub.library.BeautyLayout>
```


#### Attributes
Name | Type | Description
--- | --- | ---
`shadowRadius` | Dimension | This attribute (combined with elevation) determines how spread apart the shadow should be. ShadowRadius also determines the amount of corner radius (roundness) of the shadow. The bigger the shadowRadius is, the bigger the corner radius. ShadowRadius should be equal or bigger than elevation.
`shadowColor` | Color | The color of the shadow
`shadowXOffset` | Float | Sets shadow horizontal (x) position relative to the view.
`shadowYOffset` | Float | Sets shadow vertical (y) position relative to the view.
`shadowPercentWidth` | Float | Sets the percentage of shadow's width relative to the button width. Value is between 0 and 1, with 0 being 0% and 1 being 100% (shadow width = button width)
`shadowPercentHeight` | Float | Sets the percentage of shadow's height relative to the button height. Value is between 0 and 1, with 0 being 0% and 1 being 100% (shadow height = button height)

#### Accessing attributes from java
All custom attributes can be accessed through java like below:
```
// set elevation
button.myElevation = activity.viewElevation

// set shadow radius
button.customShadowRadius = activity.viewShadowRadius

// set xOffset
button.xOffset = activity.viewXOffset

// set yOffset
button.yOffset = activity.viewYOffset

// set percent height
button.shadowHeight = activity.viewShadowHeight

// set percent width
button.shadowWidth = activity.viewShadowWidth
```
