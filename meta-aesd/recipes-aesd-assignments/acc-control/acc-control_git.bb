# See https://git.yoctoproject.org/poky/tree/meta/files/common-licenses
LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

DEPENDS += "pigpio lirc mosquitto"

SRC_URI = "git://git@github.com/IvanVeloz/ac-cloudifier;protocol=ssh;branch=main"
PV = "1.0+git${SRCPV}"
SRCREV = "ca532f5e7b697a52d72b51c267ec76319add2747"
SRC_URI:append = " file://acc-control.service "

S = "${WORKDIR}/git/acc-control"

TARGET_LDFLAGS += "-lrt -pthread -lpigpiod_if2 -llirc_client -lmosquitto"

SYSTEMD_AUTO_ENABLE = "enable"
SYSTEMD_SERVICE:${PN} = "acc-control.service"
FILES:${PN} += "${systemd_unitdir}/system/acc-control.service"

do_configure () {
	:
}

do_compile () {
	oe_runmake
}

do_install () {
	install -d ${D}${bindir}
	install -m 0755 ${S}/bin/acc-control ${D}${bindir}/acc-control
}

do_install:append() {
	install -d ${D}${sysconfdir}
	install -d ${D}${sysconfdir}/lirc
	install -d ${D}${sysconfdir}/lirc/lircd.conf.d
	install -m 0644 ${S}/etc/GE-AHP05LZQ2.lircd.conf ${D}${sysconfdir}/lirc/lircd.conf.d/${PN}_GE-AHP05LZQ2.lircd.conf
	install -d ${D}/${systemd_unitdir}/system/
	install -m 0644 ${WORKDIR}/acc-control.service ${D}/${systemd_unitdir}/system/acc-control.service
}

RDEPENDS: += "pigpio-bin-pigpiod libpigpio lirc mosquitto" 

inherit systemd

