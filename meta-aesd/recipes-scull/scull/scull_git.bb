# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# The following license files were not able to be identified and are
# represented as "Unknown" below, you will need to check them yourself:
#   LICENSE
LICENSE = "Unknown"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f098732a73b5f6f3430472f5b094ffdb"

SRC_URI = "git://github.com/Dariloo/assignment-7-ldd.git;protocol=https;branch=main \
           file://0001-Build-only-scull-and-misc-modules.patch \
           file://scull-start-stop \
           "

# Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "e22c7e09763e0273ab8fa3c4f89326b1cd354d13"

S = "${WORKDIR}/git"

inherit module update-rc.d

EXTRA_OEMAKE += " -C ${STAGING_KERNEL_DIR} M=${S}/scull"
EXTRA_OEMAKE += "KERNELDIR=${STAGING_KERNEL_DIR}"
EXTRA_OEMAKE += "LDDINC=${S}/include"

INITSCRIPT_NAME = "scull"
INITSCRIPT_PARAMS = "defaults"

FILES:${PN} += "${sysconfdir}/init.d/scull"

do_install:append() {
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/scull-start-stop ${D}${sysconfdir}/init.d/scull
}
