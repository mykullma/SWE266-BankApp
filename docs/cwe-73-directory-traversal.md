# CWE-73: External Control of File Name or Path

Bank App uses untrusted data while constructing file paths.
This leaves the application vulnerable to malicious users uploading
their own files or exfiltrating files from the system. This may allow
for code execution or be used as a step to further system compromise.

## Exploit

1. Go to http://127.0.0.1:8080/register and register with username of '..'
2. Go to http://localhost:8080/profile/README.md
3. Observe that the README.md file is downloaded

## Resources

- [CWE 73](https://cwe.mitre.org/data/definitions/73.html)
- [Wikipedia: Directory traversal attack](https://en.wikipedia.org/wiki/Directory_traversal_attack)
- [OWASP: Path travsersal](https://www.owasp.org/index.php/Path_Traversal)
