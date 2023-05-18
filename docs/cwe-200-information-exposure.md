# CWE-200: Information Exposure in BankApp

Bank App has not been properly configured for production use, as such it has
features that are helpful for debugging but also very helpful for attackers.

## Exploit

1. Log in as a user
2. Go to http://localhost:8080/profile/somefile
3. Observe that the stack trace is shown
