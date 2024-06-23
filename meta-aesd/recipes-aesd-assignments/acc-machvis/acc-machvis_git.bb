# See https://git.yoctoproject.org/poky/tree/meta/files/common-licenses
LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

DEPENDS += "${PYTHON_PN} ${PYTHON_PN}-pip"

SRC_URI = "git://git@github.com/IvanVeloz/ac-cloudifier;protocol=ssh;branch=main"
PV = "1.0+git${SRCPV}"
SRCREV = "62a79b702b23d2c04d8921be0bf7f2a5e21f231a"

S = "${WORKDIR}/git/acc-machvis"

RDEPENDS: += "${PYTHON_PN} ${PYTHON_PN}-numpy ${PYTHON_PN}-opencv"
RDEPENDS: += "opencv libcamera libcamera-apps"

do_install:append() {
    install -d ${D}${bindir}
    install -m 0755 ${S}/utils/deviceside/acc-vid.sh ${D}${bindir}
}


inherit setuptools3
