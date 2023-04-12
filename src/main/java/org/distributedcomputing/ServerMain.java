package org.distributedcomputing;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerMain {
    public static void main(String[] args) {
        try {
            // Create two server objects
            ServerImpl server1 = new ServerImpl();
            ServerImpl server2 = new ServerImpl();
            // Export the remote objects to the stubs
            UnicastRemoteObject.unexportObject(server1, true);
            UnicastRemoteObject.unexportObject(server2, true);
            Server stub1 = (Server) UnicastRemoteObject.exportObject(server1, 0);
            Server stub2 = (Server) UnicastRemoteObject.exportObject(server2, 0);
            // Get the registry
            Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1099);
            // Bind the stubs to the registry with names
            registry.bind("Server1", stub1);
            registry.bind("Server2", stub2);
            // Print a message
            System.out.println("Servers ready");
        } catch (Exception e) {
            // Handle the exception
            System.err.println("Server exception: " + e);
            e.printStackTrace();
        }
    }
}
