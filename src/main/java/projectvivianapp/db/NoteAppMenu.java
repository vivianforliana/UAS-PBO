/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectvivianapp.db;

import java.util.List;
import java.util.Scanner;

/**
 *
 * @author vivia
 */

public class NoteAppMenu {
    private NoteService noteService;
    private Scanner scanner;

    public NoteAppMenu(String databasePath) {
        noteService = new NoteService(new DatabaseStorage(databasePath));
        scanner = new Scanner(System.in);
    }

    public void start() {
        boolean exit = false;
        while (!exit) {
            showMenu();
            int choice = getUserChoice();
            switch (choice) {
                case 1:
                    addNote();
                    break;
                case 2:
                    showNotes();
                    break;
                case 3:
                    deleteNote();
                    break;
                case 4:
                    exit = true;
                    System.out.println("Thank you for using the app.");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        }
    }

    private void showMenu() {
        System.out.println("\nNote App Menu: Oleh Vivian Forliana NIM: 23201243");
        System.out.println("1. Add Note");
        System.out.println("2. Show Notes");
        System.out.println("3. Delete Note");
        System.out.println("4. Exit");
        System.out.print("Enter your choice (1-4): ");
    }

    private int getUserChoice() {
        while (!scanner.hasNextInt()) {
            System.out.print("Please input valid number: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private void addNote() {
        scanner.nextLine(); // Clear buffer
        System.out.print("Enter Note: ");
        String note = scanner.nextLine();
        noteService.createNote(note);
        System.out.println("Note Saved: " + note);
    }

    private void showNotes() {
        List<String> notes = noteService.readNotes();
        System.out.println("Saved Notes:");
        if (notes.isEmpty()) {
            System.out.println("No notes saved.");
        } else {
            for (int i = 0; i < notes.size(); i++) {
                System.out.println((i + 1) + ". " + notes.get(i));
            }
        }
    }

    private void deleteNote() {
        showNotes();
        if (noteService.getNoteCount() > 0) {
            System.out.print("Enter the note ID to delete: ");
            int noteIndex = getUserChoice() - 1;
            if (noteIndex >= 0 && noteIndex < noteService.getNoteCount()) {
                String note = noteService.getNoteByIndex(noteIndex);
                noteService.deleteNote(note);
                System.out.println("Note deleted successfully: " + note);
            } else {
                System.out.println("Invalid input. Please enter a valid note ID.");
            }
        }
    }
}
