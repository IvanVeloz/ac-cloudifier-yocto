inherit core-image

#CORE_IMAGE_EXTRA_INSTALL += "aesd-assignments"

# Essentials
#CORE_IMAGE_EXTRA_INSTALL += "wpa_supplicant linux-firmware-rpidistro-bcm43430"
CORE_IMAGE_EXTRA_INSTALL += "openssh"
CORE_IMAGE_EXTRA_INSTALL += "ntp"

# For comfort
CORE_IMAGE_EXTRA_INSTALL += "tmux mosh"
CORE_IMAGE_EXTRA_INSTALL += "nano"
CORE_IMAGE_EXTRA_INSTALL += "htop"

# ac-cloudifier application
CORE_IMAGE_EXTRA_INSTALL += "acc-control"
CORE_IMAGE_EXTRA_INSTALL += "acc-machvis"

# ac-cloudifier-demo
CORE_IMAGE_EXTRA_INSTALL += "lircdemo"
CORE_IMAGE_EXTRA_INSTALL += "pigpiodemo"
CORE_IMAGE_EXTRA_INSTALL += "opencvdemo"

# pigpiod needs to be installed globally for the systemd service to be installed
CORE_IMAGE_EXTRA_INSTALL += "pigpio-bin-pigpiod"
CORE_IMAGE_EXTRA_INSTALL += "python3-pigpio"

# adding libcamera control through python for testing through command line
CORE_IMAGE_EXTRA_INSTALL += "libcamera libcamera-apps"

# adding pigs for pigpio control through the command line
CORE_IMAGE_EXTRA_INSTALL += "pigpio-bin-pigs"

# adding python3-opencv and v4l-utils for opencv testing through the command line
CORE_IMAGE_EXTRA_INSTALL += "python3-opencv v4l-utils"

# adding tcpdump and netcat for general network troubleshooting
CORE_IMAGE_EXTRA_INSTALL += "tcpdump netcat"
