package wf.practice5_bintang.general.domain.util;

import java.math.BigDecimal;
import java.sql.Date;

public class DbValueUtils {
	
	public static BigDecimal parseBigDecimal(String val) {
		if (val == null || val.trim().isEmpty()) {
			return null;
		}
		try {
			return new BigDecimal(val.replaceAll(",", "").trim());
		} catch (Exception e) {
			return null;
		}
	}

	public static Date parseSqlDate(String dateStr) {
		if (dateStr == null || dateStr.trim().isEmpty()) {
			return null;
		}
		try {
			String cleanDate = dateStr.split(" ")[0];
			String normalized = cleanDate.replace('/', '-');
			return Date.valueOf(normalized);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Integer parseInteger(String val) {
	    if (val == null || val.trim().isEmpty()) {
	        return null;
	    }
	    try {
	        return Integer.valueOf(val.trim());
	    } catch (Exception e) {
	        return null;
	    }
	}

}
