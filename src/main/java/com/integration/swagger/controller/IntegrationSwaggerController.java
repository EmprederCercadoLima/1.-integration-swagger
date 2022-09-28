package com.integration.swagger.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.integration.swagger.dto.GetExampleParametersByQueryDto;
import com.integration.swagger.dto.PostExampleBodyDto;
import com.integration.swagger.response.FindUserCommerceResponse;
import com.integration.swagger.response.GetExampleParameterResponse;
import com.integration.swagger.response.HelloWordResponse;
import com.integration.swagger.response.LoadHelloWordResponse;
import com.integration.swagger.response.PostExampleBodyResponse;
import com.integration.swagger.util.ListResponseBodyUtil;
import com.integration.swagger.util.LoadResponseBodyUtil;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/integration-swagger/v1")
public class IntegrationSwaggerController {
	
	@GetMapping("/hello-word")
	public ResponseEntity<LoadHelloWordResponse> getHelloWord() {
		LoadHelloWordResponse loadHelloWordResponse = new LoadHelloWordResponse();
		try {
			loadHelloWordResponse.getListHelloWordResponse().setHelloWordResponse(this.listHelloWordResponse());
			loadHelloWordResponse.setMetada("200", "OK", "Process success", "integration-swagger");
		}catch (Exception e) {
			loadHelloWordResponse.setMetada("500", "INTERNAL ERROR", "Process fail", "integration-swagger");
			return new ResponseEntity<LoadHelloWordResponse>(loadHelloWordResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<LoadHelloWordResponse>(loadHelloWordResponse, HttpStatus.OK);
	}
	
	@ApiOperation(
			value = "Get GetExampleParameterResponse with parameterId", 
			notes = "Returns a List Returns a GetExampleParameterResponse as per the id as per the id"
			)
	@ApiResponses(value = {
	  @ApiResponse(code = 200, message = "Ok"),
	  @ApiResponse(code = 204, message = "No Content"),
	  @ApiResponse(code = 400, message = "Bad request"),
	  @ApiResponse(code = 401, message = "Unauthorized"),
	  @ApiResponse(code = 403, message = "Forbidden"),
	  @ApiResponse(code = 404, message = "Not found"),
	  @ApiResponse(code = 500, message = "Internal Server Error")
	})
	@GetMapping(value = "/{parameterId}", produces = { APPLICATION_JSON_VALUE })
	ResponseEntity<List<GetExampleParameterResponse>> getExampleParameterById(
			@PathVariable (required = true, name="parameterId") String parameterId
		) {
		List<GetExampleParameterResponse> listGetExampleParameterResponse = this.loadDataForGetExampleParameterResponse();
	    List<GetExampleParameterResponse> newListGetExampleParameterResponse = listGetExampleParameterResponse.stream()
	    		.filter(employe -> employe.getId().equals(parameterId) )
	        	.collect(Collectors.toList());
	    return new ResponseEntity<List<GetExampleParameterResponse>>(newListGetExampleParameterResponse, newListGetExampleParameterResponse.size() == 0 ? HttpStatus.NO_CONTENT :  HttpStatus.OK);
	}

	@ApiOperation(
			value = "Get GetExampleParameterResponse with query email and id", 
			notes = "Returns a List Returns a GetExampleParameterResponse as per the id as per the  email and id"
			)
	@ApiResponses(value = {
	  @ApiResponse(code = 200, message = "Ok"),
	  @ApiResponse(code = 204, message = "No Content"),
	  @ApiResponse(code = 400, message = "Bad request"),
	  @ApiResponse(code = 401, message = "Unauthorized"),
	  @ApiResponse(code = 403, message = "Forbidden"),
	  @ApiResponse(code = 404, message = "Not found"),
	  @ApiResponse(code = 500, message = "Internal Server Error")
	})
	@GetMapping(value="/", produces = { APPLICATION_JSON_VALUE })
	ResponseEntity<List<GetExampleParameterResponse>> getExampleQuery(
			GetExampleParametersByQueryDto getExampleParametersByQueryDto
		) {
		List<GetExampleParameterResponse> listGetExampleParameterResponse = this.loadDataForGetExampleParameterResponse();
		List<GetExampleParameterResponse> newListGetExampleParameterResponse = listGetExampleParameterResponse.stream()
        		.filter(employe -> employe.getId().equals(getExampleParametersByQueryDto.getId()) && employe.getEmail().equals(getExampleParametersByQueryDto.getEmail()) )
        		.collect(Collectors.toList());
		return new ResponseEntity<List<GetExampleParameterResponse>>(newListGetExampleParameterResponse, newListGetExampleParameterResponse.size() == 0 ? HttpStatus.NO_CONTENT :  HttpStatus.OK);
	}

	@ApiOperation(
			value = "Get GetExampleParameterResponse with query email and id", 
			notes = "Returns a List Returns a GetExampleParameterResponse as per the id as per the  email and id"
			)
	@ApiResponses(value = {
	  @ApiResponse(code = 200, message = "Ok"),
	  @ApiResponse(code = 201, message = "Created"),
	  @ApiResponse(code = 400, message = "Bad request"),
	  @ApiResponse(code = 401, message = "Unauthorized"),
	  @ApiResponse(code = 403, message = "Forbidden"),
	  @ApiResponse(code = 404, message = "Not found"),
	  @ApiResponse(code = 500, message = "Internal Server Error")
	})
	@PostMapping(value="/", produces = { APPLICATION_JSON_VALUE })
	ResponseEntity<PostExampleBodyResponse> postExampleBody(
			@RequestBody PostExampleBodyDto postExampleBodyDto
			) {
		PostExampleBodyResponse postExampleBodyResponse = new PostExampleBodyResponse(postExampleBodyDto);
		return new ResponseEntity<PostExampleBodyResponse>(postExampleBodyResponse, HttpStatus.CREATED);
	}
	
	@ApiOperation(
			value = "Get GetExampleParameterResponse with query email and id", 
			notes = "Returns a List Returns a GetExampleParameterResponse as per the id as per the  email and id"
			)
	@ApiResponses(value = {
	  @ApiResponse(code = 200, message = "Ok"),
	  @ApiResponse(code = 400, message = "Bad request"),
	  @ApiResponse(code = 401, message = "Unauthorized"),
	  @ApiResponse(code = 403, message = "Forbidden"),
	  @ApiResponse(code = 404, message = "Not found"),
	  @ApiResponse(code = 500, message = "Internal Server Error")
	})
	@PutMapping(value="/{parameterId}", produces = { APPLICATION_JSON_VALUE })
	ResponseEntity<PostExampleBodyResponse> putExampleBodyAndPathValue(
			@PathVariable (required = true, name="parameterId") String parameterId,
			@RequestBody PostExampleBodyDto postExampleBodyDto
			) {
		PostExampleBodyResponse postExampleBodyResponse = new PostExampleBodyResponse(postExampleBodyDto);
		postExampleBodyResponse.setId(parameterId);
		return new ResponseEntity<PostExampleBodyResponse>(postExampleBodyResponse, HttpStatus.OK);
	}
	
	@ApiOperation(
			value = "Get GetExampleParameterResponse with query email and id", 
			notes = "Returns a List Returns a GetExampleParameterResponse as per the id as per the  email and id"
			)
	@ApiResponses(value = {
	  @ApiResponse(code = 200, message = "Ok"),
	  @ApiResponse(code = 400, message = "Bad request"),
	  @ApiResponse(code = 401, message = "Unauthorized"),
	  @ApiResponse(code = 403, message = "Forbidden"),
	  @ApiResponse(code = 404, message = "Not found"),
	  @ApiResponse(code = 500, message = "Internal Server Error")
	})
	@DeleteMapping(value="/{parameterId}", produces = { APPLICATION_JSON_VALUE })
	ResponseEntity<GetExampleParameterResponse> deleteExampleBodyAndPathValue(
			@PathVariable (required = true, name="parameterId") String parameterId
			) {
		List<GetExampleParameterResponse> listGetExampleParameterResponse = this.loadDataForGetExampleParameterResponse();
		GetExampleParameterResponse newListGetExampleParameterResponse = listGetExampleParameterResponse.stream()
        		.filter(employe -> employe.getId().equals(parameterId))
        		.findFirst().orElse(null);
		return new ResponseEntity<GetExampleParameterResponse>(newListGetExampleParameterResponse, (newListGetExampleParameterResponse == null) ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
	}
	
	@GetMapping(value="/find-user-commerce", produces = {  APPLICATION_JSON_VALUE })
	ResponseEntity<LoadResponseBodyUtil> findUserCommerce() {
	  List<FindUserCommerceResponse> listFindUserCommerceResponse = this.loadDataFindUserCommerceResponse();
	  LoadResponseBodyUtil<ListResponseBodyUtil<FindUserCommerceResponse>> loadResponseBodyUtil = new LoadResponseBodyUtil<ListResponseBodyUtil<FindUserCommerceResponse>>();
    try {
      loadResponseBodyUtil.getResponse().setBody(listFindUserCommerceResponse);
      loadResponseBodyUtil.setMetada("200", "OK", "Process success", "integration-swagger");
    }catch (Exception e) {
      loadResponseBodyUtil.setMetada("500", "INTERNAL ERROR", "Process fail", "integration-swagger");
      return new ResponseEntity<LoadResponseBodyUtil>(loadResponseBodyUtil, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity<LoadResponseBodyUtil>(loadResponseBodyUtil, HttpStatus.OK);
	}
	
	/*Private*/
	private List<GetExampleParameterResponse> loadDataForGetExampleParameterResponse() {
		log.info(".:. Start process [loadDataForGetExampleParameterResponse] - request: {}");
		List<GetExampleParameterResponse> listGetExampleParameterResponse = new ArrayList<>();
		
		for (int i = 0; i < 100; i++) {
			String email = "email" + i;
			String id = "id" + i;
			String password = "password" + i;
			GetExampleParameterResponse getExampleParameterResponse = new GetExampleParameterResponse(id, email, password);
			listGetExampleParameterResponse.add(getExampleParameterResponse);
		}
		log.info(".:. End process [loadDataForGetExampleParameterResponse] - response-count: {}", listGetExampleParameterResponse.size());
		return listGetExampleParameterResponse;
	}
	
	private List<FindUserCommerceResponse> loadDataFindUserCommerceResponse() {
	  List<FindUserCommerceResponse> listFindUserCommerceResponse = new ArrayList<>();
	   for (int i = 0; i < 100; i++) {
	      String email = "email" + i;
	      String id = "id" + i;
	      String password = "password" + i;
	      FindUserCommerceResponse findUserCommerceResponse = new FindUserCommerceResponse(id, email, password);
	      listFindUserCommerceResponse.add(findUserCommerceResponse);
	    }
	   return listFindUserCommerceResponse;
	}
	
	/*Private*/
	private List<HelloWordResponse> listHelloWordResponse() {
		List<HelloWordResponse> listHelloWordResponse = new ArrayList<>();
		
		for (int i = 0; i < 100; i++) { 
			HelloWordResponse helloWordResponse = new HelloWordResponse();
			helloWordResponse.setCode("I:" + i);
			helloWordResponse.setMessage("MESSAGE:" + i);
			listHelloWordResponse.add(helloWordResponse);
		}
		
		return listHelloWordResponse;
	}
	
}
