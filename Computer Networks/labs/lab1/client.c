#include <netinet/ip.h>
#include <arpa/inet.h>
#include <string.h>

char buf[8] = "hello";
int sfd;
struct sockaddr_in soc;

int main (){
    sfd=socket(AF_INET, SOCK_DGRAM, 0);
    
    soc.sin_family=AF_INET;
    soc.sin_port=htons(7777);
    soc.sin_addr.s_addr=inet_addr("127.0.0.1");
    
    sendto (sfd,buf,strlen(buf),0,&soc,sizeof(struct sockaddr_in));
}