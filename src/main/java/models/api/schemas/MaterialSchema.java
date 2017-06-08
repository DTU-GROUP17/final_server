package models.api.schemas;

import lombok.Data;

@Data
public class MaterialSchema implements Schema{
	private Integer component;
	private Integer supplier;
	private Double stocked;
}
