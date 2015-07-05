# AdbAutoConnector ##WORK IN PROGRESS
I am too lazy to input "adb connect xxx.xxx.xxx.xxx" each time when I need to connect to my phone using adb-over network,
so I decided to make an app that will do it for me.

## END GOALS:

* Click on a icon - app will try to connect to a phone using pre-selected method and throw a notification on complete
* GUI configurator
* 3 algorithms:
  
  * [DEFAULT] Automatically scan all the ips from the same upnp zone, where computer is
  * Try ips in a given range
  * Connect to a specific ip


## What's left to do:

* Test whether linux notification works
* Make something to act as windows notifications (but idk what should i do here)
* pack it into app-bundle(for osx), make shortcuts for win && lin.
