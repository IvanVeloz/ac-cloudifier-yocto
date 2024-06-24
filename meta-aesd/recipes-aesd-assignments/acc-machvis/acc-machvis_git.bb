# See https://git.yoctoproject.org/poky/tree/meta/files/common-licenses
LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

DEPENDS += "${PYTHON_PN} ${PYTHON_PN}-pip"

SRC_URI = "git://git@github.com/IvanVeloz/ac-cloudifier;protocol=ssh;branch=main"
PV = "1.0+git${SRCPV}"
SRCREV = "ca532f5e7b697a52d72b51c267ec76319add2747"
SRC_URI:append = " file://acc-machvis.service "
SRC_URI:append = " file://acc-vid.service "

S = "${WORKDIR}/git/acc-machvis"


SYSTEMD_AUTO_ENABLE = "enable"
SYSTEMD_SERVICE:${PN} = "acc-vid.service acc-machvis.service"
FILES:${PN} += "${systemd_unitdir}/system/acc-vid.service"
FILES:${PN} += "${systemd_unitdir}/system/acc-machvisf.service"

do_install:append() {
    install -d ${D}${bindir}
    install -m 0755 ${S}/utils/deviceside/acc-vid.sh ${D}${bindir}
    install -d ${D}/${systemd_unitdir}/system/
	install -m 0644 ${WORKDIR}/acc-vid.service ${D}/${systemd_unitdir}/system/acc-vid.service
    install -m 0644 ${WORKDIR}/acc-machvis.service ${D}/${systemd_unitdir}/system/acc-machvis.service
}

RDEPENDS: += "${PYTHON_PN}"
RDEPENDS: += "${PYTHON_PN}-numpy ${PYTHON_PN}-opencv"
RDEPENDS: += "python3-pigpio"
RDEPENDS: += "opencv libcamera libcamera-apps"

inherit setuptools3
inherit systemd
