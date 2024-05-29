# See https://git.yoctoproject.org/poky/tree/meta/files/common-licenses
LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

DEPENDS += "pigpio lirc"

# TODO: Set this  with the path to your assignments rep.  Use ssh protocol and see lecture notes
# about how to setup ssh-agent for passwordless access
SRC_URI = "git://git@github.com/IvanVeloz/ac-cloudifier;protocol=ssh;branch=main"

PV = "1.0+git${SRCPV}"
# TODO: set to reference a specific commit hash in your assignment repo
SRCREV = "1e896c5f0f5a84f96c611a0d93c756b9941f49c5"

# This sets your staging directory based on WORKDIR, where WORKDIR is defined at 
# https://docs.yoctoproject.org/ref-manual/variables.html?highlight=workdir#term-WORKDIR
# We reference the "server" directory here to build from the "server" directory
# in your assignments repo
S = "${WORKDIR}/git/acc-control"

TARGET_LDFLAGS += "-lrt -pthread -lpigpiod_if2 -llirc_client"

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
}


RDEPENDS: += "pigpio-bin-pigpiod libpigpio" 
