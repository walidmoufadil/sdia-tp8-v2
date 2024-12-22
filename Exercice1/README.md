## Class: `Server`

### Description
The `Server` class is a simple implementation of a guessing game server that listens for client connections on a specific port. It generates a random number between 1 and 100 and interacts with a client over a socket connection to guide the client in guessing the number. Feedback is provided to the client about whether their guess is too high, too low, or correct.

### Features
- Listens for incoming client connections on port 1234.
- Generates a random number between 1 and 100 for the guessing game.
- Exchanges messages with the client using input/output streams:
    - Sends feedback (`1`, `-1`, or `0`) based on the client's guess.
    - Receives guesses from the client.
- Closes the server socket after the client guesses the number correctly.

---

### Implementation Details

#### Methods and Functionalities
1. **`main` Method**:
    - Initializes a `ServerSocket` on port 1234.
    - Waits for a client to connect using `socket.accept()`.
    - Generates a random number between 1 and 100.
    - Reads guesses from the client's input stream and sends feedback:
        - `1`: Guess is too high.
        - `-1`: Guess is too low.
        - `0`: Guess is correct.
    - Closes the `ServerSocket` after the guessing game ends.

2. **Communication Protocol**:
    - Client sends an integer guess to the server via the input stream.
    - Server evaluates the guess:
        - If the guess is greater than the random number, the server sends `1`.
        - If the guess is less than the random number, the server sends `-1`.
        - If the guess matches the random number, the server sends `0` to indicate the game is over.

3. **Random Number Generation**:
    - The random number is generated using the formula:  
      `int number = (int) (Math.random() * 100 + 1);`  
      Ensuring the number is in the range `[1, 100]`.

4. **Socket Communication**:
    - The server communicates with the client via `Socket.getInputStream()` and `Socket.getOutputStream()`.

---

### Example Usage

1. Start the server by running the `Server` class. It will listen on port 1234.
2. A client program connects to the server and starts guessing the number.
3. The server provides feedback for each guess until the correct number is guessed.
4. After the correct guess, the server sends `0` and terminates the connection.

---

### Notes
- **Port Number**: The server listens on port 1234, which can be changed if needed.
- **Blocking Call**: `socket.accept()` blocks the execution until a client connects.
- **Error Handling**: The implementation does not currently handle exceptions (e.g., `IOException` during communication), which can be added for robustness.

---
## Class: `Client`

### Description
The `Client` class is a simple client implementation for a number guessing game. It connects to a server running on `localhost` at port `1234`, sends guesses for a random number, and receives feedback from the server to adjust its guesses. The game continues until the client guesses the correct number.

---

### Features
- Connects to the server on `localhost:1234` using a TCP socket.
- Allows the user to input guesses via the console.
- Sends the guessed number to the server.
- Receives feedback from the server:
    - `1`: Guess is too high.
    - `-1`: Guess is too low.
    - `0`: Guess is correct.
- Displays feedback messages to the user and prompts for new guesses as needed.
- Ends the game when the correct number is guessed, displaying a congratulatory message.

---

### Implementation Details

#### Methods and Functionalities
1. **`main` Method**:
    - Establishes a connection to the server using a `Socket`.
    - Uses a `Scanner` to read user input from the console.
    - Interacts with the server:
        - Sends the guessed number using the socket's output stream.
        - Reads feedback from the server using the socket's input stream.
        - Displays appropriate messages to the user based on the server's feedback:
            - "plus grand ..." if the guess is too high.
            - "plus petit ..." if the guess is too low.
    - Ends when the server responds with `0`, indicating the correct guess.

2. **User Interaction**:
    - Prompts the user to guess a number between 0 and 100.
    - Continuously adjusts the guess based on feedback until the correct number is found.

3. **Socket Communication**:
    - Sends data to the server using `Socket.getOutputStream()`.
    - Reads server responses using `Socket.getInputStream()`.

4. **Feedback Messages**:
    - `"plus grand ..."`: Indicates the guessed number is greater than the target.
    - `"plus petit ..."`: Indicates the guessed number is less than the target.
    - `"Felicitation !"`: Congratulates the user for guessing correctly.

---

### Example Usage

1. Start the server first (using the `Server` class).
2. Run the `Client` program.
3. Follow the console prompts to guess a number between 0 and 100.
4. Receive feedback and adjust guesses until the number is correct.
5. Once the correct number is guessed, the client will display `"Felicitation !"` and terminate.

---

### Notes
- **Connection**: The client assumes the server is running on `localhost` and listening on port `1234`. Update the IP address and port if needed for a different setup.
- **Input Validation**: The implementation does not validate user input (e.g., ensuring it is an integer between 0 and 100). Additional checks could be added for robustness.
- **Error Handling**: The current implementation does not handle exceptions (e.g., if the server is not running), which can be added for a better user experience.
