@echo off

set JAVA_HOME=C:\Program Files (x86)\Java\jdk1.7.0_55
set M2_HOME=C:\1. Java_Rong, Chengqi\6-Tools\apache-maven-3.2.1-bin\apache-maven-3.2.1
set PATH=.\;C:\Windows\System32;%JAVA_HOME%\bin;%M2_HOME%\bin

set date_tmp=%date:/=%
set time_tmp=%time: =0%
set yyyy=%date_tmp:~0,4%
set yy=%date_tmp:~2,2%
set mm=%date_tmp:~4,2%
set dd=%date_tmp:~6,2%
set hh=%time_tmp:~0,2%
set mi=%time_tmp:~3,2%
set ss=%time_tmp:~6,2%
set sss=%time_tmp:~9,2%
set datetime=%yyyy%%mm%%dd%%hh%%mi%%ss%%sss%
set time_tmp=
set date_tmp=
set yyyy=
set yy=
set mm=
set dd=
set hh=
set mi=
set ss=
set sss=

@echo on
@echo ... setup complete.