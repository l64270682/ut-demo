package com.xhtec.utdemo.test.utils;

import com.google.common.base.Optional;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author liyu
 */
@Slf4j
public class ProcessUtils {

    private static ProcessStrategy processStrategy;

    static {
        final String osName = System.getProperty("os.name");
        if (osName != null) {
            if (osName.contains("Windows")) {
                processStrategy = new WindowsProcessStrategy();
            } else if (osName.contains("Linux") || osName.contains("Mac")) {
                processStrategy = new UnixProcessStrategy();
            }
        }
    }

    /**
     * 按打开的端口关闭进程
     *
     * @param port
     */
    public static void killByPort(int port){
        if (processStrategy == null) {
            throw new UnsupportedOperationException("不支持该平台");
        }

        processStrategy.killByPort(port);
    }

    private interface ProcessStrategy {

        /**
         * 根据端口号杀进程接口
         *
         * @param port 端口号
         * @return 占有该端口号的进程是否被杀除
         */
        boolean killByPort(int port);

    }

    /**
     * Windows的进程管理策略
     */
    private static class WindowsProcessStrategy implements ProcessStrategy {

        @Override
        public boolean killByPort(int port) {
            if (port < 0) {
                throw new IllegalArgumentException("输入参数有误");
            }

            try {
                String str = queryProcess(String.valueOf(port));
                if (str == null) {
                    log.error("没有查询到占有该端口号的进程");
                    return false;
                } else {
                    int lastIndex = str.lastIndexOf(" ");
                    String pid = str.substring(lastIndex);
                    // 截取进程号
                    killPid(pid);
                    log.info("成功查杀占有该端口进程");
                    return true;
                }
            } catch (IOException e) {
                log.warn("关闭进程失败 [port={}]", port, e);
            }

            return false;
        }

        /**
         * 强制关闭进程
         *
         * <p>依赖taskill命令</p>
         *
         * @param pid
         * @throws IOException
         */
        private void killPid(String pid) throws IOException {
            Runtime.getRuntime().exec("taskkill /F /pid " + pid + " ");
        }

        /**
         * 按端口号查进程
         *
         * <p>使用netstat -ano 和 findstr 命令</p>
         * @param port
         * @return
         * @throws IOException
         */
        private String queryProcess(String port) throws IOException {
            InputStream in;

            Process p = Runtime.getRuntime().exec("cmd /c netstat -ano | findstr \"" + port + "\"");
            in = p.getInputStream();

            try {
                String ret;
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                while ((ret = reader.readLine()) != null) {
                    if (isValidPort(ret, port)) {
                        return ret;
                    }
                }
            } finally {
                if (in != null) {
                    in.close();
                }
            }
            return null;
        }

        private boolean isValidPort(String str, String port) {
            String regex = "^ *[a-zA-Z]+ +\\S+";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(str);
            matcher.find();

            String matched = matcher.group();
            int spstart = matched.lastIndexOf(":");
            matched = matched.substring(spstart + 1);
            if (matched.equals(port)) {
                return true;
            }
            return false;
        }
    }

    /**
     * Unix的进程管理策略
     */
    private static final class UnixProcessStrategy implements ProcessStrategy {

        /**
         * 关闭端口
         *
         * <p>依赖lsof和awk命令</p>
         *
         * @param port
         * @throws IOException
         */
        @Override
        public boolean killByPort(int port) {
            if (port < 0) {
                throw new IllegalArgumentException("输入参数有误");
            }

            try {
                String command = "lsof -i:" + port + " | awk 'NR==2{print $2}' | xargs kill -9";
                String[] cmd = { "/bin/sh", "-c", command };
                Runtime.getRuntime().exec(cmd);
                return true;
            } catch (IOException e) {
                log.warn("关闭进程失败 [port={}]", port, e);
            }
            return false;
        }

    }

    public static void main(String[] args) {
        ProcessUtils.killByPort(4300);
    }
}
