package com.amsdams;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseRecord {
	private Integer productId;
	private Integer quantityPurchased;
}
