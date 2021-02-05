// GUI and main program for the Training Record
package com.stir.cscu9t4practical1;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.lang.Number;

public class TrainingRecordGUI extends JFrame implements ActionListener {
	private int count = 1;
    private JTextField name = new JTextField(30);
    private JTextField day = new JTextField(2);
    private JTextField month = new JTextField(2);
    private JTextField year = new JTextField(4);
    private JTextField hours = new JTextField(2);
    private JTextField mins = new JTextField(2);
    private JTextField secs = new JTextField(2);
    private JTextField dist = new JTextField(4);
    private JLabel labn = new JLabel(" Name:");
    private JLabel labd = new JLabel(" Day:");
    private JLabel labm = new JLabel(" Month:");
    private JLabel laby = new JLabel(" Year:");
    private JLabel labh = new JLabel(" Hours:");
    private JLabel labmm = new JLabel(" Mins:");
    private JLabel labs = new JLabel(" Secs:");
    private JLabel labdist = new JLabel(" Distance (km):");
    private JButton addR = new JButton("Add");
    private JButton lookUpByDate = new JButton("Look Up");
    private JButton FindAllByDate = new  JButton ("Look Up All");
    
    private JLabel terrainLabel = new JLabel("Terrain:");
    private JLabel tempoLabel = new JLabel("Tempo:");
    private JTextField terrainTField = new JTextField(5);
    private JTextField tempoTField = new JTextField(5);
    
    private JLabel repLabel = new JLabel("Reps:");
    private JLabel recLabel = new JLabel("Recovery:");
    private JTextField repTField = new JTextField(2);
    private JTextField recTField = new JTextField(2);
    
    private JLabel whereLabel = new JLabel("Location:");
    private JTextField whereTField = new JTextField(5);

    private JButton find = new JButton("Find all by name");
    private JButton remove = new JButton ("Remove");
    private TrainingRecord myAthletes = new TrainingRecord();

    private JTextArea outputArea = new JTextArea(5, 50);

    public static void main(String[] args) {
        TrainingRecordGUI applic = new TrainingRecordGUI();
    } // main

    // set up the GUI 
    public TrainingRecordGUI() {
        super("Training Record");
        setLayout(new FlowLayout());
        add(labn);
        add(name);
        name.setEditable(true);
        add(labd);
        add(day);
        day.setEditable(true);
        add(labm);
        add(month);
        month.setEditable(true);
        add(laby);
        add(year);
        year.setEditable(true);
        add(labh);
        add(hours);
        hours.setEditable(true);
        add(labmm);
        add(mins);
        mins.setEditable(true);
        add(labs);
        add(secs);
        secs.setEditable(true);
        add(labdist);
        add(dist);
        dist.setEditable(true);
        
        add(terrainLabel);
        add(terrainTField);
        terrainTField.setEditable(true);
        add(tempoLabel);
        add(tempoTField);
        tempoTField.setEditable(true);


        add(repLabel);
        add(repTField);
      //  if()
        repTField.setEditable(true);
        add(recLabel);
        add(recTField);
        recTField.setEditable(true);

        add(whereLabel);
        add(whereTField);
        whereTField.setEditable(true);
        add(addR);
        addR.addActionListener(this);
        
        add(lookUpByDate);
        lookUpByDate.addActionListener(this);
        add(FindAllByDate);
        FindAllByDate.addActionListener(this);
        
        add(find);
        find.addActionListener(this);
        add(outputArea);
        outputArea.setEditable(false);
        add(remove);
        remove.addActionListener(this);
        lookUpByDate.setEnabled(false);
        FindAllByDate.setEnabled(false);
        find.setEnabled(false);
        remove.setEnabled(false);
        setSize(950, 250);
        setVisible(true);
        blankDisplay();
        
        // To save typing in new entries while testing, uncomment
        // the following lines (or add your own test cases)
        
    } // constructor

    // listen for and respond to GUI events 
    public void actionPerformed(ActionEvent event) {
        String message = "";
        if (event.getSource() == addR) {
        	
            message = addEntry();
           System.out.println(myAthletes.getNumberOfEntries());
           if(myAthletes.getNumberOfEntries() > 0)
           {
           lookUpByDate.setEnabled(true);
           FindAllByDate.setEnabled(true);
           find.setEnabled(true);
           remove.setEnabled(true);
           }
        }
        if (event.getSource() == lookUpByDate) {
            message = lookupEntry();
        }
        if (event.getSource()== FindAllByDate)
        {
        	message = "Not implemented yet";
        	message = lookAllEntries();
        }
        if(event.getSource() == find)
        {
        	message = lookAllByName();
        }
        if (event.getSource() == remove) {
        	message = removeEntry();
            if(myAthletes.getNumberOfEntries() <= 0)
            {
            lookUpByDate.setEnabled(false);
            FindAllByDate.setEnabled(false);
            find.setEnabled(false);
            remove.setEnabled(false);
            }
        }
        outputArea.setText(message);
        blankDisplay();
    } // actionPerformed

