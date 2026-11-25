# Student Management System

A Java console application for practising JDBC and basic database operations. Student details are stored in MySQL, and the menu lets a user add, view, search, update and delete records.

## Features

- Add a student with an ID, name, email, course and CGPA.
- View records and sort them by ID, name or CGPA.
- Search using a name, email address or course.
- Update or delete a record using its ID.
- Use `PreparedStatement` for values passed into SQL queries.
- Check basic input such as empty names and CGPA values outside 0–10.

## Built with

Java 17, JDBC, MySQL 8 and Maven.

## Run it locally

1. Install JDK 17+, Maven and MySQL 8+.
2. Set `DB_USERNAME` and `DB_PASSWORD` for your local MySQL account. The code currently falls back to `root` / `root`; change that for your own machine rather than using those values as real credentials.
3. Optionally set `DB_URL` if your MySQL host, port or database name is different.
4. The application creates the database and table at startup if the MySQL user has permission. You can also run `database/schema.sql` first.
5. Open a terminal in this folder and run `mvn compile exec:java`.
6. For this local demo, the login is `admin` with password `admin123`.

## A few details worth knowing

`Student` holds the student data. `StudentDAO` contains the SQL operations, `DBConnection` opens database connections, and `Main` handles the console menu and input. Keeping these jobs in separate classes makes the code easier to follow and change.

## Current limitations

The login is only a demonstration; credentials are hard-coded and the password is not securely stored. Do not deploy this as-is or reuse the demo password. A real application needs password hashing, proper authentication and authorisation, stronger validation and tests.

## Possible next steps

- Add pagination when there are many records.
- Validate email format and prevent duplicate IDs with clearer messages.
- Add automated tests for the DAO and input-handling logic.
- Move database settings into a local, untracked configuration file.
