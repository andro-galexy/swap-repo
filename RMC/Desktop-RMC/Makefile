#Application Name: RMC
#Version: 0.0.1(Beta version)
#Developer&Maintainer: Swapnil J. Udapure
#Repository: https://github.com/swapgit/swap-repo/RMC
#E-mail: swapnil.udapure5@gmail.com

java:
	javac ./*.java
	chown -hR $(USER) ./*.class

jar:
	mkdir $(HOME)/.rmc
	jar -cvfm $(HOME)/.rmc/RMC.jar manifest ./*.class
	chmod a+x $(HOME)/.rmc/RMC.jar

build:  java jar

install:
	cp ./icon/RMC.png $(HOME)/.rmc
	chown -hR $(USER) $(HOME)/.rmc
	$(HOME)/.rmc/RMC.jar
	
rm:
	rm -f ./*.$(ext)