    public String addEntry() {
        String message = "Record added\n";
        String n = name.getText();
        if(n.equals(""))
        {
        	return "please enter a valid name";
        }
        	try
        	{
			        int m = Integer.parseInt(month.getText());
			        if(m > 12  || m <1)
			        {
			        	return "Please enter a valid number for the month";
			        }
			        int d = Integer.parseInt(day.getText());
			        int y = Integer.parseInt(year.getText());
			        if((m ==2 && y%4 == 0) && (d < 1 || d >29))
			        {
			        	return "This is a leap year, so the day in February should be 1-29";
			        } else if (((m ==2 && y%4 != 0) && (d < 1 || d >28)))
			        {
			        	return "This is not a leap year, so the day in February should be 1-28";
			        } else if ((m == 1 || m == 3 || m == 5 || m == 7 || m == 8 || m == 10 || m == 12 ) && (d < 1 || d > 31))
			        {
			        	return "This month has 31 days, so enter a number from 1 to 31";
			        } else if ((m == 4 || m == 6 || m == 9 || m == 11 ) && (d < 1 || d > 30))
			        {
			        	return "This month has 30 days, so enter a number from 1 to 30";
			        }
			        float km = java.lang.Float.parseFloat(dist.getText());
			        if (km < 0)
			        {
			        	return "Please enter a non-negative number for km";
			        }
			        int h = Integer.parseInt(hours.getText());
			        if (h < 0)
			        {
			        	return "Please enter a non-negative number for hours";
			        }
			        int mm = Integer.parseInt(mins.getText());
			        if (mm < 0)
			        {
			        	return "Please enter a non-negative number for minutes";
			        }
			        int s = Integer.parseInt(secs.getText());
			        if (s < 0)
			        {
			        	return "Please enter a non-negative number for ";
			        }
			       
			        String terrain = terrainTField.getText();
			        String tempo = tempoTField.getText();
			        String where = whereTField.getText();
			        
			        if(!terrain.isEmpty() && !tempo.isEmpty() && where.isEmpty() && repTField.getText().isEmpty() && recTField.getText().isEmpty())
			        {
			        	//if(r)
			        	Entry ce = new CycleEntry(n, d, m, y, h, mm, s, km, terrain, tempo);
			        	{
				        myAthletes.addEntry(ce);
			            message ="Adding Cycle entry to the records";
			        	} 
			        } else if (where.isEmpty() && terrain.isEmpty() && tempo.isEmpty() && !repTField.getText().isEmpty() && !recTField.getText().isEmpty())
			        {
			        	int reps = Integer.parseInt(repTField.getText());
					    int recov = Integer.parseInt(recTField.getText());
					    Entry se = new SprintEntry(n, d, m, y, h, mm, s, km, reps, recov);
				        myAthletes.addEntry(se);
			            message = "Adding Sprint  entry to the records";
				        
			        }
			        else if (!where.isEmpty() && terrain.isEmpty() && tempo.isEmpty() && repTField.getText().isEmpty() && recTField.getText().isEmpty())
			        {
			        	Entry swe = new SwimEntry(n, d, m, y, h, mm, s, km, where);
			        	
				        myAthletes.addEntry(swe);
			            message = "Adding Swim entry to the records";
			        }
			        else if (terrain.isEmpty() && tempo.isEmpty() && where.isEmpty() && repTField.getText().isEmpty() && recTField.getText().isEmpty() )
			        {
				        Entry e = new Entry(n, d, m, y, h, mm, s, km);
				        myAthletes.addEntry(e);
			            message = "Adding generic entry to the records";
			        } else
			        {
			        	return "choose what type of entry you want to add!";
			        }
        	} catch (Exception e)
        	{
        		return "Wrong input";
        	}
        	if(count == myAthletes.getNumberOfEntries())
        	{
                count++;
                System.out.println(message);
                return message;
        	} else
        	{
        		return"Record already added!";
        	}
    }
    
    public String lookupEntry() {
    	try {
        int d = Integer.parseInt(day.getText());
        if(d<1 || d >31)
        {
        	return "Please enter a valid day";
        }
        int m = Integer.parseInt(month.getText());
        if(m > 12  || m <1)
        {
        	return "Enter a valid month";
        }
        int y = Integer.parseInt(year.getText());
        
        outputArea.setText("looking up record ...");
        String message = myAthletes.lookupEntry(d, m, y);
        return message;
    } catch (Exception e)
    	{
    	return "Wrong input";
    	}
    } 
    
    public String lookAllEntries() {
    	try {
        int m = Integer.parseInt(month.getText());
        int d = Integer.parseInt(day.getText());
        int y = Integer.parseInt(year.getText());
        outputArea.setText("looking up record ...");
        String message = myAthletes.lookAllEntries(d, m, y);
        return message;
    	} catch (Exception e)
    	{
    		return	"Error";
    	}
    }
    	
        public String lookAllByName() {
        	try {
            String n = name.getText();
            outputArea.setText("looking up record ...");
            String message = myAthletes.lookAllByName(n);
            return message;
        	} catch (Exception e)
        	{
        		return	"Error";
        	}
    
    }
        
        public String removeEntry ()
        {
        	try {
        	String n = name.getText();
            int d = Integer.parseInt(day.getText());
        	int m = Integer.parseInt(month.getText());
            int y = Integer.parseInt(year.getText());
            String message = myAthletes.removeEntry(n, d, m, y);

            if (message == "Entry has been removed")
        	{
        		count -- ;
        	}
            return message;
        	} catch (Exception e)
        	{
        		return "Wrong input";
        	}
        }
    public void blankDisplay() {
        name.setText("");
        day.setText("");
        month.setText("");
        year.setText("");
        hours.setText("");
        mins.setText("");
        secs.setText("");
        dist.setText("");
        terrainTField.setText("");
        tempoTField.setText("");
        repTField.setText("");
        recTField.setText("");
        whereTField.setText("");

    }// blankDisplay
    // Fills the input fields on the display for testing purposes only
    public void fillDisplay(Entry ent) {
        name.setText(ent.getName());
        day.setText(String.valueOf(ent.getDay()));
        month.setText(String.valueOf(ent.getMonth()));
        year.setText(String.valueOf(ent.getYear()));
        hours.setText(String.valueOf(ent.getHour()));
        mins.setText(String.valueOf(ent.getMin()));
        secs.setText(String.valueOf(ent.getSec()));
        dist.setText(String.valueOf(ent.getDistance()));
    }
    
    public void setEnability ()
    {
    }

} // TrainingRecordGUI

