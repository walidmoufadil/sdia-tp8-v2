## Class: `Server`

### Description
The `Server` class implements a multi-threaded file server that listens for client connections on port `1324`. Upon connection, the server allows clients to request files by name. If the file exists in the server's directory, its contents are sent to the client line by line. If the file is not found, the server responds with an error message.

### Features
- **Multi-threaded**: Handles multiple client connections simultaneously by creating a new thread for each client.
- **File Sharing**: Serves files stored in a predefined directory.
- **Error Handling**: Provides error messages if a requested file is not found or another issue occurs.
- **End-of-File Marker**: Sends an "EOF" message to the client to indicate the end of the file transfer.

---

### Implementation Details

#### 1. **Class Structure**
- **Fields**:
    - `SERVER_FILES_PATH`: The path to the directory where files are stored (`src/main/java/com/javaIntellij/Files`).
    - `clientSocket`: The socket connection to the client.

- **Constructor**:
    - Accepts a `Socket` object representing the client connection.

- **Main Method**:
    - Creates a `ServerSocket` listening on port `1324`.
    - Accepts incoming client connections.
    - Spawns a new thread for each client using the `Runnable` interface.

- **`run` Method**:
    - Handles communication with the connected client:
        1. Prompts the client for a file name.
        2. Checks if the file exists in the specified directory.
        3. If the file exists:
            - Sends its content line by line.
            - Appends "EOF" to signal the end of the file.
        4. If the file does not exist, sends an error message.
    - Closes the client socket after communication.

---

### Communication Protocol
1. **Client Requests**:
    - Client sends the name of a file it wants to retrieve.
2. **Server Responses**:
    - **File Found**: Sends "Fichier valide..." followed by the file's content, ending with "EOF".
    - **File Not Found**: Sends "Erreur: Fichier non trouvé".

---

### Example Usage

1. **Server Startup**:
    - Start the `Server` application. It listens on port `1324` and waits for client connections.

2. **Client Interaction**:
    - The client connects to the server and sends the name of a file.
    - The server checks for the file and sends its content or an error message.

---

### Example Directory Structure
```
src/main/java/com/javaIntellij/Files/
    ├── example.txt
    ├── data.txt
    └── notes.txt
```
- Ensure the directory contains the files the server can serve.

---

### Notes
- **Port Number**: The server listens on port `1324`. Update this if necessary.
- **Concurrency**: Each client request is handled in a separate thread to support multiple clients simultaneously.
- **Error Messages**:
    - If the file is not found, the server sends: `"Erreur: Fichier non trouvé"`.
    - If the client disconnects abruptly, the server logs the error but continues running.
- **Security Consideration**: The server assumes a safe file path structure. To prevent directory traversal attacks (e.g., `../`), sanitize client input.

---

### Potential Enhancements
- **Input Validation**: Validate client input to avoid invalid or malicious file requests.
- **Logging**: Add logging for better monitoring and debugging.
- **Configuration**: Make `SERVER_FILES_PATH` and `port` configurable through external properties or environment variables.
- **File Upload**: Extend functionality to allow clients to upload files to the server.

---

## Class: `Client`

### Description
The `Client` class is a simple file request client for connecting to a server running on `localhost` at port `1324`. It allows a user to request the contents of a file stored on the server by entering the file name. The server responds with the file content if available or an error message if the file is not found.

---

### Features
- **Connects to the Server**: Establishes a TCP connection to the server on `localhost:1324`.
- **File Request**: Prompts the user to input a file name to retrieve from the server.
- **Server Communication**: Exchanges messages with the server using input and output streams.
- **Displays File Contents**: Prints the file content line by line as received from the server.
- **Handles End-of-File Signal**: Stops reading from the server when the `"EOF"` message is received.

---

### Implementation Details

#### 1. **Method**
- **`run` Method**:
    - Establishes a socket connection to the server.
    - Reads the server's initial prompt.
    - Accepts user input (file name) from the console.
    - Sends the file name to the server.
    - Reads and prints the server's response:
        - Prints the file content if the server finds the file.
        - Stops reading when `"EOF"` is received.
        - Prints error messages if the server indicates the file is not found.
    - Handles exceptions gracefully if an error occurs during communication.

