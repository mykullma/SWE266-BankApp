# CWE-501: Trust Boundary Violation

Bank App identifies a logged in user by the username in the session.
It also puts the username in the session during registration.
I wonder what would happen if registration is never completed...

## Exploit

1. Go to http://127.0.0.1:8080/register
2. Try to register for '1'
3. Note the error saying the user already exists
4. Now go to http://127.0.0.1:8080/login
5. Note that you are logged in as user '1'
