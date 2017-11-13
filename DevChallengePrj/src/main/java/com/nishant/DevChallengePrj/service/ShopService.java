package com.nishant.DevChallengePrj.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.nishant.DevChallengePrj.model.ShopDetails;

@Service("shopService")
public class ShopService {
	
	private static String GOOGLE_API_KEY = "AIzaSyD57d-cqEIwY9j9IL7j3A4lqsx3d_wUpGg";
	
	public List<ShopDetails> shops = new ArrayList<>();

	public ShopDetails addShopDetails(ShopDetails shopDetails) {
		shopDetails = addGeoCodes(shopDetails);
		shops.add(shopDetails);
		return shopDetails;
	}
	
	//Adding Latitude and Longitude to model according to provided postalCode
	private ShopDetails addGeoCodes(ShopDetails shopDetails) {
		try {
			GeoApiContext context = new GeoApiContext().setApiKey(GOOGLE_API_KEY);
			GeocodingResult[] results = GeocodingApi.geocode(context, shopDetails.getShopAddress().getPostCode()).await();
			System.out.println("Geocoding Result : " + results);
			if (null != results && results.length > 0) {
				GeocodingResult geocodingResult = results[0];
				shopDetails.setShopLatitude(String.valueOf(geocodingResult.geometry.location.lat));
				shopDetails.setShopLongitude(String.valueOf(geocodingResult.geometry.location.lng));
				System.out.println("Geocoding Latitude  : " + String.valueOf(geocodingResult.geometry.location.lat));
				System.out.println("Geocoding Longitude : " + String.valueOf(geocodingResult.geometry.location.lng));
			}
		} catch (Exception e) {
			System.out.println("Error invoking geocode for " + shopDetails.getShopAddress().getPostCode());
		}
		return shopDetails;
	}
	
	public ShopDetails retrieveNearestShop(String latitude, String longitude) {
		ShopDetails nearestShopDetails = null;
		Double nearDistance = null;
		for (ShopDetails shopDetails : shops) {
			Double distance = getDistance(shopDetails.getShopLatitude(), shopDetails.getShopLongitude(), latitude, longitude);
			if (nearDistance == null || (distance < nearDistance)) {
				nearDistance = distance;
				nearestShopDetails = shopDetails;
			} 
		}
		return nearestShopDetails;
	}

	//Code to calculate distance between given two locations
	private static Double getDistance(String shopLat, String shopLon, String enteredLat, String enteredLon) {
		double lat1 = Double.parseDouble(shopLat);
		double lon1 = Double.parseDouble(shopLon);
		double lat2 = Double.parseDouble(enteredLat);
		double lon2 = Double.parseDouble(enteredLon);
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515 * 1.609344;
		System.out.println(dist);
		return dist;
	}

	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	/*::	This function converts decimal degrees to radians						 :*/
	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	/*::	This function converts radians to decimal degrees						 :*/
	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	private static double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}

}
