package com.lyxmq.novel.ui.struts2.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * A utilities class used by the web UI.
 */
public final class UIUtils {

	private static final List locales;
	private static final List timeZones;

	// load up the locales and time zones lists
	static {
		// build locales list
		locales = Arrays.asList(Locale.getAvailableLocales());
		Collections.sort(locales, new LocaleComparator());

		// build time zones list
		timeZones = Arrays.asList(TimeZone.getAvailableIDs());
		Collections.sort(timeZones);
	}

	public static List getLocales() {
		return locales;
	}

	public static List getTimeZones() {
		return timeZones;
	}

	// special comparator for sorting locales
	private static final class LocaleComparator implements Comparator {
		public int compare(Object obj1, Object obj2) {
			if (obj1 instanceof Locale && obj2 instanceof Locale) {
				Locale locale1 = (Locale) obj1;
				Locale locale2 = (Locale) obj2;
				int compName = locale1.getDisplayName().compareTo(
						locale2.getDisplayName());
				if (compName == 0) {
					return locale1.toString().compareTo(locale2.toString());
				}
				return compName;
			}
			return 0;
		}
	}

}
