# This code block was based on content at 
# https://hub.mender.io/t/how-to-configure-networking-using-systemd-in-yocto-project/1097
# with modifications to use WLAN only.

FILESEXTRAPATHS:prepend := "${THISDIR}/systemd-conf:"

SRC_URI += " \
    file://wlan.network \
"

FILES:${PN} += " \
    ${sysconfdir}/systemd/network/wlan.network \
"

do_install:append() {
    install -d ${D}${sysconfdir}/systemd/network
    install -m 0644 ${WORKDIR}/wlan.network ${D}${sysconfdir}/systemd/network
}