mkdir ~/.adb/
cp files/AdbAutoConnector.jar ~/.adb/
cp files/icon.png ~/.adb/
cp files/adb_autoconnector.desktop ~/.local/share/applications/
cp files/adb_autoconnector_configurator.desktop ~/.local/share/applications/
test -f ~/.local/shar/applications/adb_autoconnector_configurator.desktop && echo "installed!" || echo "failed to install"