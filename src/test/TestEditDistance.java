package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import process.DamerauLevensheinDistance;
import process.EditDistance;

public class TestEditDistance {
	
	String str1 = "nihao";
	String str2 = "nihaonn";
	ArrayList<String> chain1 = new ArrayList<>();
	ArrayList<String> chain2 = new ArrayList<>();
	
	@Before
	public void init(){
		chain1.add("黄石");
		chain1.add("武汉");
		chain1.add("黄冈");
		
		chain2.add("黄冈");
		chain2.add("武汉");
		chain2.add("黄石");
	}

	@Test
	public void testEditDistDP4Chks() {
		EditDistance ed = new EditDistance();
		int distance = ed.EditDistDP4Chks(chain1, chain1, chain1.size(), chain1.size());
		System.out.println("edit distance: "+distance);
		//Assert.assertEquals(distance, 1);
		
	}

	
	@Test
	public void testDLDistance(){
		DamerauLevensheinDistance dldis = new DamerauLevensheinDistance(1.0f, 1f, 1f, 1f);
		float dlDistance = dldis.executeTerms(chain1, chain1);
		System.out.println("DL distance: " + dlDistance);
	}

}
