package tools;

public class ParseCategoryCode {

	public ParseCategoryCode() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * return largeCat, mediumCat, smallCat information from the code provided.
	 * @param code
	 * @return A String array which consists of largeCat, mediumCat and smallCat. 
	 */
	public static String[] parseCateCode(String code){
		if(code.length() == 5){
			code = "0"+code;
		}
		if(code.length() == 6){
			return new String[]{code.substring(0, 2),code.substring(2,4),code};
		}
		
		return null;
	}

}
