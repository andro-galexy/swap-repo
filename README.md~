Mounter
=======


###Developer&Maintainer: Swapnil J. Udapure###


###E-mail: swapnil.udapure5@gmail.com###


*Descrition: This application is used to protect hard disk partion from direct mouting by the user; hence every time the user attempts to mount any partition; the dialog will be showned to enter the correct password for the mounting partition respectively. The application is some what simmilar to Bit-Locker program of Windows System. With the help of this application the user can maintain different passwords for different drives/partitions respectively.*


Depending on your Linux distribution kernel version; you must download the appropriate source from the repository;if you are using kernel version lower than 3.6 then download Mounter-0.0.1_Kernel-3.6; for kernel higher than 3.6 use Mounter-0.0.1_Kernel-High respectively.
Also the given application is only applicable with 32-bit Linux system; for those who are using 64-bit then they have to wait for next realese of the program; thank you.


This application comes in two parts kernel modules which handles the low level task of mounting partitions; and high level program which is completely made in java; hence JRE(Open-jdk-1.7.0) must installed in order to use this application.. 
 
##This README explains the general usage information for using the makefile...##
Mounter Application & Kernel Module Makefile Infomation...


##Common usage for the makefile is...##

##In order to deploy this application following steps must takes place...

Usage : 

    sudo make [build|insert|remove|run|install|rm ext=<file_extension[class,txt,java,o,ko,etc...]>]

##In order to build all modules and all .java files do following##
##This command will build both kernel modules as well as all java classess and finally builds jar file from it...

STEP-1: 

    sudo make build

##In order to install the build jar files and dependancy files following command will do the work...##

STEP-2: 

    sudo make install

*NOTE:Here one think to consider, the builded kernel modules are not going to be deployed/installed by defualt this is because the method for LKM installation varies from one distribution to another distribution; hence you need to manually install the kernel modules manually every time after the system boots, sorry for this inconvinience.*

##For Inserting Kernel module...##

STEP-3: 

    sudo make insert

##above command will insert both kernel modules...

##For running java program...##

STEP-4: 

    sudo make run


*NOTE:here note the above command / the java application must be run after the kernel modules inserted...*


##After the application deployed the user can change or apply password to every individual partition of the system this can be done by using the following command...
##Common Usage for the command is:
Usage : 
    sudo mounter -n <partition> -p <Password> 

##For removing Kernel modules...##

    sudo make remove

##In order to remove unwanted files from CWD...##

    sudo make rm ext=<file_extension[jpg,txt,java,o,ko,etc...]>

NOTE: In order to build or compile java files or kernel modules for the purpose of debugging see the appropriate enrty labels in the makefile
for individually building or compiling the programs respectively.Also if the makefile is modified then do not forget to update this file.

For any other additional details see makefile...

Thank You!!!
