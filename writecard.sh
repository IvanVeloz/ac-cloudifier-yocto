#! /bin/bash

RPI_ROOTFS_BZ2="./rpi-build/tmp/deploy/images/raspberrypi0-2w-64/core-image-aesd-raspberrypi0-2w-64.wic.bz2"

if [ -z "$1" ]; then
    echo "Usage: $0 <block device to write to>"
    echo "Example: $0 /dev/sdc"
    echo "ALL DATA IN THE BLOCK DEVICE WILL BE LOST"
else
    sudo bmaptool copy ${RPI_ROOTFS_BZ2} ${1}
    sync
    udisksctl power-off -b ${1}
fi
