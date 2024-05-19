inherit core-image
#CORE_IMAGE_EXTRA_INSTALL += "aesd-assignments"
CORE_IMAGE_EXTRA_INSTALL += "openssh"
CORE_IMAGE_EXTRA_INSTALL += "tmux mosh"

# ac-cloudifier-demo
CORE_IMAGE_EXTRA_INSTALL += "lircdemo"
CORE_IMAGE_EXTRA_INSTALL += "pigpio-bin-pigpiod pigpio-bin-pigs pigpio-bin-pig2vcd libpigpio pigpio-python3"
