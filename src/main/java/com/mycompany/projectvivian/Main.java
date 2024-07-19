/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.projectvivian;

import projectvivianapp.db.NoteAppMenu;

public class Main {
    public static void main(String[] args) {
        NoteAppMenu noteapp = new NoteAppMenu("notes.db");
        noteapp.start();
    }
}

