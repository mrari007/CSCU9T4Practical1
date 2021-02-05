// An implementation of a Training Record as an ArrayList
package com.stir.cscu9t4practical1;




import java.util.*;


public class TrainingRecord {
    private List<Entry> tr;
    public TrainingRecord() {
        tr = new ArrayList<Entry>();
    } //constructor
    
    // add a record to the list
   public void addEntry(Entry e){
	  // if(e.getNameDayMoYe() == lookupEntryName (e.getName(), e.getDay(), e.getMonth(), e.getYear()))
	   for (int i = 0; i<tr.size(); i++)
	   {
		    Entry count = tr.get(i);
		    if(count.getName().equalsIgnoreCase(e.getName()) && count.getDay() == e.getDay() && count.getMonth() == e.getMonth() && count.getYear() == e.getYear())
		    {
		    	System.out.println("Record already added!");
		    	return;
		    }
	   }
       tr.add(e);    
   } // addClass
   
   // look up the entry of a given day and month
   
   public String lookupEntry (int d, int m, int y) {
       ListIterator<Entry> iter = tr.listIterator();
       String result = "No entries found";
       while (iter.hasNext()) {
          Entry current = iter.next();
          if (current.getDay()==d && current.getMonth()==m && current.getYear()==y) 
             result = current.getEntry();
            }
       return result;
   } // lookupEntry
   public String lookAllEntries (int d, int m, int y)
   {
	    ListIterator<Entry> iter = tr.listIterator();
	       String result = "No entries found";
	       int count = 0;
	       while (iter.hasNext()) {
	          Entry current = iter.next();
	          if (current.getDay()==d && current.getMonth()==m && current.getYear()==y) 
	          {
	        	  if(count ==0)
	        	  {
			             result = current.getEntry();
	        	  }
	        	  else
	        	  {
	        		  result = result +  current.getEntry();
	        	  }
	        	  count++;
	          }
	            }
	       return result;
   }
   
   public String lookAllByName (String n)
   {
	    ListIterator<Entry> iter = tr.listIterator();
	       String result = "No entries found";
	       int count = 0;
	       while (iter.hasNext()) {
	          Entry current = iter.next();
	          if (n.equalsIgnoreCase(current.getName()))
	          {
	        	  if(count ==0)
	        	  {
			             result = current.getEntry();
	        	  }
	        	  else
	        	  {
	        		  result = result +  current.getEntry();
	        	  }
	        	  count++;
	          }
	            }
	       return result;
   }
   // Count the number of entries
   public int getNumberOfEntries(){
       return tr.size();
   }
   // Clear all entries
   public void clearAllEntries(){
       tr.clear();
   }
   
} // TrainingRecord