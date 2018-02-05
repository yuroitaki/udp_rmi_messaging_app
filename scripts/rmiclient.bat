@echo off
set SECPOLICY="file:./policy"
java -cp .;rmi;rmi-common -Djava.security.policy=%SECPOLICY% rmi.RMIClient %*
