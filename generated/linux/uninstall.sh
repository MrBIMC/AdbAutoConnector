rm -rf ~/.adb/
rm ~/.local/share/applications/adb_autoconnector.desktop
rm ~/.local/share/applications/adb_autoconnector_configurator.desktop
test -f ~/.local/shar/applications/adb_autoconnector_configurator.desktop && echo "uninstalled!" || echo "failed to uninstall"