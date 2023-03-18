package libraryManagement.storage;

import libraryManagement.models.*;
import java.io.*;
import java.util.ArrayList;

public class Storage<T extends Identifiable> {
    private final String DATABASE_LOCATION = "E:\\Java\\src\\libraryManagement\\Data\\";
    private final String databasePath;

    public Storage(String subdirectory)  {
        File databaseDir = new File(DATABASE_LOCATION);
        if(!databaseDir.exists()) {
            databaseDir.mkdir();
        }
        databasePath = DATABASE_LOCATION + subdirectory + "\\";
        File dataDir = new File(databasePath);
        if(!dataDir.exists()) {
            dataDir.mkdir();
        }
    }

    public T getById(String id) {
        try {
            String path = databasePath + id;
            FileInputStream fileInputStream = new FileInputStream(path);
            ObjectInputStream data = new ObjectInputStream(fileInputStream);
            T obj = (T) data.readObject();
            data.close();
            fileInputStream.close();
            return obj;
        }
        catch (IOException | ClassNotFoundException e) {

        }
        return null;
    }

    public void add(T obj) {
        try {
            String path = databasePath + obj.getId();
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            ObjectOutputStream data = new ObjectOutputStream(fileOutputStream);
            data.writeObject(obj);
            data.close();
            fileOutputStream.close();
        }
        catch (IOException e) {

        }
    }

    public boolean delete(String id) {
        File data = new File(databasePath + id);
        return data.delete();
    }

    public boolean exists(String id) {
        File data = new File(databasePath + id);
        return data.exists();
    }

    public ArrayList<T> search(String keyword) {
        File dir = new File(databasePath);
        String[] files = dir.list();

        if (files == null) {
            System.out.println("Empty folder");
            return null;
        }

        ArrayList<T> list = new ArrayList<>();
        for (String file : files) {
            T obj = getById(file);
            if (obj.getSearchableValue().toLowerCase().contains(keyword.toLowerCase())) {
                list.add(obj);
            }
        }
        return list;
    }
}
