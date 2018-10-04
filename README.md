[ ![Download](https://api.bintray.com/packages/subsub/maven/buttoncustomshadow/images/download.svg) ](https://bintray.com/subsub/maven/buttoncustomshadow/_latestVersion)

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Beauty%20Color%20Shadow%20Button-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/7165)


# Beauty Shadow Button


![](screenshot.png)

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

#### Add BeautyButton into layout
```xml
<com.subsub.library.BeautyButton
    android:id="@+id/button1"
    android:layout_width="0dp"
    android:layout_height="wrap_content"
    android:layout_margin="20dp"
    android:background="@drawable/bg_round_30dp_primary"
    android:elevation="10dp"
    android:padding="10dp"
    android:text="Button1"
    android:textColor="@color/colorWhite"
    app:layout_constraintEnd_toEndOf="parent"
    app:layout_constraintStart_toStartOf="parent"
    app:layout_constraintTop_toTopOf="parent"
    app:shadowColor="@color/colorPrimary"
    app:shadowPercentHeight="0.7"
    app:shadowPercentWidth="0.6"
    app:shadowRadius="30dp"
    app:shadowYOffset="10" />
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
  
