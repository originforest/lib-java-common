# Java编译

先编译 AnnotationProcessor 后, 再进行打包:

javac ./IdentityProcessor.java ../Identity.java

# Identity C 模块生成.

先生成c头文件
```shell
$ javac -cp ~/.m2/repository/org/scijava/native-lib-loader/2.3.5/native-lib-loader-2.3.5.jar:src/main/java -d target/classes -h src/main/java/com/originforest/common/utils/identity src/main/java/com/originforest/common/utils/identity/IdentityGenerator.java
```

### MacOS

```shell
$ gcc -I"$JAVA_HOME/include" -I"$JAVA_HOME/include/darwin" -dynamiclib -o src/main/resources/natives/osx_64/libidentify.dylib src/main/java/com/originforest/common/utils/identity/identity.c
```

### Linux

```shell
$ gcc -fPIC -I"$JAVA_HOME/include" -I"$JAVA_HOME/include/linux" -shared -o src/main/resources/natives/linux_64/libidentify.so src/main/java/com/originforest/common/utils/identity/identity.c
```

### Docker环境构建

#### Linux 64

```shell
$ docker run --rm -d --name identity_build dockcross/linux-x64 tail -f /dev/null
$ docker cp ~/Downloads/OpenJDK8U-jdk_x64_linux_hotspot_8u282b08.tar.gz identity_build:/work
$ docker exec -it identity_build bash -c 'tar zxf OpenJDK8U-jdk_x64_linux_hotspot_8u282b08.tar.gz && ls'
$ docker cp src/main/java/com/originforest/common/utils/identity/. identity_build:/work
$ docker exec -it identity_build bash -c 'export JAVA_HOME=/work/jdk8u282-b08/ && $CC -fPIC -I"$JAVA_HOME/include" -I"$JAVA_HOME/include/linux" -shared -o libidentify.so identity.c'
$ docker cp identity_build:/work/libidentify.so src/main/resources/natives/linux_64/
$ docker stop identity_build
```

测试

```shell
$ docker run --rm -it -v ~/.m2/repository:/work/repository -v $(pwd)/target:/work/target java:8 java -cp /work/target/bp-common-0.0.4-SNAPSHOT.jar:/work/repository/org/scijava/native-lib-loader/2.3.5/native-lib-loader-2.3.5.jar:/work/repository/org/slf4j/slf4j-api/1.7.30/slf4j-api-1.7.30.jar:/work/repository/ch/qos/logback/logback-classic/1.2.3/logback-classic-1.2.3.jar:/work/repository/ch/qos/logback/logback-core/1.2.3/logback-core-1.2.3.jar IdentityGenerator
```

#### Windows x86-32

```shell
$ docker run --rm -d --name identity_build dockcross/windows-static-x86 tail -f /dev/null
$ docker cp ~/Downloads/OpenJDK8U-jdk_x86-32_windows_hotspot_8u282b08.zip identity_build:/work
$ docker exec -it identity_build bash -c 'unzip OpenJDK8U-jdk_x86-32_windows_hotspot_8u282b08.zip && ls'
$ docker cp src/main/java/com/originforest/common/utils/identity/. identity_build:/work
$ docker exec -it identity_build bash -c 'export JAVA_HOME=/work/jdk8u282-b08/ && $CC -Wl,--add-stdcall-alias -I"$JAVA_HOME/include" -I"$JAVA_HOME/include/win32" -shared -o identity.dll identity.c'
$ docker cp identity_build:/work/identity.dll src/main/resources/natives/windows_32/
$ docker stop identity_build
```

#### Windows x64

```shell
$ docker run --rm -d --name identity_build dockcross/windows-static-x64 tail -f /dev/null
$ docker cp ~/Downloads/OpenJDK8U-jdk_x64_windows_hotspot_8u282b08.zip identity_build:/work
$ docker exec -it identity_build bash -c 'unzip OpenJDK8U-jdk_x64_windows_hotspot_8u282b08.zip && ls'
$ docker cp src/main/java/com/originforest/common/utils/identity/. identity_build:/work
$ docker exec -it identity_build bash -c 'export JAVA_HOME=/work/jdk8u282-b08/ && $CC -I"$JAVA_HOME/include" -I"$JAVA_HOME/include/win32" -shared -o identity.dll identity.c'
$ docker cp identity_build:/work/identity.dll src/main/resources/natives/windows_64/
$ docker stop identity_build
```

测试

```
> java -cp Z:\.m2\repository\com\originforest\bp-common\0.0.4-SNAPSHOT\bp-common-0.0.4-SNAPSHOT.jar;Z:\.m2\repository\org\scijava\native-lib-loader\2.3.5\native-lib-loader-2.3.5.jar;Z:\.m2\repository\org\slf4j\slf4j-api\1.7.30\slf4j-api-1.7.30.jar;Z:\.m2\repository\ch\qos\logback\logback-classic\1.2.3\logback-classic-1.2.3.jar;Z:\.m2\repository\ch\qos\logback\logback-core\1.2.3\logback-core-1.2.3.jar IdentityGenerator
```
