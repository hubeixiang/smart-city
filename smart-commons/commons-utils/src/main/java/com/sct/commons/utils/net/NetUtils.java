package com.sct.commons.utils.net;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * @author sven
 * 获取本机ip,与获取本机可用端口的工具类
 */
public final class NetUtils {

    public static final String LOCALHOST = "127.0.0.1";
    public static final String ANYHOST = "0.0.0.0";
    /**
     * The minimum number of server port number.
     */
    public static final int MIN_PORT_NUMBER = 1;
    /**
     * The maximum number of server port number.
     */
    public static final int MAX_PORT_NUMBER = 49151;
    private static final Logger logger = LoggerFactory.getLogger(NetUtils.class);
    private static final Pattern IP_PATTERN = Pattern.compile("\\d{1,3}(\\.\\d{1,3}){3,5}$");
    private static volatile InetAddress LOCAL_ADDRESS = null;

    public static String getLocalHost() {
        InetAddress address = getLocalAddress();
        return address == null ? LOCALHOST : address.getHostAddress();
    }

    /**
     * 遍历本地网卡，返回第一个合理的IP。
     *
     * @return 本地网卡IP
     */
    public static InetAddress getLocalAddress() {
        if (LOCAL_ADDRESS != null)
            return LOCAL_ADDRESS;
        InetAddress localAddress = getLocalAddress0();
        LOCAL_ADDRESS = localAddress;
        return localAddress;
    }

