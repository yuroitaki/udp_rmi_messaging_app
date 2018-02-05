@echo off
set SECPOLICY="file:./policy"
java -cp .;rmi;common -Djava.security.policy=%SECPOLICY% rmi.RMIServer

