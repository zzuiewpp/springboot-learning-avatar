package com.walker.client;

import com.walker.server.service.HelloService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author walker
 * @version 1.0
 * @since 2018-09-07 01:33
 */
public class ServerConsumer {
    public static void main(String[] args) throws UnknownHostException, IOException, SecurityException, NoSuchMethodException, ClassNotFoundException {

        //服务端接口名称
        String interfaceName = HelloService.class.getName();

        //需要远程执行的方法
        Method method = HelloService.class.getMethod("hello", String.class);

        //需要传递到服务端的参数
        Object[] arguments = {"hello, this is a rpc demo...."};

        Socket socket = new Socket("127.0.0.1", 8080);

        //将方法名称和参数写到底层输入流
        ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
        output.writeUTF(interfaceName); //接口名称
        output.writeUTF(method.getName());  //方法名称
        output.writeObject(method.getParameterTypes());
        output.writeObject(arguments);

        //从服务端读取方法执行结果
        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
        Object res  =  input.readObject();

        System.out.println("客户端输出信息\n");
        System.out.println(res);
    }

}
