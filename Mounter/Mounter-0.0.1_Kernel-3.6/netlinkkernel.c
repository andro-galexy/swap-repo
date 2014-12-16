/*Application Name: Mounter
Version: 0.0.1(Beta version)
Developer&Maintainer: Swapnil J. Udapure
Repository: https://github.com/swapgit/swap-repo
E-mail: swapnil.udapure5@gmail.com*/

#include <linux/module.h>
#include <net/sock.h>
#include <linux/netlink.h>
#include <linux/skbuff.h>

#define NETLINK_USER 31
int size;
int count = 0;
EXPORT_SYMBOL(count);
struct sock *nl_sk = NULL;
unsigned char *CHECK = NULL;
int SPIN_LOCK=0;
char *msg="swappy";
EXPORT_SYMBOL(msg);
EXPORT_SYMBOL(CHECK);
EXPORT_SYMBOL(size);
EXPORT_SYMBOL(SPIN_LOCK);
void send_back(void);

struct nlmsghdr *nlh;
int pid;
int res;
struct sk_buff *skb_out;
int msg_size=0;

static void hello_nl_recv_msg(struct sk_buff *skb) {

msg_size=strlen(msg);

printk(KERN_INFO "Entering: %s\n", __FUNCTION__);

nlh=(struct nlmsghdr*)skb->data;
CHECK=(unsigned int*)nlmsg_data(nlh);
printk(KERN_INFO "Netlink received From UserSpace:%s\n",CHECK);
//printk(KERN_INFO "Check len :%d\n",strlen(CHECK));

pid = nlh->nlmsg_pid; /*pid of sending process */

skb_out = nlmsg_new(msg_size,0);

if(!skb_out)
{
    printk(KERN_ERR "Failed to allocate new skb\n");
    return;
} 

}

void send_back()
{
    //printk(KERN_INFO "Entering Send_back()\n");
msg_size=size;
//nlh=(struct nlmsghdr*)skb_out->data;
    //printk(KERN_INFO "msg len: %d\n",msg_size);
skb_out = nlmsg_new(msg_size,0);

if(!skb_out)
{
    printk(KERN_ERR "Failed to allocate new skb\n");
    return;
} 
//printk("\ndev name is: %s",msg[]);
//printk("\ndir name is: %s",,msg[]);

nlh=nlmsg_put(skb_out,0,0,NLMSG_DONE,msg_size,0);  
NETLINK_CB(skb_out).dst_group = 0; /* not in mcast group */
memcpy(nlmsg_data(nlh),msg,msg_size);
res=nlmsg_unicast(nl_sk,skb_out,pid);

if(res<0)
    printk(KERN_INFO "Error while sending back to user\n");
    printk(KERN_INFO "Sending Signal to UserProccess\n");

while(strlen(CHECK)!=2)
{
/*wait untill the signal transffered back from user space to kernel space...*/
SPIN_LOCK++;
}
printk(KERN_INFO "Spin_lock Count is: %d\n",SPIN_LOCK);
SPIN_LOCK=0;
    //printk(KERN_INFO "Leaving send_back()\n");
}
EXPORT_SYMBOL(send_back);


int __init hello_init(void) {

printk("Entering: %s\n",__FUNCTION__);
// This is for 3.6 kernels and above.
/*struct netlink_kernel_cfg cfg = {
    .input = hello_nl_recv_msg,
};

nl_sk = netlink_kernel_create(&init_net, NETLINK_USER, &cfg);*/
nl_sk = netlink_kernel_create(&init_net, NETLINK_USER, 0, hello_nl_recv_msg,NULL,THIS_MODULE);
if(!nl_sk)
{

    printk(KERN_ALERT "Error creating socket.\n");
    return -10;

}

return 0;
}


static void __exit hello_exit(void) {

printk(KERN_INFO "exiting netlink module\n");
netlink_kernel_release(nl_sk);
}

module_init(hello_init); 
module_exit(hello_exit);

MODULE_LICENSE("GPL");
