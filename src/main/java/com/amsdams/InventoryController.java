package com.amsdams;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class InventoryController {

	private final InventoryService inventoryService;

	public InventoryController(InventoryService inventoryService) {
		this.inventoryService = inventoryService;
	}

	@PostMapping("/inventory/purchase-record")
	public ResponseEntity<?> addPurchaseRecord(@RequestBody PurchaseRecord purchaseRecord) {
		log.info("Creating new purchase record: {}", purchaseRecord);

		return inventoryService.purchaseProduct(purchaseRecord.getProductId(), purchaseRecord.getQuantityPurchased())
				.map(inventoryRecord -> {
					try {
						return ResponseEntity.ok().location(new URI("/inventory/" + inventoryRecord.getProductId()))
								.body(inventoryRecord);
					} catch (URISyntaxException e) {
						return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
					}
				}).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/inventory/{id}")
	public ResponseEntity<?> getInventoryRecord(@PathVariable Integer id) {

		return inventoryService.getInventoryRecord(id).map(inventoryRecord -> {
			try {
				return ResponseEntity.ok().location(new URI("/inventory/" + inventoryRecord.getProductId()))
						.body(inventoryRecord);
			} catch (URISyntaxException e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		}).orElse(ResponseEntity.notFound().build());
	}

}
