LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://git@github.com/Dariloo/aesd-assignments.git;protocol=ssh;branch=main            file://aesdchar-start-stop "

PV = "1.0+git${SRCPV}"
SRCREV = "e2d45b7cf2dbff2f1164d5cc9e995c79b65e3445"

S = "${WORKDIR}/git/aesd-char-driver"

inherit module update-rc.d

EXTRA_OEMAKE += "KERNELDIR=${STAGING_KERNEL_DIR}"

INITSCRIPT_NAME = "aesdchar"
INITSCRIPT_PARAMS = "start 10 2 3 4 5 . stop 90 0 1 6 ."

FILES:${PN} += "${sysconfdir}/init.d/aesdchar"
RDEPENDS:${PN} += "kernel-module-aesdchar"

do_compile() {
    oe_runmake KERNELDIR=${STAGING_KERNEL_DIR} modules
}

do_install() {
    install -d ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/extra
    install -m 0644 ${S}/aesdchar.ko         ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/extra/aesdchar.ko

    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/aesdchar-start-stop         ${D}${sysconfdir}/init.d/aesdchar
}
