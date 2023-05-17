# CWE-601: URL Redirection to Untrusted Site ('Open Redirect')

Bank App uses untrusted data while building URL redirects.
This leaves the application vulnerable to malicious users controlling
the destination website served when navigating through VeraDemo.

## Exploit

1. Go to http://127.0.0.1:8080/login?target=http://google.com
2. Login, if the user is not already
3. Observe the Google homepage is loaded

## Resources

- [CWE 601](https://cwe.mitre.org/data/definitions/601.html)
- [OWASP Cheat Sheet](https://www.owasp.org/index.php/Unvalidated_Redirects_and_Forwards_Cheat_Sheet)
