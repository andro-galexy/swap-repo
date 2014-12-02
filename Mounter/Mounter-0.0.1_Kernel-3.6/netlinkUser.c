/*Application Name: Mounter
Version: 0.0.1(Beta version)
Developer&Maintainer: Swapnil J. Udapure
Repository: https://github.com/swapgit/swap-repo
E-mail: swapnil.udapure5@gmail.com*/

#include <sys/socket.h>
#include <linux/netlink.h>
#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include <jni.h>
#include "JNIConnection.h"

#define NETLINK_USER 31

#define MAX_PAYLOAD 1024 /* maximum payload size*/
struct sockaddr_nl src_addr, dest_addr;
struct nlmsghdr *nlh = NULL;
struct iovec iov;
int sock_fd;
char *path;
struct msghdr msg;
struct try{
char dev_name[16];
char dir_name[50];
}t1;
struct try *tp;
FILE *ptr_file;
char buf[10];
void invoke_class(JNIEnv* env, char * dev_name, char *dir_name);

JNIEXPORT jint JNICALL Java_JNIConnection_main (JNIEnv *env, jobject job)
{
sock_fd=socket(PF_NETLINK, SOCK_RAW, NETLINK_USER);
if(sock_fd<0)
return -1;
buf[0]='O';buf[1]='K';
path = strcat(getenv("HOME"),"/.mounter/mounter");
loop:
memset(&src_addr, 0, sizeof(src_addr));
src_addr.nl_family = AF_NETLINK;
src_addr.nl_pid = getpid(); /* self pid */

bind(sock_fd, (struct sockaddr*)&src_addr, sizeof(src_addr));

memset(&dest_addr, 0, sizeof(dest_addr));
memset(&dest_addr, 0, sizeof(dest_addr));
dest_addr.nl_family = AF_NETLINK;
dest_addr.nl_pid = 0; /* For Linux Kernel */
dest_addr.nl_groups = 0; /* unicast */

nlh = (struct nlmsghdr *)malloc(NLMSG_SPACE(MAX_PAYLOAD));
memset(nlh, 0, NLMSG_SPACE(MAX_PAYLOAD));
nlh->nlmsg_len = NLMSG_SPACE(MAX_PAYLOAD);
nlh->nlmsg_pid = getpid();
nlh->nlmsg_flags = 0;

strcpy(NLMSG_DATA(nlh), buf);

iov.iov_base = (void *)nlh;
iov.iov_len = nlh->nlmsg_len;
msg.msg_name = (void *)&dest_addr;
msg.msg_namelen = sizeof(dest_addr);
msg.msg_iov = &iov;
msg.msg_iovlen = 1;

printf("Sending message to kernel\n");
sendmsg(sock_fd,&msg,0);
printf("Waiting for message from kernel\n");

/* Read message from kernel */
 recvmsg(sock_fd, &msg, 0);
if(strlen((char *)NLMSG_DATA(nlh))>0){tp = (struct try *)NLMSG_DATA(nlh);

invoke_class(env,&tp->dev_name,&tp->dir_name);

ptr_file =fopen(path,"r");
if (!ptr_file)
printf("err open file");
while (fgets(buf,10, ptr_file)!=NULL)
printf("%s",buf);
fclose(ptr_file);

if(strcmp(buf,"ok")==0)
{
buf[0]='m';buf[1]='n';
}
else
{
buf[0]='n';buf[1]='o';
}

}
goto loop;
close(sock_fd);
}

void invoke_class(JNIEnv* env, char * dev_name, char *dir_name) {
   
    jclass helloWorldClass;
    jmethodID mainMethod;
    jobjectArray applicationArgs;
    jstring applicationArg0;
    jstring applicationArg1;
    
    helloWorldClass = (*env)->FindClass(env, "JNIConnection");

    mainMethod = (*env)->GetStaticMethodID(env, helloWorldClass, "setIp", "(Ljava/lang/String;Ljava/lang/String;)V");

    applicationArg0 = (*env)->NewStringUTF(env, dev_name);
    applicationArg1 = (*env)->NewStringUTF(env, dir_name);
    (*env)->CallStaticVoidMethod(env, helloWorldClass, mainMethod, applicationArg0, applicationArg1);
}

