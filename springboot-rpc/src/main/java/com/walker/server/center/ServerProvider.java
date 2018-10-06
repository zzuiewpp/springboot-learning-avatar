package com.walker.server.center;

import com.walker.server.service.HelloService;
import com.walker.server.service.HelloServiceImpl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangpp
 * @version 1.0
 * @since 2018-09-07 01:18
 */
public class ServerProvider {
    private static Map<String, Object> services = new HashMap<>();

    // 将服务端接口注册到一个map中
    static {
        services.put(HelloService.class.getName(), new HelloServiceImpl());
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        // ServerSocket负责接收客户连接请求，服务器端需要创建监听特定端口的ServerSocke
        ServerSocket serverSocket = new ServerSocket(8080);
        while (true) {
            // 使Server端的程序处于等待状态，程序将一直阻塞直到捕捉到一个来自Client端的请求，并返回一个用于与该Client通信的Socket对象Link-Socket
            Socket server = serverSocket.accept();

            //读取底层输出流的数据
            ObjectInputStream input = new ObjectInputStream(server.getInputStream());
            String serviceName = input.readUTF();
            String methodName = input.readUTF();
            Class<?>[] parameterTypes = (Class<?>[]) input.readObject();
            Object[] arguments = (Object[]) input.readObject();

            // 反射获取接口相关数据
            Class serviceInterfaceClass = Class.forName(serviceName);
            Object serviceImpl = services.get(serviceName);
            Method methods = serviceInterfaceClass.getMethod(methodName, parameterTypes);
            Object result = methods.invoke(serviceImpl, arguments);

            // 服务端接口执行结果写到底层输入流
            ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
            System.out.println("服务端返回调用接口结果\n");
            out.writeObject(result);
        }
    }
}
