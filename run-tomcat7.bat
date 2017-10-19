@echo off
call setenvs.bat

set TARGET_DIST=%1
if not "%TARGET_DIST%" == "" goto runTomcat
set TARGET_DIST=local

:runTomcat
@echo on
start mvn tomcat7:run -P %TARGET_DIST%
