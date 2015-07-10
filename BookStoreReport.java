package bookStoreReport;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class BookStoreReport {
	private	Object[] reportObj;
	private String reportAddress, reportName;
	private TreeMap<String, ArrayList<String>> reportMap = new TreeMap<String, ArrayList<String>>();
	
	BookStoreReport(String name, String address, Object[] obj){
		reportName = name;
		reportAddress = address;
		reportObj = obj;
		try {
			readFileToMap();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Object[] getReportObj(){
		return reportObj;
	}
	
	public String getReportAddress(){
		return reportAddress;
	}
	
	public String getReportName(){
		return reportName;
	}
	
	public TreeMap<String,ArrayList<String>> getReportMap(){
		return reportMap;
	}
	
	public void writeLineToFile(String tempString)throws FileNotFoundException, IOException{
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File(reportAddress), true));
		bw.write(tempString);
		bw.newLine();
		bw.close();
	}
	
	public void writeLineToFile(String tempString, String address)throws FileNotFoundException, IOException{
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File(address), true));
		bw.write(tempString);
		bw.newLine();
		bw.close();
	}
	
	public void writeMapToFile()throws FileNotFoundException, IOException{
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File(reportAddress), false));
		bw.close();
		for(Map.Entry<String, ArrayList<String>> entry : reportMap.entrySet()){
			String tempString = entry.getKey();
	    	for(String i:entry.getValue()){
	    		tempString = tempString + "," + i;
	    	}
	    	writeLineToFile(tempString);
		}
	}
		
	public void writeMapToFile(String address)throws FileNotFoundException, IOException{
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File(address), false));
		bw.close();
		for(Map.Entry<String, ArrayList<String>> entry : reportMap.entrySet()){
			String tempString = entry.getKey();
	    	for(String i:entry.getValue()){
	    		tempString = tempString + "," + i;
	    	}
	    	writeLineToFile(tempString, address);
		}
	}
	
	public void readFileToMap()throws FileNotFoundException, IOException{
		BufferedReader buffer = new BufferedReader(new FileReader(reportAddress));
		String tempLine;
		for(int i = 0; (tempLine = buffer.readLine()) != null; i++){
			String[] column = tempLine.split(",");
			ArrayList<String> tempArrayList = new ArrayList<String>();
			Collections.addAll(tempArrayList, Arrays.copyOfRange(column, 1, column.length));
			addEntry(reportMap, column[0], tempArrayList);
		}
		buffer.close();
	}
	
	public void readFileToMap(String tempFile, TreeMap tempMap)throws FileNotFoundException, IOException{
		BufferedReader buffer = new BufferedReader(new FileReader(tempFile));
		String tempLine;
		for(int i = 0; (tempLine = buffer.readLine()) != null; i++){
			String[] column = tempLine.split(",");
			ArrayList<String> tempArrayList = new ArrayList<String>();
			Collections.addAll(tempArrayList, Arrays.copyOfRange(column, 1, column.length));
			addEntry(tempMap, column[0], tempArrayList);
		}
		buffer.close();
	}
	
	private void addEntry(TreeMap tempMap, String tempKey, ArrayList tempArrayList){
		if(hasDuplicateKey(tempKey, tempMap) == true){
			
		}
		else{
			tempMap.put(tempKey, tempArrayList);	
		}
	}
	
	private boolean hasDuplicateKey(String tempString, TreeMap<String, ArrayList<String>> tempMap){
		boolean hasDuplicate = false;
		for(Map.Entry<String, ArrayList<String>> entry : tempMap.entrySet()){
			if(entry.getKey().equalsIgnoreCase(tempString)){
				hasDuplicate = true;
				break;
			}
		}
		return hasDuplicate;
	}
	
}
