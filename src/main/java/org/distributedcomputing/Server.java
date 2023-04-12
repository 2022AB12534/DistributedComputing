package org.distributedcomputing;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Server extends Remote {
    // Method to fetch a list of strings from a file
    List<String> fetchList(String filename) throws RemoteException;
    // Method to compare and merge two lists of strings
    List<String> compareAndMerge(List<String> list1, List<String> list2) throws RemoteException;
}
