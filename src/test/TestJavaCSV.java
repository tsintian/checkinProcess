package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;

import com.csvreader.CsvReader;

public class TestJavaCSV {
	
	CsvReader csvReader = null;

	public TestJavaCSV() throws FileNotFoundException {
		FileInputStream stream = new FileInputStream("/home/tianqin/projects/checkins/notes/ClassCodeTable.csv");
		Charset charsetUtf8 = Charset.forName("UTF-8");
		this.csvReader = new CsvReader(stream,charsetUtf8);
	}
	
	
	public void readcsv() throws IOException{
		this.csvReader.readHeaders();
		int i=0;
		while(this.csvReader.readRecord()){
			System.out.println("record: " + ++i + this.csvReader.get(0)+", "+this.csvReader.get(1)+", " +this.csvReader.get(2));
		}
	}

	public static void main(String[] args) throws IOException{
		TestJavaCSV tjc = new TestJavaCSV();
		tjc.readcsv();
		
	}

}
