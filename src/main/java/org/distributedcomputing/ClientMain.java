package org.distributedcomputing;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

public class ClientMain {
    public static void main(String[] args) {
        try {
            // Get the registry
            Registry registry = LocateRegistry.getRegistry();
            // Lookup the stubs for the servers by name
            Server stub1 = (Server) registry.lookup("Server1");
            Server stub2 = (Server) registry.lookup("Server2");
            // Fetch the lists of strings from the servers
            List<String> list1 = stub1.fetchList("file1.txt");
            List<String> list2 = stub2.fetchList("file2.txt");
            // Compare the list sizes and call the compareAndMerge method on the server with the bigger list
            List<String> result;
            if (list1.size() > list2.size()) {
                result = stub1.compareAndMerge(list1, list2);
            } else {
                result = stub2.compareAndMerge(list1, list2);
            }
            // Print the result
            System.out.println("The merged list of unique strings is: ");
            for (String s : result) {
                System.out.println(s);
            }
        } catch (Exception e) {
            // Handle the exception
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}

