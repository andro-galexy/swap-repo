#Application Name: Mounter
#Version: 0.0.1(Beta version)
#Developer&Maintainer: Swapnil J. Udapure
#Repository: https://github.com/swapgit/swap-repo
#E-mail: swapnil.udapure5@gmail.com*/

ifneq ($(KERNELRELEASE),)
	obj-m := $(file)

else
	KERNELDIR ?= /lib/modules/$(shell uname -r)/build
	PWD := $(shell pwd)
default:
	$(MAKE) -C $(KERNELDIR) M=$(PWD) modules

endif

jni:
	javah JNIConnection
	javap -s -p JNIConnection
	gcc -o netlinkuser.so -shared  -I /usr/lib/jvm/java-7-openjdk-i386/include/ -I /usr/lib/jvm/java-7-openjdk-i386/include/linux/ netlinkUser.c

java:
	javac *.java
	chown -hR $(USER) ./*.class

jar:
	mkdir ./jar
	jar -cvfm ./jar/Mounter.jar ./META-INF/MANIFEST.MF ./*.class ./org
	jar -cvfm ./jar/MounterCLI.jar ./META-INF/MANIFEST ./*.class ./org
	chmod a+x ./jar/*.jar
	chown -hR $(USER) ./jar

build:  java jni jar
	make default file=netlinkkernel.o
	make default file=my_module.o
	mkdir $(HOME)/.mounter
	cp netlinkuser.so $(HOME)/.mounter/
	cp ./icon/Lock-icon.png $(HOME)/.mounter/

run:
	java -jar /usr/local/bin/Mounter.jar

install:
	cp ./jar/*.jar /usr/local/bin
#	cp ./exec/Mounter.desktop $(HOME)/.config/autostart
	cat ./exec/stub.sh ./jar/MounterCLI.jar > /usr/local/bin/mounter
	chmod +x /usr/local/bin/mounter

insert:
	insmod netlinkkernel.ko
	insmod my_module.ko

remove:
	rmmod my_module
	rmmod netlinkkernel

rm:
	rm -f ./*.$(ext)
