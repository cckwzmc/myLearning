package com.myfetch.util;

public class StringEncoding {

	private final static int GB2312 = 0;

	private final static int GBK = 1;

	private final static int BIG5 = 2;

	private final static int UTF8 = 3;

	private final static int UNICODE = 4;

	private final static int EUC_KR = 5;

	private final static int SJIS = 6;

	private final static int EUC_JP = 7;

	private final static int ASCII = 8;

	private final static int UNKNOWN = 9;

	private final static int TOTALT = 10;

	private static Encoding[] encodings;

	private int[][] GB2312format;
	private int[][] GBKformat;
	private int[][] Big5format;
	private int[][] EUC_KRformat;
	private int[][] JPformat;

	static {
		initEncodings();
	}

	private static void initEncodings() {

		encodings = new Encoding[TOTALT];

		int i = 0;
		encodings[i++] = new Encoding("GB2312", "GB2312");
		encodings[i++] = new Encoding("GBK", "GBK");
		encodings[i++] = new Encoding("BIG5", "BIG5");
		encodings[i++] = new Encoding("UTF8", "UTF-8");
		encodings[i++] = new Encoding("UNICODE(UTF-16)", "UTF-16");
		encodings[i++] = new Encoding("EUC-KR", "EUC-KR");
		encodings[i++] = new Encoding("Shift-JIS", "Shift_JIS");
		encodings[i++] = new Encoding("EUC-JP", "EUC-JP");
		encodings[i++] = new Encoding("ASCII", "ASCII");
		encodings[i++] = new Encoding("ISO8859-1", "ISO8859-1");

	}

	public StringEncoding() {
		init();
	}

	private void init() {
		GB2312format = new int[94][94];
		GBKformat = new int[126][191];
		Big5format = new int[94][158];
		EUC_KRformat = new int[94][94];
		JPformat = new int[94][94];
	}

	public Encoding getEncoding(final byte[] data) {
		return check(getEncodingValue(data));
	}

	private static Encoding check(final int result) {
		if (result == -1) {
			return encodings[UNKNOWN];
		}
		return encodings[result];

	}

	private int getEncodingValue(byte[] content) {
		if (content == null)
			return -1;
		int[] scores;
		int index, maxscore = 0;
		int encoding = UNKNOWN;
		scores = new int[TOTALT];
		// 分配或然率
		scores[GB2312] = getProbabilityByGB2312Encoding(content);
		scores[GBK] = getProbabilityByGBKEncoding(content);
		scores[BIG5] = getProbabilityByBIG5Encoding(content);
		scores[UTF8] = getProbabilityByUTF8Encoding(content);
		scores[UNICODE] = getProbabilityByUTF16Encoding(content);
		scores[EUC_KR] = getProbabilityByEUC_KREncoding(content);
		scores[ASCII] = getProbabilityByASCIIEncoding(content);
		scores[SJIS] = getProbabilityBySJISEncoding(content);
		scores[EUC_JP] = getProbabilityByEUC_JPEncoding(content);
		scores[UNKNOWN] = 0;

		// 概率比较
		for (index = 0; index < TOTALT; index++) {
			if (scores[index] > maxscore) {
				// 索引
				encoding = index;
				// 最大几率
				maxscore = scores[index];
			}
		}
		// 返回或然率大于50%的数据
		if (maxscore <= 50) {
			encoding = UNKNOWN;
		}
		return encoding;
	}

	/** */
	/**
	 * gb2312数据或然率计算
	 * 
	 * @param content
	 * @return
	 */
	private int getProbabilityByGB2312Encoding(byte[] content) {
		int i, rawtextlen = 0;

		int dbchars = 1, gbchars = 1;
		long gbformat = 0, totalformat = 1;
		float rangeval = 0, formatval = 0;
		int row, column;

		// 检查是否在亚洲汉字范围内
		rawtextlen = content.length;
		for (i = 0; i < rawtextlen - 1; i++) {
			if (content[i] >= 0) {
			} else {
				dbchars++;
				// 汉字GB码由两个字节组成，每个字节的范围是0xA1 ~ 0xFE
				if ((byte) 0xA1 <= content[i] && content[i] <= (byte) 0xF7 && (byte) 0xA1 <= content[i + 1] && content[i + 1] <= (byte) 0xFE) {
					gbchars++;
					totalformat += 500;
					row = content[i] + 256 - 0xA1;
					column = content[i + 1] + 256 - 0xA1;
					if (GB2312format[row][column] != 0) {
						gbformat += GB2312format[row][column];
					} else if (15 <= row && row < 55) {
						// 在gb编码范围
						gbformat += 200;
					}

				}
				i++;
			}
		}
		rangeval = 50 * ((float) gbchars / (float) dbchars);
		formatval = 50 * ((float) gbformat / (float) totalformat);

		return (int) (rangeval + formatval);
	}

