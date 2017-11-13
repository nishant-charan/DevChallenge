package com.nishant.DevChallengePrj;

import org.junit.Assert;
import org.junit.Test;

import com.nishant.DevChallengePrj.model.ShopAddress;
import com.nishant.DevChallengePrj.model.ShopDetails;
import com.nishant.DevChallengePrj.service.ShopService;


public class DevChallengePrjApplicationTests {
	
	private final ShopService shopService = new ShopService();
	
	private void setUp() {
		ShopAddress shopAddress1 = new ShopAddress("1", "411014");
		ShopAddress shopAddress2 = new ShopAddress("2", "411028");
		ShopDetails shopDetails1 = new ShopDetails("Test1", shopAddress1, "18.5574028", "73.92830049999999");
		ShopDetails shopDetails2 = new ShopDetails("Test2", shopAddress2, "18.5149325", "73.9261587");
		shopService.shops.add(shopDetails1);
		shopService.shops.add(shopDetails2);
	}
	
	@Test
	public void testAddNewShop() {
		setUp();
		Integer shopsSize = shopService.shops.size();
		ShopAddress tobeAddedAddress = new ShopAddress();
		tobeAddedAddress.setNumber("3");
		tobeAddedAddress.setPostCode("411048");
		ShopDetails tobeAddedShop = new ShopDetails();
		tobeAddedShop.setShopName("Test3");
		tobeAddedShop.setShopAddress(tobeAddedAddress);
		ShopDetails newAddedShop = shopService.addShopDetails(tobeAddedShop);
		Assert.assertTrue(shopService.shops.contains(newAddedShop));
		Assert.assertEquals("Both Size should be equal", shopsSize + 1, shopService.shops.size());
	}
	
}
