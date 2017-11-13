package com.nishant.DevChallengePrj.controller;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nishant.DevChallengePrj.model.ShopDetails;
import com.nishant.DevChallengePrj.service.ShopService;


@RestController
public class ShopController {
	
	public static Logger LOG = LoggerFactory.getLogger(ShopController.class);
	
	@Autowired
	private ShopService shopService;
	
	@PostMapping("/addNewShop")
	public ResponseEntity<Void> addNewShop(@RequestBody ShopDetails newShopDetails) throws Exception {
		LOG.info("Adding new shop");
		ShopDetails shopDetails = shopService.addShopDetails(newShopDetails);

		if (shopDetails == null) {
			return ResponseEntity.noContent().build();
		}

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
				"/{shopName}").buildAndExpand(shopDetails.getShopName()).toUri();

		return ResponseEntity.created(location).build();
	}
	
	@GetMapping("/latitude/{latitude}/longitude/{longitude}")
	public ShopDetails retrieveNearestShop(@PathVariable String latitude,
			@PathVariable String longitude) throws Exception {
		return shopService.retrieveNearestShop(latitude, longitude);
	}

}
