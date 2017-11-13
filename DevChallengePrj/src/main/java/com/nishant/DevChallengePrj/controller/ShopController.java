package com.nishant.DevChallengePrj.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nishant.DevChallengePrj.model.ShopDetails;
import com.nishant.DevChallengePrj.service.ShopService;


@RestController
public class ShopController {
	
	@Autowired
	private ShopService shopService;
	
	@PostMapping("/addNewShop")
	public ResponseEntity<Void> addNewShop(@RequestBody ShopDetails newShopDetails) {

		ShopDetails shopDetails = shopService.addShopDetails(newShopDetails);

		if (shopDetails == null) {
			return ResponseEntity.noContent().build();
		}

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
				"/{shopName}").buildAndExpand(shopDetails.getShopName()).toUri();

		return ResponseEntity.created(location).build();
	}

}
