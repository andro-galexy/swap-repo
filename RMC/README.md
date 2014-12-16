RMC
=======


###Developer&Maintainer: Swapnil J. Udapure###


###E-mail: swapnil.udapure5@gmail.com###


*Descrition: This application is used to control desktop from android application using android phones hotspot network; the application is simple but with limited functionality, the user can navigate through the user interface on their desktops, download files from the desktop, fire some terminal commands by opening the terminal window,browse the files/directories by using the defualt file browser respectively.The entire application is made in java so java sould be thier to do the job.*

*Also while downloading any file using the android app, user can either shake(left jerk) the phone or press the button at the top right most corner of the application respectively.*

*Application pre-requisites:*
*Ubuntu (Any Version) Only(Will not work with any distributions other than ubuntu, except having GNOME desktop installed...)*
*JDK, JRE(open-jdk-1.x.x)*
*GNU Make 2.xx and above.*

Now, How to use the application? first clone the repository; then follow the below explained instructions in order to install the application(both desktop and mobile side),once done then download and install the androind app from the link given below. After this; start your mobile hotspot network, and now start the RMC application on android side and enjoy.

*NOTE: Here one think to note, in order to use the application one has to start the android phone hotspot not ubuntu/desktop hotspot respectively.*


This application comes in two parts desktop side request handler which listens for the commands send by the android device and to execute these commands ; and android side application to control the desktop remotely; hence JRE(Open-jdk-1.7.0) must installed in order to use this application.. 
 
##This README explains the general usage information for using the makefile...##
RMC Makefile Infomation...


##Common usage for the makefile is...##

##In order to deploy this application following steps must takes place...

Usage : 

    sudo make [build|install|rm ext=<file_extension[class,txt,java,jar,etc...]>]

##In order to build all modules i.e. all .java files do following##

STEP-1

    cd path-to-rmc-dir

##This command will build all java classess and finally builds jar file from it...

STEP-2: 

    make build

##In order to install the build jar files and dependancy files following command will do the work...##

STEP-3: 

    make install


##After the application deployed the user can now control desktop from android RMC application; you can download and install the app from following link.

    https://www.dropbox.com/s/688i6i2zjhtegp7/RMC.apk?dl=0


##In order to remove unwanted files from CWD...##

    sudo make rm ext=<file_extension[jpg,txt,java,o,ko,etc...]>

NOTE: In order to build or compile java files for the purpose of debugging see the appropriate enrty labels in the makefile
for individually building or compiling the programs respectively.Also if the makefile is modified then do not forget to update this file.

For any other additional details see makefile...

Thank You!!!
