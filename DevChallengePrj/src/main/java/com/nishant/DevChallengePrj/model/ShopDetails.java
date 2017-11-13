package com.nishant.DevChallengePrj.model;

public class ShopDetails {

	private String shopName;
	private ShopAddress shopAddress;
	private String shopLongitude;
	private String shopLatitude;

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public ShopAddress getShopAddress() {
		return shopAddress;
	}

	public void setShopAddress(ShopAddress shopAddress) {
		this.shopAddress = shopAddress;
	}

	public String getShopLongitude() {
		return shopLongitude;
	}

	public void setShopLongitude(String shopLongitude) {
		this.shopLongitude = shopLongitude;
	}

	public String getShopLatitude() {
		return shopLatitude;
	}

	public void setShopLatitude(String shopLatitude) {
		this.shopLatitude = shopLatitude;
	}
	
	public ShopDetails(String shopName, ShopAddress shopAddress, String shopLatitude, String shopLongitude) {
		super();
		this.shopName = shopName;
		this.shopAddress = shopAddress;
		this.shopLatitude = shopLatitude;
		this.shopLongitude = shopLongitude;
	}
	
	public ShopDetails() {
		
	}
	
	@Override
	public String toString() {
		return String.format(
				"ShopDetails [shopName=%s, shopAddress=%s, shopLongitude=%s, shopLatitude=%s]",
				shopName, shopAddress, shopLongitude, shopLatitude);
	}

}