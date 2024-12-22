## **General Introduction to the Exercises**

These exercises focus on fundamental concepts of network programming in Java, particularly the use of **sockets** for client-server communication and multi-threading to handle multiple client connections. Each exercise builds a modular system, separating the responsibilities of the components (server and clients) while showcasing specific functionalities.

---

### **Exercise 1: Basic Client-Server Communication**
#### **Objective**
This exercise implements basic communication between a server and a client. The goal is to create a simple interaction where the server generates a random number, and the client attempts to guess it based on hints provided by the server.

#### **Main Modules**
1. **`Server`**:
    - Listens on a specific port (1234) and waits for client connections.
    - Generates a random number between 1 and 100.
    - Provides hints to the client (higher or lower) to help them guess the number.
    - Ends the session when the client guesses the number correctly.

2. **`Client`**:
    - Connects to the server using `localhost` and port 1234.
    - Allows the user to input guesses via the console.
    - Receives hints from the server and displays them to guide the user.
    - Terminates when it receives a success signal.

#### **Key Learnings**
- Introduction to sockets (`ServerSocket`, `Socket`).
- Bidirectional communication (sending/receiving data between the server and client).
- Working with streams (`InputStream`, `OutputStream`).

---

### **Exercise 2: Multi-Client Server and File Handling**
#### **Objective**
This exercise builds on the first by introducing a multi-threaded server capable of handling multiple clients simultaneously. Each client can request a specific file, which the server sends line by line if it exists.

#### **Main Modules**
1. **`Server`**:
    - Listens on a specific port (1324) and accepts multiple client connections using threads.
    - Each client connection is handled in a separate thread, enabling simultaneous processing.
    - Allows clients to request a file by name. If the file exists in the server's directory, it sends the content line by line. Otherwise, it returns an error message.

2. **`Client`**:
    - Connects to the server and allows the user to enter a file name via the console.
    - Sends the file name to the server and waits for the response.
    - Displays the file's content if it exists or shows an error message if the file is not found.

3. **`Client1` and `Client2`**:
    - Additional entry points that directly call the `Client.run()` method.
    - Provide flexibility to execute the same client functionality from different contexts or configurations.

#### **Key Learnings**
- Creation and management of multi-threaded servers.
- Client-server communication with file handling.
- Advanced use of streams (`BufferedReader`, `PrintWriter`) for reading and writing.
- Transmitting data as strings between the client and server.

---

### **Summary of Differences Between the Two Exercises**

| Aspect                        | Exercise 1                                | Exercise 2                               |
|-------------------------------|-------------------------------------------|------------------------------------------|
| **Main Functionality**        | Number guessing game.                     | Multi-client file server.                |
| **Server**                    | Single-client, single loop.               | Multi-client, thread-based handling.     |
| **Clients**                   | Single client.                            | Multiple clients with additional entry points. |
| **Complexity**                | Basic.                                    | Intermediate, with file management.      |

---

### **Conclusion**
These exercises progress from basic network programming concepts to more advanced applications, illustrating essential topics such as thread management, file transmission, and bidirectional communication between servers and clients.