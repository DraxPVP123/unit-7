import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;
/********************************************************************
 * Written by: Ari Goldberg
 *
 * ASSIGNMENT: Add a search method to your StateDB program that does
 * 	the following:
 *
 * search:  should ask the user what field they want to search
 * 	(using buttons) and then call one of three search methods:
 *
 * 	searchForState - should ask for the state to search for and
 * 		use a binary search to find the state and print the data
 * 		for that state.
 * 	searchForPopulation - should ask for a low and a high population
 * 		to search for and use a sequential search to print the data
 * 		for all states with populations in that range in one JOP
 * 		window.
 *  searchForBirdFlower - should ask for the bird or flower to
 *  	search for and use a sequential search to print all states
 *  	with that bird or flower in one JOP window.  You should only
 *  	have one set of search code that searches for either one.
 *
 * For all 3 searches if the search data isn't found you should print
 * 		an appropriate message.  Case shouldn't matter in your search.
 *
 * HAND IN: Copy and paste your code to Schoology and submit!
 *
 ********************************************************************/
public class StateDB {
    public static void changeJOP() {
        UIManager um = new UIManager();
        um.put("OptionPane.messageForeground", UIManager.getFont("Arial"));
        um.put("Panel.background", Color.white);
        um.put("Label.font", new FontUIResource(new Font("Times New Roman", Font.BOLD, 58)));
        um.put("OptionPane.messageForeground", new Color(0, 0, 0));
        um.put("TextField.background", Color.white);
        um.put("TextField.font", new FontUIResource(new Font("Dialog", Font.BOLD, 24)));
        um.put("TextField.foreground", Color.black);
        um.put("Panel.background", new Color(215, 214, 214));
        um.put("OptionPane.background", new Color(215, 214, 214));
        um.put("Button.background", new Color(132, 112, 255));
        um.put("Button.foreground", new Color(0, 0, 0));
        um.put("Button.font", new FontUIResource(new Font("Arial", Font.BOLD, 14)));
    }
    public static void main(String[] args) {
        changeJOP();
        String states[][] = new String[51][5];
        fillArray(states);
        int choice;
        do {
            choice = menu();
            switch (choice) {
                case 0: printArray(states);break;
                case 1: search(states);break;
                case 2: System.exit(0); break;
            }
        }while (choice != 3);
    }
    public static int menu() {
        String message = "";
        message += "What do you want to do?";
        String[] buttons = {"Print", "Search", "Quit"};

        int choice = JOptionPane.showOptionDialog(null, message, "Choices", 0, 3, null, buttons, null);
        return choice;
    }
    public static String[][] fillArray(String states[][]) {
        try {
            Scanner inFile = new Scanner(new File("states.txt"));

            while (inFile.hasNext()) {
                for (int r = 0; r < 51; r++) {
                    for (int c = 0; c < 5; c++) {
                        states[r][c] = inFile.nextLine();

                    }
                }
            }
            inFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return states;
    }
    public static void printArray(String[][] states) {
        JTextArea area = new JTextArea();
        String name = "name", capital = "capital", flower = "flower", bird = "bird", pop = "population";
        area.append("STATE NAME" + "\t" + "\t" + "       " + "CAPITAL" + "\t" + "\t" + "\t" + "      " + "FLOWER" + "\t" + "\t" + "\t" + "     " +
                "BIRD" + "\t" + "\t" + "\t" + " " + "POPULATION" + "\n" + "\n");
        int spaces[] = new int[]{30, 30, 30, 30};

        for (int r = 0; r < states.length; r++) {

            name = states[r][0];
            capital = states[r][1];
            flower = states[r][2];
            bird = states[r][3];
            pop = states[r][4];

            while (name.length() <= spaces[0])
                name += " ";
            while (capital.length() <= spaces[1])
                capital += " ";
            while (flower.length() <= spaces[2])
                flower += " ";
            while (bird.length() <= spaces[3])
                bird += " ";
            area.append(name + capital + flower + bird + pop + "\n");
        }
        area.setBackground(new Color(255,250,205));
        area.setForeground(new Color(0,0,0));
        area.setFont(new Font("Consolas", Font.PLAIN, 15));
        area.setRows(35);
        area.setColumns(115);
        JScrollPane pane = new JScrollPane(area);
        JOptionPane.showMessageDialog(null,pane);
    }
    public static void search(String[][] states) {
        String message = "";
        message += "What do you want to search for?";
        String[] buttons = {"State Name", "Flower", "Bird", "Population"};

        int n = JOptionPane.showOptionDialog(null, message, "Choices", 0, 3, null, buttons, null);

        switch (n) {
            case 0: searchState(states); break;
            case 1: searchFlower(states);break;
            case 2: searchBird(states);break;
            case 3: searchPopulation(states); break;
        }
    }
    public static void searchState(String[][] states) {
        {
            String searchForState = JOptionPane.showInputDialog("What state would you like to search for?");
            searchForState = searchForState.toUpperCase();

            String message = "";
            int r = 0;

            while (r < 51 && !states[r][0].equalsIgnoreCase(searchForState)) {
                r++;
            }

            if (r < 51) {
                message += "Here is the information for " + searchForState + ":" + "\n\n";
                message += "State's Name: " + states[r][0] + "\n";
                message += "State's Capital: " + states[r][1] + "\n";
                message += "State's Flower: " + states[r][2] + "\n";
                message += "State's Bird: " + states[r][3] + "\n";
                message += "State's Population: " + states[r][4] + "\n";
            } else {
                message += "Sorry, there were no matches for your data";
            }
            JOptionPane.showMessageDialog(null, message);
        }
    }
    public static void searchFlower(String[][] states) {
        String searchForFlower = JOptionPane.showInputDialog("What flower would you like to search for?");
        int r = 0;
        ArrayList<String> flowerPerStates = new ArrayList<String>();
        for (int i = 0; i < 51; i++) {
            if (searchForFlower.equalsIgnoreCase(states[r][2])) {
                flowerPerStates.add(0, states[r][0]);
                r++;
            } else
                r++;
        }
        String message = "";

        if (flowerPerStates.size() == 0)
            message += "Sorry, there were no matches for your data";
        else {
            message += "Here are all the states who have " + searchForFlower + " as their state flower:" + "\n\n";

            for (int i = 0; i < flowerPerStates.size(); i++)
                message += flowerPerStates.get(i) + "\n";
        }
        JOptionPane.showMessageDialog(null, message);
    }
    public static void searchBird(String[][] states) {
        String searchForBird = JOptionPane.showInputDialog("What bird would you like to search for?");
        int r = 0;
        ArrayList<String> birdPerStates = new ArrayList<String>();

        for (int i = 0; i < 51; i++) {
            if (searchForBird.equalsIgnoreCase(states[r][3]))
                birdPerStates.add(0, states[r][0]);
            else
                r++;
        }
        String message = "";

        if (birdPerStates.size() == 0)
            message += "Sorry, there were no matches for your data";
        else {
            message += "Here are all the states who have " + searchForBird + " as their state bird:" + "\n\n";

            String birdAdded = "";

            for (int i = 0; i < birdPerStates.size(); i++)
                birdAdded = birdPerStates.get(i);
            message += birdAdded.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null, message);
    }
    public static void searchPopulation(String states[][]) {
        int lowPopulation = Integer.parseInt(JOptionPane.showInputDialog("Enter a low population"));
        int highPopulation = Integer.parseInt(JOptionPane.showInputDialog("Enter a high population"));

        int r = 0;
        int p = 0;

        ArrayList<String> populationPerStates = new ArrayList<String>();

        for (int i = 0; i < 51; i++) {
            p = Integer.parseInt(states[r][4]);
            if (p >= lowPopulation && p <= highPopulation) {
                populationPerStates.add(states[r][0]); r++;
            } else r++;
        }
        String message = "";

        if (populationPerStates.size() == 0)
            message += "Sorry, there were no matches for your data";
        else {
            message += "Here are all the states who's population is between " + lowPopulation + " and " + highPopulation + ":\n\n";

            String populationAdded = "";

            for (int i = 0; i < populationPerStates.size(); i++) {
                populationAdded = populationPerStates.get(i);
                message += populationAdded.toString() + "\n";
            }
        }
        JOptionPane.showMessageDialog(null, message);
    }
}