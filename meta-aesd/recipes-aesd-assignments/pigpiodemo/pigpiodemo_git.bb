# See https://git.yoctoproject.org/poky/tree/meta/files/common-licenses
LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

DEPENDS += "pigpio"

# TODO: Set this  with the path to your assignments rep.  Use ssh protocol and see lecture notes
# about how to setup ssh-agent for passwordless access
SRC_URI = "git://git@github.com/IvanVeloz/ac-cloudifier-demo;protocol=ssh;branch=main"

PV = "1.0+git${SRCPV}"
# TODO: set to reference a specific commit hash in your assignment repo
SRCREV = "ac2a753d2f5afbf6aec71127ca614cea038bda89"

# This sets your staging directory based on WORKDIR, where WORKDIR is defined at 
# https://docs.yoctoproject.org/ref-manual/variables.html?highlight=workdir#term-WORKDIR
# We reference the "server" directory here to build from the "server" directory
# in your assignments repo
S = "${WORKDIR}/git/pigpio/pigpiodemo"

TARGET_LDFLAGS += "-pthread -lrt -lpigpiod_if2"

do_configure () {
	:
}

do_compile () {
	oe_runmake
}

do_install () {
	install -d ${D}${bindir}
	install -m 0755 ${S}/pigpiodemo ${D}${bindir}/pigpiodemo
}

RDEPENDS: += "pigpio-bin-pigpiod libpigpio"
# uncomment pigpio-bin-pigs for CLI control
RDEPENDS: += "pigpio-bin-pigs" 
