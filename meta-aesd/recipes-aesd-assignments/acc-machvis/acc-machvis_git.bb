# See https://git.yoctoproject.org/poky/tree/meta/files/common-licenses
LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

DEPENDS += "${PYTHON_PN} ${PYTHON_PN}-pip"

SRC_URI = "git://git@github.com/IvanVeloz/ac-cloudifier;protocol=ssh;branch=main"
PV = "1.0+git${SRCPV}"
SRCREV = "90a6a9a92b997170f73d43d6e909c2a2f39038c1"

S = "${WORKDIR}/git/acc-machvis"

RDEPENDS: += "${PYTHON_PN} ${PYTHON_PN}-numpy ${PYTHON_PN}-opencv" 

inherit setuptools3
