# See https://git.yoctoproject.org/poky/tree/meta/files/common-licenses
LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

DEPENDS += "opencv"

# TODO: Set this  with the path to your assignments rep.  Use ssh protocol and see lecture notes
# about how to setup ssh-agent for passwordless access
SRC_URI = "git://git@github.com/IvanVeloz/ac-cloudifier-demo;protocol=ssh;branch=main"

PV = "1.0+git${SRCPV}"
SRCREV = "6b9de233bc088442caacb911ac5eabebcb81967a"

# This sets your staging directory based on WORKDIR, where WORKDIR is defined at 
# https://docs.yoctoproject.org/ref-manual/variables.html?highlight=workdir#term-WORKDIR
# We reference the "server" directory here to build from the "server" directory
# in your assignments repo
S = "${WORKDIR}/git/opencv/opencvdemo"

inherit pkgconfig cmake

do_install() {
    install -d ${D}${bindir}
    install -m 0755 opencvdemo ${D}${bindir}
}

# NOTE: the image could be made smaller by using the individual opencv depends.
# rather than the entire metapackage.
RDEPENDS: += "opencv"
