package com.xutrade.platform;

import com.xutrade.platform.model.AuthenticationRequest;
import com.xutrade.service.AuthenticationService;
import com.xutrade.service.exception.BadSignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.crypto.Hash;
import org.web3j.crypto.Keys;
import org.web3j.crypto.Sign;
import org.web3j.utils.Numeric;

import java.math.BigInteger;
import java.security.SignatureException;

@RestController
public class AuthenticationController {

    @Autowired
    AuthenticationService service;

    @RequestMapping(value = "/auth", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String authenticate(@RequestBody AuthenticationRequest request)
            throws SignatureException, BadSignatureException {

        service.verifySignature(request.getAddress(),
                request.getMessage(),
                request.getHexR(),
                request.getHexS(),
                request.getHexV());
        return service.getJwt(request.getAddress());
    }
}