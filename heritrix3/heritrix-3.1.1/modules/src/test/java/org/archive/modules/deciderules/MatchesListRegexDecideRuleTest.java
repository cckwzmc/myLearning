package org.archive.modules.deciderules;

import java.util.regex.Pattern;

import org.archive.state.ModuleTestBase;

public class MatchesListRegexDecideRuleTest extends ModuleTestBase {
	public void testEvaluate(){
		String t="http://www.meishichina.com/aabb/ccdd/a.html";
		String regx="^http://([a-z0-9]*\\.)*meishichina.com/(.)*";
		Pattern p=Pattern.compile(regx);
		boolean matches = p.matcher(t).matches();
		assertEquals(true, matches);
	}
}
