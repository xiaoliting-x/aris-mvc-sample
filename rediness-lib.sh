#!/bin/sh

source ./setenvs.sh

mvn install:install-file -Dfile=lib-local/aris-encrypt-tool.jar -DgroupId=com.accenture.aris -DartifactId=aris-encrypt-tool -Dversion=1.0  -Dpackaging=jar -DgeneratePom=true
mvn install:install-file -Dfile=lib-local/jconn4.jar -DgroupId=com.sybase.jdbc4.jdbc -DartifactId=jconn4 -Dversion=26502  -Dpackaging=jar -DgeneratePom=true
mvn install:install-file -Dfile=lib-local/ojdbc6.jar -DgroupId=com.oracle -DartifactId=ojdbc6 -Dversion=11.2.0 -Dpackaging=jar -DgeneratePom=true
mvn install:install-file -Dfile=lib-local/jdom-2.0.5-contrib.jar -DgroupId=org.jdom -DartifactId=jdom2-contrib -Dversion=2.0.5  -Dpackaging=jar -DgeneratePom=true
mvn install:install-file -Dfile=lib-local/aris-dbunit-2.4.9.jar -DgroupId=org.dbunit -DartifactId=aris-dbunit -Dversion=2.4.9  -Dpackaging=jar -DgeneratePom=true -DpomFile=lib-local/aris-dbunit/pom.xml
mvn dependency:tree

