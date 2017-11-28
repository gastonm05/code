/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wheresmybus;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class XLSParser {

	private String inputFile;
        ArrayList <Integer>  numbers;
	String[][] fullData;
        String[] directionList;
	String[] stopList;
	String[] timesByStop;
	String stopName;
	String closestTime;
	String nextClosestTime;
	String directionName;
        int directionNumber;
	int horaActual;
	int stopNumber;
	int stopsRow = 0;
	int line;
        int idx;
	int idx3;

	public void SetLine(int line) {
		this.line = line;
	}

	public void SetDirectionName(String directionName) {
		this.directionName = directionName;

	}

	public void SetStopName(String stopName) {
		this.stopName = stopName;
	}

	public void SetInputFile() {
		String fileName = "Excels/" + line + "-HABILES" + ".xls";

		inputFile = fileName;
	}

	public void read() throws IOException {

		File inputWorkbook = new File(inputFile);
		Workbook w;

		try {
			w = Workbook.getWorkbook(inputWorkbook);
			// Get the first sheet

			Sheet sheet = w.getSheet(0);
			int total = sheet.getColumns();
			int half = total / 2;
			int from = 0;
			int until = half;

			if (directionNumber == 2) {
				from = half;
				until = total;
			}
			if (total % 2 != 0) {
				half++;
			}
			fullData = new String[half][sheet.getRows()];
			// Loop over first half of records

			for (int j = from, col = 0; j < until; j++, col++) {
				for (int i = 0; i < sheet.getRows(); i++) {
					Cell cell = sheet.getCell(j, i);
					fullData[col][i] = cell.getContents();

				}
			}
		} catch (BiffException e) {
		}

	}

	public void read2() throws IOException {


                        int totalRows = fullData[0].length;
			timesByStop = new String[totalRows];

			for (int i = 0; i < totalRows; i++) {
				timesByStop[i] = fullData[stopNumber][i];
			}
	}

	// Get a Row from a 2-D array
	public void GetStopList() {
		stopList = Arrays.stream(fullData).map(o -> o[stopsRow]).toArray(String[]::new);
	}

	public void SetStopNumber() {

		int n = 0;
		for (int i = 0; i < stopList.length; i++) {
			if (stopList[i].contains(stopName)) {
				n = i;
				break;
			}
		}
		stopNumber = n;
	}
        
        public void SetDirectionNumber() {

		int n = 0;
		for (int i = 0; i < directionList.length; i++) {
			if (directionList[i].contains(directionName)) {
				n = i;
				break;
			}
		}
		directionNumber = n;
	}

	// iterate each value
	// within convert each value to minutes
	// compare if they are the closest
	public void GetClosestTime() {
                //int horaActual2 = 1435;
		int distance = horaActual;
		int idx = 0;
                
                numbers = new ArrayList <> ();


            for (String str : timesByStop) {
                if (str.matches("\\d{2}:\\d{2}") || str.matches("\\d{1}:\\d{2}"))
                {
                    numbers.add(ConvertTime(str));
                }
            }
                Collections.sort(numbers);
                int i = 0;
                while(numbers.get(i) < horaActual)
                {
                    i++;
                    idx = i;
                }

                    
		idx3 = idx;
		int t = numbers.get(idx);
                closestTime = ConvertMinsToHour(t);

	}
        
        public String ConvertMinsToHour(int minutes){
        String startTime = "00:00";
        int h = minutes / 60 + Integer.parseInt(startTime.substring(0,1));
        int m = minutes % 60 + Integer.parseInt(startTime.substring(3,4));

        String mins = String.format("%02d", m);
        String hours = String.format("%02d", h);

        String newtime = hours+":"+mins;
        return newtime;
        }
        
        

	public void GetNextClosestTime() {
                String str ;
                    if ( idx3 == numbers.size()){
                        str = ConvertMinsToHour(numbers.get(0));
			
                    }
                    else{
                        str = ConvertMinsToHour(numbers.get(idx3+1));   
		}
		nextClosestTime = str;
	}

	public void GetCurrentTime() {
		Date date = new Date(); // given date
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(date); // assigns calendar to given date
		int horas = calendar.get(Calendar.HOUR_OF_DAY);
		int minutos = calendar.get(Calendar.MINUTE);
		horaActual = horas * 60 + minutos;
	}

	public int ConvertTime(String timeToConvert) {
		String[] hourMin = timeToConvert.split(":");
		int hour = Integer.parseInt(hourMin[0]);
		int mins = Integer.parseInt(hourMin[1]);
		int hoursInMins = hour * 60;

		return hoursInMins + mins;
	}

	public String NextBusDiff() {
		int next = ConvertTime(closestTime);
		int mins = next - horaActual;
		String str = "Proximo colectivo en " + mins + " minutos, a las:";
		if (mins > 59) {
			int hours = mins / 60;
			int minutes = mins % 60;
			str = "Proximo colectivo en " + hours + " hora/s y " + minutes + " minuto/s, a las:";
		}
		return str;
	}

	public String UpcomingBusTime() {
            
		//String str = closestTime;
		return closestTime;
	}

	public String NextBusTime() {
		String str = "El siguiente colectivo llegara a las: " + nextClosestTime;

		return str;
	}

	public void read3() throws IOException {

		File inputWorkbook = new File(inputFile);
		Workbook w;

		try {
			w = Workbook.getWorkbook(inputWorkbook);
			// Get the first sheet

			Sheet sheet = w.getSheet(0);
			int total = sheet.getColumns();
			int half = total / 2;

			directionList = new String[3];
                        directionList[0]= "Direccion";

			for (int i = 0, f = 1; i < sheet.getColumns(); i++) {
				Cell cell = sheet.getCell(i, 0);
                                if (i == half-2 ^ i == total-2){
                		directionList[f] = cell.getContents(); 
                                f++;
                                }
			}
		} catch (BiffException e) {
		}

	}
}
