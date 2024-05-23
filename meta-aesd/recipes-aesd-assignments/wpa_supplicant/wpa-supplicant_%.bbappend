# This code block was based on content at 
# https://hub.mender.io/t/how-to-configure-networking-using-systemd-in-yocto-project/1097
# with modifications to standardize file tree and use different 
# wpa_supplicant.conf content.

FILESEXTRAPATHS:prepend := "${THISDIR}/wpa_supplicant:"

SRC_URI += "file://wpa_supplicant-nl80211-wlan0.conf"

SYSTEMD_AUTO_ENABLE: = "enable"
SYSTEMD_SERVICE:${PN}:append = " wpa_supplicant-nl80211@wlan0.service"

do_install:append() {
    install -d ${D}${sysconfdir}/wpa_supplicant/
    install -D -m 0600 ${WORKDIR}/wpa_supplicant-nl80211-wlan0.conf ${D}${sysconfdir}/wpa_supplicant/

    install -d ${D}${sysconfdir}/systemd/system/multi-user.target.wants/
    ln -s ${systemd_unitdir}/system/wpa_supplicant@.service ${D}${sysconfdir}/systemd/system/multi-user.target.wants/wpa_supplicant-nl80211@wlan0.service
}
