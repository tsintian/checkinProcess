package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import tools.ParseCategoryCode;

public class TestParseCategoryCode {
	private String code = "050111";

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testParseCateCode() {
		String[] results = ParseCategoryCode.parseCateCode(code);
		System.out.println("large cate: " + results[0]);
		System.out.println("medium cate: " + results[1]);
		System.out.println("small cate: " + results[2]);
	}

}
