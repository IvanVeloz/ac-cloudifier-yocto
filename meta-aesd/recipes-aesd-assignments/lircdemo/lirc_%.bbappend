
FILESEXTRAPATHS:prepend := "${THISDIR}/lirc:"

SRC_URI += "\
    file://lircd.conf \
    file://lirc_options.conf \
"

do_install:append() {
    install -m 0644 ${WORKDIR}/lircd.conf ${D}${sysconfdir}/lirc/
    install -m 0755 -d ${D}${sysconfdir}/lirc/lircd.conf.d
    install -m 0644 ${WORKDIR}/lirc_options.conf ${D}${sysconfdir}/lirc/
}
