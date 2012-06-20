call mvn install:install-file -Dfile=lib\trsdev4_infra_jdk16.jar -Dsources=lib\trsdev4_infra_jdk16_src.zip -DgroupId=com.trs.dev4 -DartifactId=infra-jdk16 -Dversion=1.0 -Dpackaging=jar
if not "%ERRORLEVEL%" == "0" exit /b

call mvn install:install-file -Dfile=lib\trsbean.jar -DgroupId=com.trs -DartifactId=trsbean -Dversion=1.0 -Dpackaging=jar
if not "%ERRORLEVEL%" == "0" exit /b
