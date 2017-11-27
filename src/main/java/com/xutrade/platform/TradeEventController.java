package com.xutrade.platform;

import com.xutrade.repository.entity.TradeEntity;
import com.xutrade.repository.entity.TradeEventEntity;
import com.xutrade.service.AuthenticationService;
import com.xutrade.service.EventLogRequest;
import com.xutrade.service.TradeEventService;
import com.xutrade.service.TradeService;
import com.xutrade.service.exception.ContractNotFound;
import com.xutrade.service.exception.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class TradeEventController {

    private final AuthenticationService authenticationService;
    private final TradeService tradeService;
    private TradeEventService service;

    @Autowired
    public TradeEventController(TradeEventService service,
                                TradeService tradeService,
                                AuthenticationService authenticationService) {
        this.service = service;
        this.tradeService = tradeService;
        this.authenticationService = authenticationService;
    }

    @RequestMapping(value = "/log", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void logEvent(@RequestBody EventLogRequest request) {
        service.logEvent(request);
    }

    @RequestMapping(value = "/log/{id}", method = RequestMethod.GET)
    public List<TradeEventEntity> getLogs(HttpServletRequest request, @PathVariable("id") long id)
            throws ContractNotFound, UnauthorizedException {
        TradeEntity trade = this.tradeService.getTrade(id);
        if(!authenticationService.hasPermissionToTrade(request.getHeader(HttpHeaders.AUTHORIZATION), trade)) {
            throw new UnauthorizedException(trade.getId().toString());
        }
        return this.service.getLogs(id);
    }
}
