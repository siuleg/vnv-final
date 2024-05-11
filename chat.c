
/* Name of the student: Miguel A. Lucero */

#include <asm-generic/errno-base.h>
#include <netinet/in.h>
#include <stdio.h>
#include <errno.h>
#include <string.h>
#include <stdint.h>
#include <sys/select.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <arpa/inet.h>

/* Definition of a static length for the buffer for each read() ->
   write() transmission 
*/
#define BUFFER_LEN ((size_t) 4096)

/* Type definition for the state of the server */
typedef struct {
  int listening_fd;
  int peer_one_fd;
  int peer_two_fd;
} state_t;

/* A function to parse a string to a port number on a 16bit unsigned
   integer.

   Returns zero if everything worked and *port has been written to.

   Returns a negative number if something went wrong parsing the
   input.

*/
static int parse_port(uint16_t *port, const char *str) {
  unsigned long int t1, t3;
  uint16_t t2;
  char *end;

  if (str == NULL) return -1;
  if (*str == '\0') return -1;

  t1 = strtoul(str, &end, 0);
  if (*end != '\0') return -1;

  t2 = (uint16_t) t1;
  t3 = (unsigned long long int) t2;

  if (t1 != t3) return -1;

  *port = t2;
  return 0;
}

/* A wrapper around write() */
ssize_t my_write(int fd, const void *buf, size_t count) {
  size_t s_r, s_d, wr;
  ssize_t t;

  for (s_r = count, s_d = ((size_t) 0);
       s_r > ((size_t) 0);
       s_r -= wr, s_d += wr) {
    t = write(fd, &(((char *) buf)[s_d]), s_r);
    if (t < ((ssize_t) 0)) return t;
    if (t == ((ssize_t) 0)) break;
    wr = (size_t) t;
  } 
  return ((ssize_t) s_d);
}

/* A function to open a listening TCP/IP socket 
   at port port.

   Returns a file descriptor that is a IPv4 TCP/IP socket that has
   been bound to port port and that has been set into listening 
   mode.

   Calls socket(), bind() and listen() in this order.

   Returns a negative number if anything went wrong, after 
   printing a error message and cleaning up after itself.

*/
static int open_listening_fd(uint16_t port) {
  int sockfd;
  struct sockaddr_in serveraddr;
  // create a socket IPv4 TCP 
  sockfd = socket(AF_INET, SOCK_STREAM, 0);
  if (sockfd < 0) {
    fprintf(stderr, "Cannot create a socket: %s\n", strerror(errno));
    return -1;
  }

  // Bind the socket to a port number 
  memset(&serveraddr, 0, sizeof(serveraddr));
  serveraddr.sin_family = AF_INET;
  serveraddr.sin_addr.s_addr = htonl(INADDR_ANY);
  serveraddr.sin_port = htons(port);
  if (bind(sockfd, (struct sockaddr *) &serveraddr, sizeof(serveraddr)) < 0) {
    fprintf(stderr, "Cannot bind a socket: %s\n", strerror(errno));
    if (close(sockfd)) {
      fprintf(stderr, "Cannot close a socket: %s\n", strerror(errno));
    }
    return -1;
  }

  /* Listen on the socket for incoming connections */
  if (listen(sockfd, 2) < 0) {
    fprintf(stderr, "Cannot listen on a socket: %s\n", strerror(errno));
    if (close(sockfd)) {
      fprintf(stderr, "Cannot close a socket: %s\n", strerror(errno));
    }
    return -1;
  }

  return sockfd;
}

