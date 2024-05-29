#!/bin/bash
# Script to build image for qemu.
# Author: Siddhant Jajoo, Ivan Veloz

git submodule init
git submodule sync
git submodule update

# local.conf won't exist until this step on first execution
source poky/oe-init-build-env rpi-build-nanbield

# Add layers the project is dependent on
bitbake-layers add-layer ../meta-raspberrypi
bitbake-layers add-layer ../meta-openembedded/meta-oe
bitbake-layers add-layer ../meta-openembedded/meta-python
bitbake-layers add-layer ../meta-openembedded/meta-networking
bitbake-layers add-layer ../meta-openembedded/meta-multimedia

CONFLINE="MACHINE = \"raspberrypi0-2w-64\""
MACHINEFILE="../meta-aesd/conf/machine/raspberrypi0-2w-64.conf"
DISTROFILE="../meta-aesd/conf/distro/ac-cloudifier.conf"

cat conf/local.conf | grep "${CONFLINE}" > /dev/null
local_conf_info=$?

if [ $local_conf_info -ne 0 ];then
	echo "Append ${CONFLINE} in the local.conf file"
	echo ${CONFLINE} >> conf/local.conf
	echo "Append contents of ${MACHINEFILE} in the local.conf file"
	cat ${MACHINEFILE} >> conf/local.conf
	echo "Append contents of ${DISTROFILE} in the local.conf file"
	cat ${DISTROFILE} >> conf/local.conf
	
else
	echo "${CONFLINE} already exists in the local.conf file"
fi


bitbake-layers show-layers | grep "meta-aesd" > /dev/null
layer_info=$?

if [ $layer_info -ne 0 ];then
	echo "Adding meta-aesd layer"
	bitbake-layers add-layer ../meta-aesd
else
	echo "meta-aesd layer already exists"
fi

set -e
bitbake-layers show-layers
echo "STARTING BUILD"
bitbake core-image-aesd
