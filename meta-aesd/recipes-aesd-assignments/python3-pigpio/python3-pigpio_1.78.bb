SUMMARY = "Raspberry Pi Python module to access the pigpio daemon"
LICENSE = "Unlicense"
SRC_URI[sha256sum] = "91efa50e4990649da97408a384782d6ccf58342fc59cdfe21ed7a42911569975"

LIC_FILES_CHKSUM = "file://PKG-INFO;beginline=10;endline=10;md5=4c3eb9d07bf9f8a97e8943519c2751c6"


PYPI_PACKAGE = "pigpio"

inherit pypi setuptools3

RDEPENDS_${PN} += " \
    pigpio \
"