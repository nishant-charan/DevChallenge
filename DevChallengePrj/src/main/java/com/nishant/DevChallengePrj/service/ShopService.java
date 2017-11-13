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

}
