inherit core-image

#CORE_IMAGE_EXTRA_INSTALL += "aesd-assignments"

# Essentials
#CORE_IMAGE_EXTRA_INSTALL += "wpa_supplicant linux-firmware-rpidistro-bcm43430"
CORE_IMAGE_EXTRA_INSTALL += "openssh"
CORE_IMAGE_EXTRA_INSTALL += "ntp"

# For comfort
CORE_IMAGE_EXTRA_INSTALL += "tmux mosh"
CORE_IMAGE_EXTRA_INSTALL += "nano"

# ac-cloudifier-demo
CORE_IMAGE_EXTRA_INSTALL += "lircdemo"
CORE_IMAGE_EXTRA_INSTALL += "pigpiodemo"
CORE_IMAGE_EXTRA_INSTALL += "opencvdemo"

# pigpiod needs to be installed globally for the systemd service to be installed
CORE_IMAGE_EXTRA_INSTALL += "pigpio-bin-pigpiod"

