# This code block was based on content at 
# https://gist.github.com/dir-ableton/7ad9045e242165a9e4707c84e6df754b?permalink_comment_id=4222014#gistcomment-4222014
# with modifications to:
#
# * Update the license type, 
# * Fix compatibility with current version 
#   - specify branch (previously defaulting to `master` which is deprecated now)
#   - specify version with SRCREV instead of tag
# * Fix old syntax for appends (e.g. FILES_${PN} becomes FILES:${PN})
# * Install the systemd service file and make it part of the daemon package

SUMMARY = "pigpio is a C library for the Raspberry which allows control of the General Purpose Input Outputs (GPIO)."
DESCRIPTION = "pigpio is a C library for the Raspberry which allows control of the General Purpose Input Outputs (GPIO)."
SECTION = "utils"
LICENSE = "Unlicense"
LIC_FILES_CHKSUM = "file://UNLICENCE;md5=61287f92700ec1bdf13bc86d8228cd13"

SRC_URI = "git://github.com/joan2937/pigpio.git;protocol=https;branch=master"
SRCREV = "c33738a320a3e28824af7807edafda440952c05d"

S = "${WORKDIR}/git"

EXTRA_OEMAKE += "CC='${CC}'"
EXTRA_OEMAKE += "CROSS_PREFIX=${TARGET_PREFIX}"
# we don't need to strip, it will be stripped by build system
EXTRA_OEMAKE += "STRIP=echo"
EXTRA_OEMAKE += "PYINSTALLARGS='--root=$(DESTDIR) --prefix=${prefix}'"
TARGET_CC_ARCH += "${LDFLAGS}"

# gpio package will be empty with depends from all packages
ALLOW_EMPTY:${PN} = "1"
ALLOW_EMPTY:${PN}-dbg = "0"
ALLOW_EMPTY:${PN}-dev = "0"

FILES:${PN}-bin-pigs    = "${bindir}/pigs"
FILES:${PN}-bin-pig2vcd = "${bindir}/pig2vcd"

FILES:${PN}-bin-pigpiod    = "${bindir}/pigpiod"
FILES:${PN}-bin-pigpiod   += "${systemd_unitdir}/system/pigpiod.service"
RDEPENDS:${PN}-bin-pigpiod = "lib${PN}"

# *-bin package will be empty with depends from all *-bin-* packages
RDEPENDS:${PN}-bin = " ${PN}-bin-pigpiod ${PN}-bin-pigs ${PN}-bin-pig2vcd"
ALLOW_EMPTY:${PN}-bin = "1"

FILES:lib${PN}  = "${libdir}/lib${PN}.so.*"
FILES:lib${PN}  =+ "/opt/${PN}/cgi"

FILES:lib${PN}-if  = "${libdir}/lib${PN}_if.so.*"
FILES:lib${PN}-if2 = "${libdir}/lib${PN}_if2.so.*"

#FILES:${PN}-dev = "${includedir}/*.h"
FILES:${PN}-dev += "${libdir}/lib${PN}*.so"
FILES:${PN}-doc = "${mandir}"

FILES:${PN}-python2 = "${libdir}/python2*/*"
FILES:${PN}-python3 = "${libdir}/python3*/*"

SYSTEMD_PACKAGES = "${PN}-bin-pigpiod"
SYSTEMD_SERVICE:${PN}-bin-pigpiod = "pigpiod.service"
SYSTEMD_AUTO_ENABLE:${PN}-bin-pigpiod = "enable"

inherit systemd
PACKAGECONFIG[systemd] = "--with-systemdsystemunitdir=${systemd_unitdir}/system/,--without-systemdsystemunitdir,systemd"

PACKAGECONFIG ?= " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', ' systemd', '', d)} \
"

PACKAGES =+ " ${PN}-bin-pigpiod ${PN}-bin-pigs ${PN}-bin-pig2vcd \
              lib${PN} lib${PN}_if lib${PN}_if2 \
              ${PN}-python2 ${PN}-python3"

do_install() {
    oe_runmake install DESTDIR=${D} prefix=${prefix} mandir=${mandir}
}

do_install:append() {
    if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
        install -m 755 -d ${D}${systemd_unitdir}/system
        install -m 0644 ${S}/util/pigpiod.service ${D}${systemd_unitdir}/system/
    fi
}

inherit lib_package
