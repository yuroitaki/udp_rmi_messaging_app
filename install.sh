#!/bin/bash

echo Computer Networks and Distributed Systems Coursework - Install Utility
echo \(c\) Daniele Sgandurra, Imperial College London, Mar 2016

rm -f rmiclient.bat rmiserver.bat udpclient.bat udpserver.bat build.bat 
cp scripts/Makefile .
cp scripts/*.sh .
fromdos policy
for i in *.sh; do
    fromdos $i
done
chmod u+x *.sh

echo Done!
