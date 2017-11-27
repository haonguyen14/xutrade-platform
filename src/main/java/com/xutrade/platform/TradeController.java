package com.xutrade.platform;


import com.xutrade.repository.entity.TradeEntity;
import com.xutrade.service.AuthenticationService;
import com.xutrade.service.NewTradeRequest;
import com.xutrade.service.TradeService;
import com.xutrade.service.exception.ContractNotFound;
import com.xutrade.service.exception.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class TradeController {

    private final TradeService tradeService;
    private final AuthenticationService authenticationService;

    @Autowired
    public TradeController(TradeService tradeService, AuthenticationService authenticationService) {
        this.tradeService = tradeService;
        this.authenticationService = authenticationService;
    }

    @RequestMapping(value = "/trade", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public long createTrade(@RequestBody NewTradeRequest request) {
        return this.tradeService.createTrade(request);
    }

    @RequestMapping(value = "/trade/{id}", method = RequestMethod.GET)
    public TradeEntity getTrade(HttpServletRequest request, @PathVariable("id") long id)
                                throws ContractNotFound, UnauthorizedException {
        TradeEntity trade = this.tradeService.getTrade(id);
        if(!authenticationService.hasPermissionToTrade(request.getHeader(HttpHeaders.AUTHORIZATION), trade)) {
            throw new UnauthorizedException(trade.getId().toString());
        }
        return trade;
    }
}