	/** */
	/**
	 * gb2312或然率计算
	 * 
	 * @param content
	 * @return
	 */
	private int getProbabilityByGBKEncoding(byte[] content) {
		int i, rawtextlen = 0;

		int dbchars = 1, gbchars = 1;
		long gbformat = 0, totalformat = 1;
		float rangeval = 0, formatval = 0;
		int row, column;
		rawtextlen = content.length;
		for (i = 0; i < rawtextlen - 1; i++) {
			if (content[i] >= 0) {
			} else {
				dbchars++;
				if ((byte) 0xA1 <= content[i] && content[i] <= (byte) 0xF7 && // gb范围
						(byte) 0xA1 <= content[i + 1] && content[i + 1] <= (byte) 0xFE) {
					gbchars++;
					totalformat += 500;
					row = content[i] + 256 - 0xA1;
					column = content[i + 1] + 256 - 0xA1;
					if (GB2312format[row][column] != 0) {
						gbformat += GB2312format[row][column];
					} else if (15 <= row && row < 55) {
						gbformat += 200;
					}

				} else if ((byte) 0x81 <= content[i] && content[i] <= (byte) 0xFE && // gb扩展区域
						(((byte) 0x80 <= content[i + 1] && content[i + 1] <= (byte) 0xFE) || ((byte) 0x40 <= content[i + 1] && content[i + 1] <= (byte) 0x7E))) {
					gbchars++;
					totalformat += 500;
					row = content[i] + 256 - 0x81;
					if (0x40 <= content[i + 1] && content[i + 1] <= 0x7E) {
						column = content[i + 1] - 0x40;
					} else {
						column = content[i + 1] + 256 - 0x40;
					}
					if (GBKformat[row][column] != 0) {
						gbformat += GBKformat[row][column];
					}
				}
				i++;
			}
		}
		rangeval = 50 * ((float) gbchars / (float) dbchars);
		formatval = 50 * ((float) gbformat / (float) totalformat);
		return (int) (rangeval + formatval) - 1;
	}

	/** */
	/**
	 * 解析为big5的或然率
	 * 
	 * @param content
	 * @return
	 */
	private int getProbabilityByBIG5Encoding(byte[] content) {
		int i, rawtextlen = 0;
		int dbchars = 1, bfchars = 1;
		float rangeval = 0, formatval = 0;
		long bfformat = 0, totalformat = 1;
		int row, column;
		rawtextlen = content.length;
		for (i = 0; i < rawtextlen - 1; i++) {
			if (content[i] >= 0) {
			} else {
				dbchars++;
				if ((byte) 0xA1 <= content[i] && content[i] <= (byte) 0xF9 && (((byte) 0x40 <= content[i + 1] && content[i + 1] <= (byte) 0x7E) || ((byte) 0xA1 <= content[i + 1] && content[i + 1] <= (byte) 0xFE))) {
					bfchars++;
					totalformat += 500;
					row = content[i] + 256 - 0xA1;
					if (0x40 <= content[i + 1] && content[i + 1] <= 0x7E) {
						column = content[i + 1] - 0x40;
					} else {
						column = content[i + 1] + 256 - 0x61;
					}
					if (Big5format[row][column] != 0) {
						bfformat += Big5format[row][column];
					} else if (3 <= row && row <= 37) {
						bfformat += 200;
					}
				}
				i++;
			}
		}
		rangeval = 50 * ((float) bfchars / (float) dbchars);
		formatval = 50 * ((float) bfformat / (float) totalformat);

		return (int) (rangeval + formatval);
	}

	/** */
	/**
	 * 在utf-8中的或然率
	 * 
	 * @param content
	 * @return
	 */
	private int getProbabilityByUTF8Encoding(byte[] content) {
		int score = 0;
		int i, rawtextlen = 0;
		int goodbytes = 0, asciibytes = 0;
		// 检查是否为汉字可接受范围
		rawtextlen = content.length;
		for (i = 0; i < rawtextlen; i++) {
			if ((content[i] & (byte) 0x7F) == content[i]) {
				asciibytes++;
			} else if (-64 <= content[i] && content[i] <= -33 && i + 1 < rawtextlen && -128 <= content[i + 1] && content[i + 1] <= -65) {
				goodbytes += 2;
				i++;
			} else if (-32 <= content[i] && content[i] <= -17 && i + 2 < rawtextlen && -128 <= content[i + 1] && content[i + 1] <= -65 && -128 <= content[i + 2] && content[i + 2] <= -65) {
				goodbytes += 3;
				i += 2;
			}
		}

		if (asciibytes == rawtextlen) {
			return 0;
		}

		score = (int) (100 * ((float) goodbytes / (float) (rawtextlen - asciibytes)));
		// 如果不高于98则减少到零
		if (score > 98) {
			return score;
		} else if (score > 95 && goodbytes > 30) {
			return score;
		} else {
			return 0;
		}

	}

	/** */
	/**
	 * 检查为utf-16的或然率
	 * 
	 * @param content
	 * @return
	 */
	private int getProbabilityByUTF16Encoding(byte[] content) {

		if (content.length > 1 && ((byte) 0xFE == content[0] && (byte) 0xFF == content[1]) || ((byte) 0xFF == content[0] && (byte) 0xFE == content[1])) {
			return 100;
		}
		return 0;
	}

