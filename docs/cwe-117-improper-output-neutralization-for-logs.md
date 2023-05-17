# CWE-117: Improper Output Neutralization for Logs

Bank App has a lot of logging statements, unfortunately it uses untrusted data
in the logging statements. This may allow an attacker to inject their own
"Enters" (Carriage Return & Line Feed or just Line Feed) and thereby inject
their own log lines.

While this may seem innocuos consequences of this may be that auditors can not
trust log files in case of an incident or attackers may attempt to inject
HTML and trick an administrator into executing it and causing XSS on
an administrator part of the application.

## Exploit

1. Go to http://127.0.0.1:8080/%0D%0A2023-05-16T16:55:18.354-07:00%20%20INFO%202256%20---%20This%20has%20been%20injected
2. Observe that "INFO This has been injected" has been added to the logs.

## Resources

- [CWE-113](https://cwe.mitre.org/data/definitions/113.html)
