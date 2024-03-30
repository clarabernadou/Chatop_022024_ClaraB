# Getting Started

## Installing the Application in Your Code Editor

To integrate the Chatop_022024_ClaraB application with your code editor, follow these steps:

### Using IntelliJ:

1. Open the `Chatop_022024_ClaraB` file in your IntelliJ code editor.

2. Navigate to "Current File."

3. Select "Edit Configurations..."

4. Click on "Add new run configuration..."

5. Choose "Maven" from the options.

6. In the "Run" section, under "Command line," specify `spring-boot:run`.

7. Run the project by clicking on the "Run" button.

### Using VSCode:

1. Open any file located in the `src/main/java/com/chatop/SpringSecurityAuth/...` folder within VSCode.

2. Locate the arrow icon at the top right corner of the files bar and click on it.

3. From the dropdown menu, select "Run Java."

4. Choose `com.chatop.SpringSecurityAuthApplication` from the options.

Following these instructions will successfully set up and run the Chatop_022024_ClaraB application in your preferred code editor.

---

## Database Schema

-- Table: users
CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(255) UNIQUE,
    name VARCHAR(255),
    password VARCHAR(255),
    created_at DATE DEFAULT CURRENT_DATE,
    updated_at DATE DEFAULT CURRENT_DATE ON UPDATE CURRENT_DATE
);

-- Table: rentals
CREATE TABLE rentals (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    surface INT,
    price INT,
    description VARCHAR(500),
    pictureURL VARCHAR(255),
    owner_id INT,
    created_at DATE DEFAULT CURRENT_DATE,
    updated_at DATE DEFAULT CURRENT_DATE ON UPDATE CURRENT_DATE,
    FOREIGN KEY (owner_id) REFERENCES users(id)
);

-- Table: messages
CREATE TABLE messages (
    id INT PRIMARY KEY AUTO_INCREMENT,
    message TEXT,
    user_id INT,
    rental_id INT,
    created_at DATE DEFAULT CURRENT_DATE,
    updated_at DATE DEFAULT CURRENT_DATE ON UPDATE CURRENT_DATE,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (rental_id) REFERENCES rentals(id)
);
