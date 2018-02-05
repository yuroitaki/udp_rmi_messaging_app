@echo off

echo Computer Networks and Distributed Systems Coursework - Install Utility
echo (c) Daniele Sgandurra, Imperial College London, Mar 2016
echo.

del  rmiclient.sh rmiserver.sh udpclient.sh udpserver.sh Makefile
xcopy /Y scripts\*.bat .

echo.
echo Done!
echo.
