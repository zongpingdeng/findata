package com.cimc.datahub.mining;

public class PositionUtils {

	private static final double EARTH_RADIUS = 6371.004;
	static double pi = 3.14159265358979324;
	static double a = 6378245.0;
	static double ee = 0.00669342162296594323;
	static double x_pi = 3.14159265358979324 * 3000.0 / 180.0;

	/**
	 * 将一个点坐标转换为百度坐标
	 * 
	 * @param point
	 */
	public static double[] transBMapPosition(double wgLat, double wgLon) {
		if (wgLat == 0 || wgLon == 0)
			return null;
		double[] MarsGPS = transform(wgLat, wgLon);
		double x = MarsGPS[1], y = MarsGPS[0];
		double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * x_pi);
		double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * x_pi);
		MarsGPS[0] = z * Math.sin(theta) + 0.0060068;
		MarsGPS[1] = z * Math.cos(theta) + 0.0065054;
		return MarsGPS;
	}

	// World Geodetic System ==> Mars Geodetic System
	private static double[] transform(double wgLat, double wgLon) {
		double[] ret = new double[2];
		ret[0] = wgLat;
		ret[1] = wgLon;
		double mgLat, mgLon;
		if (outOfChina(wgLat, wgLon)) {
			mgLat = wgLat;
			mgLon = wgLon;
			ret[0] = mgLat;
			ret[1] = mgLon;
			return ret;
		}
		double dLat = transformLat(wgLon - 105.0, wgLat - 35.0);
		double dLon = transformLon(wgLon - 105.0, wgLat - 35.0);
		double radLat = wgLat / 180.0 * pi;
		double magic = Math.sin(radLat);
		magic = 1 - ee * magic * magic;
		double sqrtMagic = Math.sqrt(magic);
		dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * pi);
		dLon = (dLon * 180.0) / (a / sqrtMagic * Math.cos(radLat) * pi);
		mgLat = wgLat + dLat;
		mgLon = wgLon + dLon;

		ret[0] = mgLat;
		ret[1] = mgLon;
		return ret;
	}

	private static boolean outOfChina(double lat, double lon) {
		if (lon < 72.004 || lon > 137.8347)
			return true;
		if (lat < 0.8293 || lat > 55.8271)
			return true;
		return false;
	}

	private static double transformLat(double x, double y) {
		double ret = -100.0 + 2.0 * x + 3.0 * y + 0.2 * y * y + 0.1 * x * y + 0.2 * Math.sqrt(Math.abs(x));
		ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;
		ret += (20.0 * Math.sin(y * pi) + 40.0 * Math.sin(y / 3.0 * pi)) * 2.0 / 3.0;
		ret += (160.0 * Math.sin(y / 12.0 * pi) + 320 * Math.sin(y * pi / 30.0)) * 2.0 / 3.0;
		return ret;
	}

	private static double transformLon(double x, double y) {
		double ret = 300.0 + x + 2.0 * y + 0.1 * x * x + 0.1 * x * y + 0.1 * Math.sqrt(Math.abs(x));
		ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;
		ret += (20.0 * Math.sin(x * pi) + 40.0 * Math.sin(x / 3.0 * pi)) * 2.0 / 3.0;
		ret += (150.0 * Math.sin(x / 12.0 * pi) + 300.0 * Math.sin(x / 30.0 * pi)) * 2.0 / 3.0;
		return ret;
	}

	/**
	 * @param lat1
	 *            纬度
	 * @param lng1
	 *            经度
	 * @param lat2
	 *            纬度
	 * @param lng2
	 *            经度
	 * @return
	 */
	public static double getDistance(double lat1, double lng1, double lat2, double lng2) {
		double radLat1 = lat1;
		double radLat2 = lat2;
		double a = lng1 - lng2;

		double s = Math.acos(Math.cos(radLat1) * Math.cos(radLat2) * Math.cos(a) + Math.sin(radLat1) * Math.sin(radLat2));
		s = s * EARTH_RADIUS * 1000 * Math.PI / 180.0;
		return s;
	}

	/**
	 * @param lat_a
	 *            维度
	 * @param lng_a
	 *            经度
	 * @param lat_b
	 * @param lng_b
	 * @return
	 */
	private static double gps2m(double lat_a, double lng_a, double lat_b, double lng_b) {

		double pk = (double) (180 / 3.14169);

		double a1 = lat_a / pk;

		double a2 = lng_a / pk;

		double b1 = lat_b / pk;

		double b2 = lng_b / pk;

		double t1 = Math.cos(a1) * Math.cos(a2) * Math.cos(b1) * Math.cos(b2);

		double t2 = Math.cos(a1) * Math.sin(a2) * Math.cos(b1) * Math.sin(b2);

		double t3 = Math.sin(a1) * Math.sin(b1);

		double tt = Math.acos(t1 + t2 + t3);

		return 6366000 * tt;

	}

	/**
	 * 每个点元素为经度，纬度
	 * 
	 * @param node1
	 * @param node2
	 * @return
	 */
	public static double getDistince(double[] node1, double[] node2) {
		return getDistance(node1[1], node1[0], node2[1], node2[0]);
	}

	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}

	public static double GetDistance(double lat1, double lng1, double lat2, double lng2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS * 1000;
		// s = Math.round(s * 10000) / 10000;
		return s;
	}

	public static void main(String[] args) {
		double distance = getDistance(36.74114227294922, 119.14092225294847, 36.70890173661081, 119.12844896818463);
		System.out.println(distance);
		System.out.println(gps2m(36.74114227294922, 119.14092225294847, 36.70890173661081, 119.12844896818463));
		System.out.println(GetDistance(36.74114227294922, 119.14092225294847, 36.70890173661081, 119.12844896818463));
		double[] rs = transBMapPosition(36.699466705322266, 119.0972188313802);
		System.out.println("百度坐标:" + rs[1] + "," + rs[0]);
	}

}
