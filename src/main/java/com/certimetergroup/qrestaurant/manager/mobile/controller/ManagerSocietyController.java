package com.certimetergroup.qrestaurant.manager.mobile.controller;

import com.certimetergroup.qrestaurant.dto.DTOSociety;
import com.certimetergroup.qrestaurant.enumeration.ResponseType;
import com.certimetergroup.qrestaurant.manager.response.GetSocietiesResponse;
import com.certimetergroup.qrestaurant.manager.response.PostManagerSociety;
import com.certimetergroup.qrestaurant.manager.service.Manager2SocietyService;
import com.certimetergroup.qrestaurant.manager.service.ManagerJwtService;
import com.certimetergroup.qrestaurant.manager.service.SocietyService;
import com.certimetergroup.qrestaurant.model.Manager2Society;
import com.certimetergroup.qrestaurant.model.Society;
import com.certimetergroup.qrestaurant.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Validated
@RestController
public class ManagerSocietyController {

    @Autowired
    private ManagerJwtService jwtService;
    @Autowired
    private Manager2SocietyService manager2SocietyService;
    @Autowired
    private SocietyService societyService;

    /*
     * ENDPOINT USED TO GET THE LIST OF SOCIETY OF A MANAGER
     * @param accessToken is manager's JWT access token
     * @return list of societies
     */
    @GetMapping("/society")
    public ResponseEntity<Response> getSocieties(@RequestHeader("Authorization") @NotEmpty String accessToken) {
        Integer idManager = jwtService.getFieldFromAccessToken(accessToken, "id_user", Integer.class);
        List<Society> societies = manager2SocietyService.getSocietiesOfManager(idManager);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new GetSocietiesResponse(ResponseType.SUCCESS, societies));
    }

    /*
     * ENDPOINT USED TO INSERT A NEW SOCIETY
     * @param accessToken is manager's JWT access token
     * @param dtoSociety contains society details : idSociety, street, city, country, societyNamr, vatnum
     * @return society inserted
     */
    @PostMapping("/society")
    public ResponseEntity<Response> postSociety(@RequestHeader("Authorization") @NotEmpty String accessToken,
                                                @RequestBody @Valid DTOSociety dtoSociety) {
        Society society = dtoSociety.toSociety();
        societyService.insertSociety(society);
        Integer idManager = jwtService.getFieldFromAccessToken(accessToken, "id_user", Integer.class);
        Manager2Society manager2Society = new Manager2Society(0, society.getIdSociety(), idManager);
        manager2SocietyService.insertManager2Society(manager2Society);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new PostManagerSociety(ResponseType.SUCCESS, society));
    }
}
