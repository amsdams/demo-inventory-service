package com.amsdams;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryRecord {
	private Integer productId;
	private Integer quantity;
	private String productName;
	private String productCategory;
}
