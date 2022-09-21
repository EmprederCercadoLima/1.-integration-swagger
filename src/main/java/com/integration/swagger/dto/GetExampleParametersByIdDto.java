package com.integration.swagger.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data @AllArgsConstructor @NoArgsConstructor  @Getter @Setter 
public class GetExampleParametersByIdDto {

	@ApiModelProperty(
			 name = "id",
		     value = "Identificador de usuario",
		     example = "id20",
		     dataType = "String",
		     required = false)
	private String id;

}
