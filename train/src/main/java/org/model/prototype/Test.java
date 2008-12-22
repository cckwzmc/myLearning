package org.model.prototype;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test {
	private static Logger logger = LoggerFactory.getLogger(Test.class);

	public static void main(String[] args) {
		try {
			Prototype aPrototype = new PrototypeImplA();
			aPrototype.setPId("pid");
			logger.info(aPrototype.getPId());
			logger.info(aPrototype.sId);
			// Prototype proa=aPrototype.clone();
			// logger.info(proa.getPId());
			PrototypeImplA proA = new PrototypeImplA();
			proA.setPId("proA ");
			logger.info(proA.getPId());
			logger.info(proA.sId);
//			PrototypeImplA proB = proA.clone();
//			logger.info(proB.getPId());
//			logger.info(proB.sId);
			PrototypeClone clone=new PrototypeClone();
			clone.setPrototype(new String[]{"a","prototype prototype"});
			logger.info(clone.getPrototype()[0]);
			PrototypeClone cloneA=clone.clone();
			logger.info(cloneA.getPrototype()[0]);
			cloneA.setPrototype(new String[]{"b","prototype reset"});
			logger.info(cloneA.getPrototype()[0]);
			logger.info(clone.getPrototype()[0]);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
