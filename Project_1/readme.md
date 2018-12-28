# Project 1 - Full-Stack Java Employee Reimbursement Application
This project is a CRUD application for requesting, viewing, approving, and denying financial reimbursements. Within this project, there are two separate user roles: manager and employee. The project uses SQL and JDBC for data persistence uses the DAO design pattern extensively.

Employees can request to be reimbursed for their expenses by submitting a request to a manager. They are able to view all requests that they've made: pending, approved, and denied. Additionally, employees can view their own profile and edit their profile information (e.g., name).

Managers can approve, deny, and view all reimbursement requests based on status and employee name.

Both employees and managers have their own separate views based on account type. These sessions are managed via the use of the Java Servlet HttpSession interface in conjunction with AJAX to display the appropriate data. This is aided by the use of Java Servlets that display specific views based on session state, user role, and endpoint accessed.

Technologies used include: Java, Servlets, JDBC, log4j, JUnit.