	/** */
	/**
	 * 检查为ascii的或然率
	 * 
	 * @param content
	 * @return
	 */
	private static int getProbabilityByASCIIEncoding(byte[] content) {
		int score = 75;
		int i, rawtextlen;

		rawtextlen = content.length;

		for (i = 0; i < rawtextlen; i++) {
			if (content[i] < 0) {
				score = score - 5;
			} else if (content[i] == (byte) 0x1B) { // ESC (used by ISO 2022)
				score = score - 5;
			}
			if (score <= 0) {
				return 0;
			}
		}
		return score;
	}

	/** */
	/**
	 * 检查为euc_kr的或然率
	 * 
	 * @param content
	 * @return
	 */
	private int getProbabilityByEUC_KREncoding(byte[] content) {
		int i, rawtextlen = 0;

		int dbchars = 1, krchars = 1;
		long krformat = 0, totalformat = 1;
		float rangeval = 0, formatval = 0;
		int row, column;
		rawtextlen = content.length;
		for (i = 0; i < rawtextlen - 1; i++) {
			if (content[i] >= 0) {
			} else {
				dbchars++;
				if ((byte) 0xA1 <= content[i] && content[i] <= (byte) 0xFE && (byte) 0xA1 <= content[i + 1] && content[i + 1] <= (byte) 0xFE) {
					krchars++;
					totalformat += 500;
					row = content[i] + 256 - 0xA1;
					column = content[i + 1] + 256 - 0xA1;
					if (EUC_KRformat[row][column] != 0) {
						krformat += EUC_KRformat[row][column];
					} else if (15 <= row && row < 55) {
						krformat += 0;
					}

				}
				i++;
			}
		}
		rangeval = 50 * ((float) krchars / (float) dbchars);
		formatval = 50 * ((float) krformat / (float) totalformat);

		return (int) (rangeval + formatval);
	}

	private int getProbabilityByEUC_JPEncoding(byte[] content) {
		int i, rawtextlen = 0;

		int dbchars = 1, jpchars = 1;
		long jpformat = 0, totalformat = 1;
		float rangeval = 0, formatval = 0;
		int row, column;

		rawtextlen = content.length;
		for (i = 0; i < rawtextlen - 1; i++) {
			if (content[i] >= 0) {
			} else {
				dbchars++;
				if ((byte) 0xA1 <= content[i] && content[i] <= (byte) 0xFE && (byte) 0xA1 <= content[i + 1] && content[i + 1] <= (byte) 0xFE) {
					jpchars++;
					totalformat += 500;
					row = content[i] + 256 - 0xA1;
					column = content[i + 1] + 256 - 0xA1;
					if (JPformat[row][column] != 0) {
						jpformat += JPformat[row][column];
					} else if (15 <= row && row < 55) {
						jpformat += 0;
					}

				}
				i++;
			}
		}
		rangeval = 50 * ((float) jpchars / (float) dbchars);
		formatval = 50 * ((float) jpformat / (float) totalformat);

		return (int) (rangeval + formatval);
	}

	private int getProbabilityBySJISEncoding(byte[] content) {
		int i, rawtextlen = 0;

		int dbchars = 1, jpchars = 1;
		long jpformat = 0, totalformat = 1;
		float rangeval = 0, formatval = 0;
		int row, column, adjust;

		rawtextlen = content.length;
		for (i = 0; i < rawtextlen - 1; i++) {
			if (content[i] >= 0) {
			} else {
				dbchars++;
				if (i + 1 < content.length && (((byte) 0x81 <= content[i] && content[i] <= (byte) 0x9F) || ((byte) 0xE0 <= content[i] && content[i] <= (byte) 0xEF))
						&& (((byte) 0x40 <= content[i + 1] && content[i + 1] <= (byte) 0x7E) || ((byte) 0x80 <= content[i + 1] && content[i + 1] <= (byte) 0xFC))) {
					jpchars++;
					totalformat += 500;
					row = content[i] + 256;
					column = content[i + 1] + 256;
					if (column < 0x9f) {
						adjust = 1;
						if (column > 0x7f) {
							column -= 0x20;
						} else {
							column -= 0x19;
						}
					} else {
						adjust = 0;
						column -= 0x7e;
					}
					if (row < 0xa0) {
						row = ((row - 0x70) << 1) - adjust;
					} else {
						row = ((row - 0xb0) << 1) - adjust;
					}

					row -= 0x20;
					column = 0x20;
					if (row < JPformat.length && column < JPformat[row].length && JPformat[row][column] != 0) {
						jpformat += JPformat[row][column];
					}
					i++;
				} else if ((byte) 0xA1 <= content[i] && content[i] <= (byte) 0xDF) {
				}

			}
		}
		rangeval = 50 * ((float) jpchars / (float) dbchars);
		formatval = 50 * ((float) jpformat / (float) totalformat);

		return (int) (rangeval + formatval) - 1;
	}

}