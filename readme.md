[![Maven Central](https://maven-badges.herokuapp.com/maven-central/build.max/common/badge.svg)](https://maven-badges.herokuapp.com/maven-central/build.max/common/)
[![License](https://img.shields.io/github/license/originforest/lib-java-common)](https://opensource.org/licenses/MIT)

Common library in MAXBUILD

- [Setup](#setup)
- [ErrorCode](#errorcode)
- [Message](#message)
  - [Response](#response)
  - [Msg](#msg)
- [Common Exception](#common-exception)
- [Common Constant](#common-constant)
- [Utility](#utility)
  - [CommandUtils](#commandutils)
  - [CryptUtils](#cryptutils)
  - [DateUtils](#dateutils)
  - [FileUtils](#fileutils)
  - [JsonUtils](#jsonutils)
  - [PathUtils](#pathutils)
  - [StringUtils](#stringutils)
  - [分布式ID](#分布式id)

## Setup

```html
<dependency>
    <groupId>build.max</groupId>
    <artifactId>common</artifactId>
    <version>0.0.1.RELEASE</version>
</dependency>
```

## ErrorCode

定义所用到的 errorCode

```java


new Msg(ErrorCode.OK);
new Msg(ErrorCode.UNAUTHORIZE);
```

## Message

网络消息包相关对象.

### Response

功能等同于 ResponseEntity, 可设置 statusCode 及 headers;

_在RestController中返回此对象将构造适当的http报文返回给客户端._

```java


new Response().setBody(...);
new Response().setHeader("Content-Type", "Jsonxxx");
new Response().setStatusCode(200);
```

### Msg

定义平台服务间的通信对象

_在RestController中返回此对象将按照消息包格式返回数据给客户端._

```java


// To create a HashMap data.
new Msg<Object>();   //  MsgObject === Msg<Object>
// To create a AnyEntity data.
new Msg<AnyEntity>();

// To create a msg only contain errorCode.
new Msg().setErr(ErrorCode.NOT_FOUND);
new Msg(ErrorCode.NOT_FOUND);
new Msg(ErrorCode.NOT_FOUND, "找不到指定的资源");

// To create a msg contain ErrorCode.OK and data
new Msg(new Object(...));
```

## Common Exception

定义系统用到的一些异常类型.


| 异常类名 | 说明 |
| - | - |
| build.max.`OfException` | of系统一般异常基类 |
| build.max.`OfRuntimeException` | of系统runtime异常基类 |

OfException 派生类


| 异常类名 | 说明 |
| - | - |
| build.max.`OfDBException` | 数据库异常 |
| build.max.`OfExecuteCommandException` | command执行错误异常 |
| build.max.`OfJsonException` | json相关错误异常 |

OfRuntimeException 派生类


| 异常类名 | 说明 |
| - | - |
| build.max.`OfErrSubcodeException` | 附带某个具体业务错误码(subCode)异常 |
| build.max.`OfMsgException` | 消息处理错误相关异常 |
| build.max.`OfNotFoundException` | NotFound异常 |
| build.max.`OfRawMsgException` | 在restController中抛出此异常, 可以根据设置的 httpStatusCode 并返回http报文 |

## Common Constant

定义系统用到的一些常量


| 常量名 | 说明 |
| - | - |
| DBConstant.`COL_DEFINITION_ID` | "char(32)", 可用定义id数据库字段类型 |
| DBConstant.`COL_DEFINITION_ENUM` | "varchar(20)", 可用定义枚举,状态等数据库字段类型 |

## Utility

一些常用工具对象.

### CommandUtils

操作系统命令执行工具

```java


ExecResult result;

// process exit code.
result = CommandUtils.exec("cat /proc/cpuinfo");
result.code;

// get output stream.
CommandUtils.exec("cat /proc/cpuinfo", 
  (out_line) -> {
    System.out.println(out_line);
  }, (err_line)->{
    System.out.println(err_line);
  });
```

### CryptUtils

通用加密工具

```java


CryptUtils.sha1("xxxxxxxxxxxx");
CryptUtils.sha256("xxxxxxxxxxxx");
```

### DateUtils

时间工具, 用于将时间与utc时间进行转换.

```java


// 将本地时间转换为utc字符串.
DateUtils.date2utcstr(new Date());  // MMddHHmmssSSS

// 将utc字符串时间转换为本地时间.
DateUtils.utcstr2date("2020110501");  // 返回本地时间对象.
```

### FileUtils

文件工具; 进行本地文件操作; 以下举例部分接口.

```java


// 保证指定的目录存在.
FileUtils.assureDir("/xxx/xxx");

// 拷贝目录.
FileUtils.dirCopy("/xxx/src", "xxx/dest");

// 以文本方式读取文件.
FileUtils.readFile("/xxx/xx.md"); // 返回文本内容.

// 将文本写入文件.
FileUtils.writeFile("/xxx/xx.md", "content");
```

### JsonUtils

json序列化工具

```java


// 解析.
Object obj = JsonUtils.parse("{}");

// 字符串化.
String str = JsonUtils.stringify(obj);
```

### PathUtils

路径工具, 可以解决不同操作系统路径分隔符不同等问题

```java


// join路径, 生成: root/dir1/dir2
String path = PathUtils.join("root", "dir1/",  "dir2");
```

### StringUtils

字符串工具; 用于处理字符串的一些常规简化操作.

```java


// 判断字符串是否是 null或空字符串.
StringUtils.isEmpty("");
```

### 分布式ID

使用示例

```java


// 生成一个新的id.
IdentityUtils.nextId();

// 验证传入的id格式是否合法.
IdentityUtils.isValid('xxxx');

// id字符串的长度 = 32
IdentityUtils.ID_LENGTH;
```
