package build.max.common.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import build.max.common.exception.OfExecuteCommandException;

import lombok.extern.slf4j.Slf4j;

/**
 * 用于执行控制台命令.
 * 
 * @author brian.li
 */
@Slf4j
public class CommandUtils {

  public static class ExecResult {
    /**
     * 退出code.
     */
    public int code;
  }

  /**
   * 执行command.
   * 
   * @param cmd 命令.
   * @return 命令进程退出code.
   * @throws OfExecuteCommandException 命令执行错误时发生
   */
  public static ExecResult exec(String cmd) throws OfExecuteCommandException {
    return exec(new String[] { cmd }, null, null, null, null);
  }

  /**
   * 执行command.
   * 
   * @param cmd        命令.
   * @param infoStream 提供接口以便接收信息流.
   * @param errStream  提供接口以便接收错误流.
   * @return 命令进程退出code.
   * @throws OfExecuteCommandException 命令执行错误时发生
   */
  public static ExecResult exec(String cmd, ICommandStream infoStream, ICommandStream errStream)
      throws OfExecuteCommandException {
    return exec(new String[] { cmd }, null, null, infoStream, errStream);
  }

  /**
   * 执行command.
   * 
   * @param cmdarray 命令及参数.
   * @return 命令进程退出code.
   * @throws OfExecuteCommandException 命令执行错误时发生
   */
  public static ExecResult exec(String[] cmdarray) throws OfExecuteCommandException {
    return exec(cmdarray, null, null, null, null);
  }

  /**
   * 执行command.
   * 
   * @param cmdarray   命令及参数.
   * @param infoStream 提供接口以便接收信息流.
   * @param errStream  提供接口以便接收错误流.
   * @return 命令进程退出code.
   * @throws OfExecuteCommandException 命令执行错误时发生
   */
  public static ExecResult exec(String[] cmdarray, ICommandStream infoStream, ICommandStream errStream)
      throws OfExecuteCommandException {
    return exec(cmdarray, null, null, infoStream, errStream);
  }

  /**
   * 执行command.
   * 
   * @param cmdarray 命令及参数.
   * @param envp     环境变量; 格式为 <i>name</i>=<i>value</i>
   * @return 命令进程退出code.
   * @throws OfExecuteCommandException 命令执行错误时发生
   */
  public static ExecResult exec(String[] cmdarray, String[] envp) throws OfExecuteCommandException {
    return exec(cmdarray, envp, null, null, null);
  }

  /**
   * 执行command.
   * 
   * @param cmdarray   命令及参数.
   * @param envp       环境变量; 格式为 <i>name</i>=<i>value</i>
   * @param infoStream 提供接口以便接收信息流.
   * @param errStream  提供接口以便接收错误流.
   * @return 命令进程退出code.
   * @throws OfExecuteCommandException 命令执行错误时发生
   */
  public static ExecResult exec(String[] cmdarray, String[] envp, ICommandStream infoStream, ICommandStream errStream)
      throws OfExecuteCommandException {
    return exec(cmdarray, envp, null, infoStream, errStream);
  }

  /**
   * 执行command.
   * 
   * @param cmdarray   命令及参数.
   * @param envp       环境变量; 格式为 <i>name</i>=<i>value</i>
   * @param workdir    工作目录
   * @param infoStream 提供接口以便接收信息流.
   * @param errStream  提供接口以便接收错误流.
   * @return 命令进程退出code.
   * @throws OfExecuteCommandException 命令执行错误时发生
   */
  public static ExecResult exec(String[] cmdarray, String[] envp, String workdir, ICommandStream infoStream,
      ICommandStream errStream) throws OfExecuteCommandException {
    try {
      Process process = Runtime.getRuntime().exec(cmdarray, envp, workdir == null ? null : new File(workdir));

      ExecResult result = new ExecResult();

      if (errStream != null) {
        try (BufferedReader brError = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {
          // error.
          do {
            String line = brError.readLine();
            if (line != null) {
              errStream.onStream(line);
            } else {
              break;
            }
          } while (true);
        } catch (Exception e) {
          log.error("exec stream error", e);
        }
      } // if.

      if (infoStream != null) {
        try (BufferedReader brInfo = new BufferedReader(new InputStreamReader(process.getInputStream()))) {

          // info.
          do {
            String line = brInfo.readLine();
            if (line != null) {
              infoStream.onStream(line);
            } else {
              break;
            }
          } while (true);
        } catch (Exception e) {
          log.error("exec stream error", e);
        }
      } // if.

      process.waitFor();
      result.code = process.exitValue();
      return result;
    } catch (Exception e) {
      log.error("exec error", e);
      throw new OfExecuteCommandException(e);
    }
  }
}