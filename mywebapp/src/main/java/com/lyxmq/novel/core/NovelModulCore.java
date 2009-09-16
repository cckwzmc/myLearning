package com.lyxmq.novel.core;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class NovelModulCore {
	/** The static log object for this class. */
	private static final Log Log = LogFactory.getLog(NovelModulCore.class);
	 // our configured weblogger provider
    private static NovelProvider novelProvider = null;
    
    
    // non-instantiable
    private NovelModulCore() {
        // hello all you beautiful people
    }
    
}
