
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.bpfaas/bp-common/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.bpfaas/bp-common/)
[![License](https://img.shields.io/github/license/bpfaas/java-bp-common)](https://opensource.org/licenses/MIT)


```html
<dependency>
    <groupId>com.bpfaas</groupId>
    <artifactId>bp-common</artifactId>
    <version>0.0.3</version>
</dependency>
```

包含如下内容:

- 约定返回对象格式
- 常用异常
  - BpErrSubcodeException  可使用此异常来传递一个业务相关的err_subCode; 在外层统一处理;
  - BpRawMsgException      可在controller处理中抛出此异常, 错误处理时将会自动返回包含指定httpStatusCode等信息 http的raw形式.
- 常用工具
  - JsonUtils     json序列化反序列化工具.
  - CommandUtils  命令执行工具
  - CryptUtils    加密算法等工具
- 注解
  - @ValidatorId 验证请求参数id的合法性.
  
## 分布式ID编译

使用C语言版本实现ID生成，代码文件`src/main/resources/identity/identity.c`由`Fiathux Su`提供

使用示例

```java
import com.bpfaas.common.utils.identify.IdentityGenerator;

/**
 * @author rjf
 * @since 2021/1/26
 */
public class IdentityGeneratorDemo {

  public static void main(String[] args) {
    for (int i = 0; i < 5; i++) {
      System.out.println(IdentityGenerator.nextId());
    }
  }

}

```

先生成c头文件
```shell
$ javac -cp ~/.m2/repository/org/scijava/native-lib-loader/2.3.5/native-lib-loader-2.3.5.jar:src/main/java -d target/classes -h src/main/java/com/bpfaas/common/utils/identify src/main/java/com/bpfaas/common/utils/identify/IdentityGenerator.java
```

### MacOS

```shell
$ gcc -I"$JAVA_HOME/include" -I"$JAVA_HOME/include/darwin" -dynamiclib -o src/main/resources/natives/osx_64/libidentify.dylib src/main/java/com/bpfaas/common/utils/identify/identify.c
```

### Linux

```shell
$ gcc -fPIC -I"$JAVA_HOME/include" -I"$JAVA_HOME/include/linux" -shared -o src/main/resources/natives/linux_64/libidentify.so src/main/java/com/bpfaas/common/utils/identify/identify.c
```

### Docker环境构建

#### Linux 64

```shell
$ docker run --rm -d --name identity_build dockcross/linux-x64 tail -f /dev/null
$ docker cp ~/Downloads/OpenJDK8U-jdk_x64_linux_hotspot_8u282b08.tar.gz identity_build:/work
$ docker exec -it identity_build bash -c 'tar zxf OpenJDK8U-jdk_x64_linux_hotspot_8u282b08.tar.gz && ls'
$ docker cp src/main/java/com/bpfaas/common/utils/identify/. identity_build:/work
$ docker exec -it identity_build bash -c 'export JAVA_HOME=/work/jdk8u282-b08/ && $CC -fPIC -I"$JAVA_HOME/include" -I"$JAVA_HOME/include/linux" -shared -o libidentify.so identify.c'
$ docker cp identity_build:/work/libidentify.so src/main/resources/natives/linux_64/
$ docker stop identity_build
```

测试

```shell
$ docker run --rm -it -v ~/.m2/repository:/work/repository -v $(pwd)/target:/work/target java:8 java -cp /work/target/bp-common-0.0.4-SNAPSHOT.jar:/work/repository/org/scijava/native-lib-loader/2.3.5/native-lib-loader-2.3.5.jar:/work/repository/org/slf4j/slf4j-api/1.7.30/slf4j-api-1.7.30.jar:/work/repository/ch/qos/logback/logback-classic/1.2.3/logback-classic-1.2.3.jar:/work/repository/ch/qos/logback/logback-core/1.2.3/logback-core-1.2.3.jar com.bpfaas.common.utils.identify.IdentityGenerator
```

#### Windows x86-32

```shell
$ docker run --rm -d --name identity_build dockcross/windows-static-x86 tail -f /dev/null
$ docker cp ~/Downloads/OpenJDK8U-jdk_x86-32_windows_hotspot_8u282b08.zip identity_build:/work
$ docker exec -it identity_build bash -c 'unzip OpenJDK8U-jdk_x86-32_windows_hotspot_8u282b08.zip && ls'
$ docker cp src/main/java/com/bpfaas/common/utils/identify/. identity_build:/work
$ docker exec -it identity_build bash -c 'export JAVA_HOME=/work/jdk8u282-b08/ && $CC -Wl,--add-stdcall-alias -I"$JAVA_HOME/include" -I"$JAVA_HOME/include/win32" -shared -o identify.dll identify.c'
$ docker cp identity_build:/work/identify.dll src/main/resources/natives/windows_32/
$ docker stop identity_build
```

#### Windows x64

```shell
$ docker run --rm -d --name identity_build dockcross/windows-static-x64 tail -f /dev/null
$ docker cp ~/Downloads/OpenJDK8U-jdk_x64_windows_hotspot_8u282b08.zip identity_build:/work
$ docker exec -it identity_build bash -c 'unzip OpenJDK8U-jdk_x64_windows_hotspot_8u282b08.zip && ls'
$ docker cp src/main/java/com/bpfaas/common/utils/identify/. identity_build:/work
$ docker exec -it identity_build bash -c 'export JAVA_HOME=/work/jdk8u282-b08/ && $CC -I"$JAVA_HOME/include" -I"$JAVA_HOME/include/win32" -shared -o identify.dll identify.c'
$ docker cp identity_build:/work/identify.dll src/main/resources/natives/windows_64/
$ docker stop identity_build
```

测试

```
> java -cp Z:\.m2\repository\com\bpfaas\bp-common\0.0.4-SNAPSHOT\bp-common-0.0.4-SNAPSHOT.jar;Z:\.m2\repository\org\scijava\native-lib-loader\2.3.5\native-lib-loader-2.3.5.jar;Z:\.m2\repository\org\slf4j\slf4j-api\1.7.30\slf4j-api-1.7.30.jar;Z:\.m2\repository\ch\qos\logback\logback-classic\1.2.3\logback-classic-1.2.3.jar;Z:\.m2\repository\ch\qos\logback\logback-core\1.2.3\logback-core-1.2.3.jar com.bpfaas.common.utils.identify.IdentityGenerator
```