/* Main function for the chat server. 

   All state is held in the state argument.

   The function assumes that the state has already
   been initialized with a new, bound and listening 
   acceptance port.

   It will perform the accepts on peer one and peer two,
   if needed repeatedly for different peers and will
   copy everything received from a peer one to peer two 
   and vice versa.

   Returns a negative number if an error which cannot be 
   recovered from has occured. Loops forever otherwise.

   See the description below for more details.

*/
static int run_chat_server(state_t *state) {
  /* TODO, local variable declarations */
 
  
  /* Loop forever, in each iteration doing the following:

     + If I have at least one unknown peer (fd is negative for peer one
       or peer two), look if a new connection can be accepted.

     + Otherwise, I have two peers:
        +  Try to read from peer one, writing to peer two.
        +  Try to read from peer two, writing to peer one.
        -> If one of the peers signal end-of-file, close their
           file descriptor and set the peer file descriptor
           back to -1. Close only their file descriptor.
        -> If a write fails on a peer, close the file descriptor
           of that peer and set it back to -1.

        CAUTION: Do not just call accept() or read() because
                 that call will block until there is something to 
                 be accepted or read. You NEED to use select() to find
                 on which of the 3 (or 2) file descriptors you can perform
                 an accept() (which calls read()) or a read().

		 You may be able to circumvent the use of select() for
		 the accept() but you will need to use it for the two
		 read() calls on peer one and peer two, as you do not
		 know who types first. 

		 Any attempt at trying to get around the use of
		 select() by spawing threads, forking of children,
		 setting the file descriptors non-blocking or timeouts
		 will receive very, very, very little credit.

		 YOU NEED TO USE select(). 

	For any accept(), the length of the client address information
	must be checked.

	CAUTION: You need to make sure peer one stays connected when 
	         peer two disconnects (reaches EOF), waiting for a new
		 peer two to come in, and vice versa.

        In case of an error, try to recover. If that is impossible,
        return -1. Cleaning up is not required.

  */ 
  fd_set read_fds;
  int max_fd;
  char buffer[BUFFER_LEN];
  struct sockaddr_in clientaddr;
  socklen_t clientaddrlen;

  for (;;) {
        FD_ZERO(&read_fds);
        FD_SET(state->peer_one_fd, &read_fds);
        FD_SET(state->peer_two_fd, &read_fds);
        FD_SET(state->listening_fd, &read_fds);
        max_fd = (state->listening_fd > state->peer_one_fd) ? state->listening_fd : state->peer_one_fd;
        max_fd = (max_fd > state->peer_two_fd) ? state->listening_fd : state->peer_two_fd;

        int activity = select(max_fd + 1, &read_fds, NULL, NULL, NULL);
        if (activity < 0) {
            perror("select");
            return -1;
        }

        if (FD_ISSET(state->listening_fd, &read_fds)) {
            clientaddrlen = sizeof(clientaddr);
            int connfd = accept(state->listening_fd, (struct sockaddr*)&clientaddr, &clientaddrlen);
        
          if(state->peer_one_fd ==-1){
           state->peer_one_fd = connfd;
          } else if (state->peer_two_fd == -1) {
            state->peer_two_fd = connfd;
          }else{
            close(connfd);
          }
      }

    if(FD_ISSET(state->peer_one_fd, &read_fds)){
      ssize_t bytes_read = recv(state->peer_one_fd, buffer, sizeof(buffer), 0);
      if(bytes_read <=0){
        //Peer one disconnected
        close(state->peer_one_fd);
        state->peer_one_fd =-1;
      } else {
        my_write(state->peer_two_fd, buffer, bytes_read);
      }
    }
    if(FD_ISSET(state->peer_two_fd, &read_fds)){
      ssize_t bytes_read = recv(state->peer_two_fd, buffer, sizeof(buffer), 0);
      if(bytes_read <=0){
        //Peer two disconnected
        close(state->peer_two_fd);
        state->peer_two_fd =-1;
      } else {
        my_write(state->peer_one_fd, buffer, bytes_read);
      }
    }

    if(state->peer_one_fd ==-1 && state->peer_two_fd ==-1){
      break;
    }

  }

    /* Unreachable */
    return 0;
}

/* The start of the program */
int main(int argc, char **argv) {
  uint16_t port;
  state_t state;

  /* Get TCP port number to listen at */
  if (argc < 2) {
    fprintf(stderr, "Usage: chat <TCP port>\n");
    return 1;
  }
  if (parse_port(&port, argv[1]) < 0) {
    fprintf(stderr, "Usage: chat <TCP port>\n");
    return 1;
  }

  /* Initialize the state */
  state.listening_fd = -1;
  state.peer_one_fd = -1;
  state.peer_two_fd = -1;
  
  /* Open the listening fd */
  state.listening_fd = open_listening_fd(port);
  if (state.listening_fd < 0) {
    fprintf(stderr, "Could not initialize the chat server on port %llu\n",
            ((unsigned long long int) port));
    return 1;    
  }

  /* Run the server */
  if (run_chat_server(&state) < 0) {
    fprintf(stderr, "An error occurred. Shutting down the server.\n");
    if (state.peer_one_fd >= 0) {
      if (close(state.peer_one_fd) < 0) {
        fprintf(stderr, "Could not close a file descriptor: %s\n",
                strerror(errno));
      } else {
        state.peer_one_fd = -1;
      }
    }
    if (state.peer_two_fd >= 0) {
      if (close(state.peer_two_fd) < 0) {
        fprintf(stderr, "Could not close a file descriptor: %s\n",
                strerror(errno));
      } else {
        state.peer_two_fd = -1;
      }
    }
    if (state.listening_fd >= 0) {
      if (close(state.listening_fd) < 0) {
        fprintf(stderr, "Could not close a file descriptor: %s\n",
                strerror(errno));
      } else {
        state.listening_fd = -1;
      }
    }
    return 1;
  }

  /* Close the ports that are still open */
  if (state.peer_one_fd >= 0) {
    if (close(state.peer_one_fd) < 0) {
      fprintf(stderr, "Could not close a file descriptor: %s\n",
              strerror(errno));
      if (state.peer_two_fd >= 0) {
        if (close(state.peer_two_fd) < 0) {
          fprintf(stderr, "Could not close a file descriptor: %s\n",
                  strerror(errno));
        } else {
          state.peer_two_fd = -1;
        }
      }
      if (state.listening_fd >= 0) {
        if (close(state.listening_fd) < 0) {
          fprintf(stderr, "Could not close a file descriptor: %s\n",
                  strerror(errno));
        } else {
          state.listening_fd = -1;
        }
      }
      return 1;
    } else {
      state.peer_one_fd = -1;
    }
  }
  if (state.peer_two_fd >= 0) {
    if (close(state.peer_two_fd) < 0) {
      fprintf(stderr, "Could not close a file descriptor: %s\n",
              strerror(errno));
      if (state.listening_fd >= 0) {
        if (close(state.listening_fd) < 0) {
          fprintf(stderr, "Could not close a file descriptor: %s\n",
                  strerror(errno));
        } else {
          state.listening_fd = -1;
        }
      }
      return 1;
    } else {
      state.peer_two_fd = -1;
    }
  }
  if (state.listening_fd >= 0) {
    if (close(state.listening_fd) < 0) {
      fprintf(stderr, "Could not close a file descriptor: %s\n",
              strerror(errno));
    } else {
      state.listening_fd = -1;
    }
    return 1;
  }
 
  /* Signal success */
  return 0;
}
