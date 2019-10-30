package com.amsdams;

import java.util.Optional;

public interface InventoryService {
	Optional<InventoryRecord> getInventoryRecord(Integer productId);

	Optional<InventoryRecord> purchaseProduct(Integer productId, Integer quantity);
}