    private static InetAddress getLocalAddress0() {
        InetAddress localAddress = null;
        try {
            localAddress = InetAddress.getLocalHost();
            if (isValidAddress(localAddress)) {
                return localAddress;
            }
        } catch (Throwable e) {
            logger.warn("Failed to retriving ip address, " + e.getMessage(), e);
        }
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            if (interfaces != null) {
                while (interfaces.hasMoreElements()) {
                    try {
                        NetworkInterface network = interfaces.nextElement();
                        Enumeration<InetAddress> addresses = network.getInetAddresses();
                        if (addresses != null) {
                            while (addresses.hasMoreElements()) {
                                try {
                                    InetAddress address = addresses.nextElement();
                                    if (isValidAddress(address)) {
                                        return address;
                                    }
                                } catch (Throwable e) {
                                    logger.warn("Failed to retriving ip address, " + e.getMessage(), e);
                                }
                            }
                        }
                    } catch (Throwable e) {
                        logger.warn("Failed to retriving ip address, " + e.getMessage(), e);
                    }
                }
            }
        } catch (Throwable e) {
            logger.warn("Failed to retriving ip address, " + e.getMessage(), e);
        }
        logger.error("Could not get local host ip address, will use 127.0.0.1 instead.");
        return localAddress;
    }

    private static boolean isValidAddress(InetAddress address) {
        if (address == null || address.isLoopbackAddress())
            return false;
        String name = address.getHostAddress();
        return (name != null && !ANYHOST.equals(name) && !LOCALHOST.equals(name) && IP_PATTERN.matcher(name).matches());
    }

    public static int getFreeSocketPort() {
        return getFreeSocketPort(-1, -1, -1);
    }

    public static int getFreeSocketPort(Set<Integer> excludePortSet) {
        return getFreeSocketPort(-1, -1, -1, excludePortSet);
    }

    public static int getFreeSocketPort(Integer default_port) {
        return getFreeSocketPort(default_port, -1, -1);
    }

    public static int getFreeSocketPort(Integer default_port, int port_start, int port_end) {

        return getFreeSocketPort(default_port, port_start, port_end, null);
    }

    public static int getFreeSocketPort(Integer default_port, int port_start, int port_end, Set<Integer> excludePortSet) {

        if (default_port != null && default_port > 0) {
            boolean flag = available(default_port);
            if (flag) {
                return default_port;
            }

        }
        if (port_start < 1) {
            port_start = 10000;
        }

        if (port_end < 0 || port_end > 65536) {
            port_end = 65535;
        }

        return getNextAvailable(port_start, port_end, excludePortSet);
    }

    public static int getAvailablePort() {
        return getNextAvailable(1024, MAX_PORT_NUMBER, null);
    }

    public static int getAvailablePort(int fromPort) {
        return getNextAvailable(fromPort, MAX_PORT_NUMBER, null);
    }

    private static int getNextAvailable(int beginPort, int endPort, Set<Integer> excludePortSet) {
        if ((beginPort < MIN_PORT_NUMBER) || (beginPort > MAX_PORT_NUMBER)) {
            throw new IllegalArgumentException("Invalid start port: " + beginPort);
        }
        int tempEndProt = MAX_PORT_NUMBER;
        if (endPort > 0) {
            tempEndProt = endPort;
        }

        for (int i = beginPort; i <= tempEndProt; i++) {

            if (excludePortSet != null && excludePortSet.contains(i)) {
                continue;
            }

            if (available(i)) {
                return i;
            }
        }
        throw new IllegalArgumentException("Could not find an available port " + "above " + beginPort + ",endPort=" + endPort);
    }

    public static boolean isLocalFreePort(int port) {
        boolean flag = available(port);
        if (flag) {
            // 空闲端口
            return true;
        } else {
            // 非可用端口
            return false;
        }
    }

    /**
     * Checks to see if a specific port is available.
     *
     * @param port the port to check for availability
     */
    private static boolean available(int port) {
        boolean available = false;
        if ((port < MIN_PORT_NUMBER) || (port > MAX_PORT_NUMBER)) {
            throw new IllegalArgumentException("Invalid start port: " + port);
        }

        Socket socket = null;
        try {
            SocketAddress socketAddress = new InetSocketAddress(port);
            socket = new Socket();
            socket.bind(socketAddress);
            available = true;
        } catch (UnknownHostException e) { // unknown host
            logger.error("available UnknownHostException.port=" + port, e);
            available = false;
        } catch (IOException e) { // io exception, service probably not
            // running,maybe the port available
            available = false;
        } catch (Throwable e) { // io exception, service probably not
            logger.error("available Throwable.port=" + port, e);
            available = false;
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (Exception e) {
                    logger.error("available Throwable.port=" + port, e);
                } catch (Throwable e) {
                    logger.warn("available Throwable.port=" + port, e);
                }
            }
        }
        return available;
    }

    public static boolean potAvailabilityCheck(int port) {
        return available(port);
    }

    public static boolean rmoatePortAvailabilityCheck(String ip, int port) {
        boolean available = false;
        Socket socket = null;
        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress(ip, port), 10);
            available = false;
        } catch (SocketTimeoutException e) { // io exception, service probably
            // not running
            available = true;

        } catch (Exception e) { // io exception, service probably not running
            available = true;
            logger.warn("rmoatePortAvailabilityCheck Exception.ip=" + ip + ",port=" + port, e);

        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return available;
    }

    private static int getRmoatePortNextAvailable(String remoteHost, int beginPort, int endPort, Set<Integer> excludePortSet) {
        if ((beginPort < MIN_PORT_NUMBER) || (beginPort > MAX_PORT_NUMBER)) {
            throw new IllegalArgumentException("Invalid start port: " + beginPort);
        }
        int tempEndProt = MAX_PORT_NUMBER;
        if (endPort > 0) {
            tempEndProt = endPort;
        }

        for (int i = beginPort; i < tempEndProt; i++) {

            if (excludePortSet != null && excludePortSet.contains(i)) {
                continue;
            }
            if (rmoatePortAvailabilityCheck(remoteHost, i)) {
                return i;
            }
        }
        throw new IllegalArgumentException("Could not find an available port. beginPort=" + beginPort + ",endPort=" + endPort + ",remoteHost=" + remoteHost);
    }

    public static int getFreeSocketPort(String remoteHost) {
        if (remoteHost == null || remoteHost.length() == 0) {
            return getFreeSocketPort();
        } else {
            return getRmoateFreeSocketPort(remoteHost, -1, -1, -1);
        }
    }

    public static int getFreeSocketPort(String remoteHost, Set<Integer> excludePortSet) {
        if (remoteHost == null || remoteHost.length() == 0) {
            return getFreeSocketPort(excludePortSet);
        } else {
            return getRmoateFreeSocketPort(remoteHost, -1, -1, -1, excludePortSet);
        }
    }

    public static int getRmoateFreeSocketPort(String remoteHost, int default_port, int port_start, int port_end) {
        return getRmoateFreeSocketPort(remoteHost, default_port, port_start, port_end, null);
    }

    public static int getRmoateFreeSocketPort(String remoteHost, int default_port, int port_start, int port_end, Set<Integer> excludePortSet) {

        if (default_port > 0) {
            boolean flag = rmoatePortAvailabilityCheck(remoteHost, default_port);
            if (flag) {
                return default_port;
            }

        }
        if (port_start < 1) {
            port_start = 10000;
        }

        if (port_end < 1 || port_end > 65536) {
            port_end = 65535;
        }

        return getRmoatePortNextAvailable(remoteHost, port_start, port_end, null);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        Integer port = NetUtils.getFreeSocketPort();
        System.out.println(port);
        Integer default_port = NetUtils.getFreeSocketPort(12585);
        System.out.println(default_port);

        boolean rmoatePortAvailabilityCheckFlag_false = NetUtils.rmoatePortAvailabilityCheck("ctdp", 2181);
        System.out.println("rmoatePortAvailabilityCheckFlag_false:" + rmoatePortAvailabilityCheckFlag_false);
        boolean rmoatePortAvailabilityCheckFlag_true = NetUtils.rmoatePortAvailabilityCheck("ctdp", 2182);
        System.out.println("rmoatePortAvailabilityCheckFlag_true:" + rmoatePortAvailabilityCheckFlag_true);
    }
}
