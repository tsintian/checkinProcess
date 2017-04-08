package tools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.csvreader.CsvReader;

import db.connection.DBManager;

public class UpdatePoiClassCode {
	
	

	private CsvReader csvReader = null;
	private String tableName= "poi_class_code";
	


	public UpdatePoiClassCode(String filePath) {
		FileInputStream stream = null;
		//  /home/tianqin/projects/checkins/notes/ClassCodeTable.csv
		try {
			stream = new FileInputStream(filePath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Charset charsetUtf8 = Charset.forName("UTF-8");
		this.csvReader = new CsvReader(stream,charsetUtf8);
	}
	
	public void update() throws IOException, SQLException{
		this.csvReader.readHeaders();
		
		String insertSQL = "INSERT INTO "+tableName+" (id, poi_class, code) VALUES (?,?,?)";
		Connection conn = DBManager.getConnection();
		PreparedStatement ps = conn.prepareStatement(insertSQL);
		
		while(this.csvReader.readRecord()){
			ps.setInt(1, Integer.parseInt(this.csvReader.get("id")));
			ps.setString(2, this.csvReader.get("poi_class"));
			ps.setString(3, this.csvReader.get("code"));
			ps.execute();
		}
		//conn.commit();
		if(ps != null){
			ps.close();
		}
		if(conn != null){
			conn.close();
		}
		
		
	}
	
	
	public void close(){
		this.csvReader.close();
	}
	

	public static void main(String[] args) throws IOException, SQLException {
		String path="/home/tianqin/projects/checkins/notes/ClassCodeTable.csv";
		UpdatePoiClassCode upc = new UpdatePoiClassCode(path);
		upc.update();
		upc.close();
		System.out.println("the program has finished the updating");

	}

}
