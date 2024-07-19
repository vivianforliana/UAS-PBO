/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package projectvivianapp.db;

import java.util.List;
/**
 *
 * @author vivia
 */


public interface DataStorage {
    void writeData(String note);
    List<String> readData();
    void deleteData(String note);
}
