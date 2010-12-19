@echo off
title Compiling...
cd Source
"C:\Program Files\Java\jdk6\bin\javac.exe" -cp . -d ./Bin/ ./*java
pause