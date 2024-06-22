#! /bin/bash

if [ -z "$1" ]; then
    echo "Usage: $0 <block device to write to> <build suffix, if any>"
    echo "Example: $0 /dev/sdc nanbield"
    echo "ALL DATA IN THE BLOCK DEVICE WILL BE LOST"
    return 1
else
    if [ -z "$2" ]; then
        BUILD_SUFFIX=""
    else
        BUILD_SUFFIX="-${2}"
    fi
    RPI_ROOTFS_BZ2="./rpi-build-nanbield/tmp-glibc/deploy/images/raspberrypi0-2w-64/core-image-aesd-raspberrypi0-2w-64.rootfs.wic.bz2"
    sudo bmaptool copy ${RPI_ROOTFS_BZ2} ${1}
    sync
fi