#### 2. **Communication Flow**
1. **Server Prompt**: The server prompts for a file name.
2. **User Input**: The user enters the file name.
3. **File Request**: The client sends the file name to the server.
4. **Server Response**:
    - If the file exists, the server sends its content line by line, followed by `"EOF"`.
    - If the file does not exist, the server sends an error message (e.g., `"Erreur: Fichier non trouvé"`).
5. **Display Output**: The client prints the server's response.

---

### Example Usage

1. **Start the Server**:
    - Ensure the `Server` program is running and listening on port `1324`.

2. **Run the Client**:
    - Start the `Client` program by calling `Client.run()`.
    - Follow the instructions:
        - Enter the file name when prompted.
        - View the file content or an error message.

3. **Example Interaction**:
   ```
   Entrer le nom du fichier :
   example.txt
   Fichier valide...
   Line 1: File content...
   Line 2: File content...
   EOF
   ```

---

### Notes
- **Server Dependency**: The client assumes a server is running on `localhost:1324`. Update the address and port if necessary.
- **Input Validation**: The current implementation does not validate the user's input (e.g., checking for null or empty strings). Adding validation would improve robustness.
- **Error Handling**:
    - Gracefully handles connection errors, server unavailability, and unexpected server responses.
    - Logs an error message if an exception occurs.

---

### Potential Enhancements
- **User-Friendly Output**: Format the output more clearly for better readability.
- **File Saving**: Allow the client to save the received file content locally.
- **Configurable Host and Port**: Enable dynamic configuration of the server's address and port through arguments or environment variables.
- **Command-Line Interface**: Implement a command-line interface (CLI) to improve user interaction.

---

## Class: `Client1`

### Description
The `Client1` class serves as an entry point for running the file request client functionality defined in the `Client` class. It invokes the `run` method of `Client` to start the client-side application, enabling communication with the server to request and retrieve file contents.

---

### Features
- **Simplified Entry Point**: Delegates the functionality to the `Client.run()` method, encapsulating the details of client operations.
- **Ease of Use**: Provides a single point of execution for starting the client program.

---

### Implementation Details
- **`main` Method**:
    - Calls the static `run` method of the `Client` class.
    - Starts the client process to connect to the server, request files, and handle server responses.

---

### Example Usage
1. Ensure the `Server` is running on `localhost` at port `1324`.
2. Run the `Client1` program:
    - This will execute the `Client.run()` method.
    - Follow the prompts to enter a file name and view the server's response.

---

### Notes
- **Delegation**: This class does not contain any logic itself; it solely delegates functionality to the `Client` class.
- **Minimal Responsibility**: Designed to keep the main method lightweight and focused.

---

## Class: `Client2`

### Description
The `Client2` class serves as an alternate entry point for running the client-side application defined in the `Client` class. It invokes the `Client.run()` method, providing the same functionality as `Client1`.

---

### Features
- **Alternative Entry Point**: Functions as another entry point to start the client program, identical in functionality to `Client1`.
- **Ease of Execution**: Simplifies launching the client program by delegating the core logic to the `Client` class.

---

### Implementation Details
- **`main` Method**:
    - Calls the static `run` method of the `Client` class.
    - Initiates the client-side process to connect to the server, request files, and handle server responses.

---

### Example Usage
1. Start the server using the `Server` program on `localhost` at port `1324`.
2. Run the `Client2` program:
    - This will invoke the `Client.run()` method.
    - Follow the on-screen prompts to request a file and view the server's response.

---

### Notes
- **Duplicate Functionality**: The `Client2` class provides the same functionality as `Client1`. Having both classes may be useful for testing or for scenarios where different configurations or extensions are planned.
- **Delegation Pattern**: Like `Client1`, this class delegates all functionality to the `Client` class, maintaining minimal responsibility.
