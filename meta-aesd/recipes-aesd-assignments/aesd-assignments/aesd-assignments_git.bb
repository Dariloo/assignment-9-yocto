LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://git@github.com/Dariloo/aesd-assignments.git;protocol=ssh;branch=main \
           file://aesdsocket-start-stop \
"

PV = "1.0+git${SRCPV}"

SRCREV = "d452e03db41fabb8e1664c2833160e0cf630f407"

S = "${WORKDIR}/git/server"

FILES:${PN} += "${bindir}/aesdsocket ${sysconfdir}/init.d/aesdsocket"

inherit update-rc.d

INITSCRIPT_NAME = "aesdsocket"
INITSCRIPT_PARAMS = "defaults"

do_configure () {
    :
}

do_compile () {
    oe_runmake CROSS_COMPILE="${TARGET_PREFIX}"
}

do_install () {
    install -d ${D}${bindir}
    install -m 0755 ${S}/aesdsocket ${D}${bindir}/aesdsocket

    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/aesdsocket-start-stop ${D}${sysconfdir}/init.d/aesdsocket
}
