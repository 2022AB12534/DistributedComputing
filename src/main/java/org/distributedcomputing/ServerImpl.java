package org.distributedcomputing;

import java.io.File;
import java.io.FileNotFoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class ServerImpl extends UnicastRemoteObject implements Server {
    // Constructor
    public ServerImpl() throws RemoteException {
        super();
    }

    // Method to fetch a list of strings from a file
    public List<String> fetchList(String filename) throws RemoteException {
        List<String> list = new ArrayList<String>();
        try {
            // Open the file and scan its contents
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            // Add each line to the list
            while (scanner.hasNextLine()) {
                list.add(scanner.nextLine());
            }
            // Close the scanner
            scanner.close();
        } catch (FileNotFoundException e) {
            // Handle the exception
            System.out.println("File not found: " + filename);
            e.printStackTrace();
        }
        return list;
    }

    // Method to compare and merge two lists of strings
    public List<String> compareAndMerge(List<String> list1, List<String> list2) throws RemoteException {
        List<String> result = new ArrayList<String>();
        // Use a hash set to store the unique strings
        HashSet<String> set = new HashSet<String>();
        // Add all the strings from the first list to the set
        for (String s : list1) {
            set.add(s);
        }
        // Add all the strings from the second list to the set
        for (String s : list2) {
            set.add(s);
        }
        // Convert the set to a list
        result.addAll(set);
        return result;
    }
}