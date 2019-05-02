# android-notification-log
**android-notification-log** is an android library derived from Interaction Lab's mobile application [*Notification Log*](https://github.com/interactionlab/android-notification-log) used for notification research on mobile devices. The original codebase was transfered to become a library while also refactoring the persistence layer to make use of Android's *Room persistence Library*.

## Third party libraries

* [Android v4 Support Library](https://developer.android.com/topic/libraries/support-library/packages#v4)
* [Android v7 Appcompat Library](https://developer.android.com/topic/libraries/support-library/packages#v7-appcompat)
* [Android v14 Preference Support Library](https://developer.android.com/topic/libraries/support-library/packages#v14-preference)
* [Android Room Persistence Library](https://developer.android.com/topic/libraries/architecture/room)
* [Google Location and Activity Recognition ](https://developers.google.com/android/guides/setup)

## Permissions
The following permissions are needed in preparation for capturing notifications and enriching each logged notification with network, physical activity/movement information. In order to use this library, you will need to register your app as notification listener for example by starting an intend to `android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS`.


*	`android.permission.ACCESS_NETWORK_STATE`
*	`com.google.android.gms.permission.ACTIVITY_RECOGNITION`
*	`android.permission.ACCESS_FINE_LOCATION`
*	`android.permission.ACCESS_COARSE_LOCATION`
*	`android.permission.BIND_NOTIFICATION_LISTENER_SERVICE`

## License 
* MIT - see [LICENSE](https://github.com/schwedenmut/android-notification-log/blob/master/LICENSE) file

## Version 
* Version 1.0.0

## How-to use this code
### Import with gradle
1. Add **jitpack.io** in your root *build.gradle* at the end of repositories:  
```groovy
allprojects {
        repositories {
            ...
            maven { url 'https://jitpack.io' }
        }
    }
```
2. add android-notification-log dependency:
```groovy
dependencies {
	implementation 'com.github.schwedenmut:android-notification-log:v1.0.0'
}
```
### Add manually
1.  Clone or download this repository
2.  Build library
3.	Copy files into libs directory in your app folder
	1.	for *.jar files: add this code to dependency on your gradle file
	2.	for *.aar files: try from projectstructure / new module/ import from aar/jar  
	`compile files('libs/library.jar')`
	
## Contact
Twitter: [@schwedenmut](https://twitter.com/schwedenmut "schwedenmut on twitter")
