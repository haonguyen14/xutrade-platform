package com.xutrade.service;

import com.xutrade.repository.entity.TradeEntity;
import com.xutrade.service.exception.BadSignatureException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.springframework.stereotype.Component;
import org.web3j.crypto.Keys;
import org.web3j.crypto.Sign;
import org.web3j.utils.Numeric;

import javax.crypto.SecretKey;
import java.security.SignatureException;

@Component
public class AuthenticationService {
    private final SecretKey privateKey;

    public AuthenticationService() {
        this.privateKey = MacProvider.generateKey();
    }

    public byte[] getPrefixedMessage(String message) {
        byte[] messageBytes = message.getBytes();
        byte[] prefixBytes = ("\u0019Ethereum Signed Message:\n" + messageBytes.length).getBytes();

        byte[] finalMessage = new byte[prefixBytes.length + messageBytes.length];
        System.arraycopy(prefixBytes, 0, finalMessage, 0, prefixBytes.length);
        System.arraycopy(messageBytes, 0, finalMessage, prefixBytes.length, messageBytes.length);
        return finalMessage;
    }

    public String getSenderAddress(byte[] message, byte[] r, byte[] s, byte v) throws SignatureException {
        Sign.SignatureData signature = new Sign.SignatureData(v, r, s);
        String publicKey = Sign.signedMessageToKey(message, signature).toString(16);
        return Keys.getAddress(publicKey);
    }

    public void verifySignature(String _senderAddr, String message, String r, String s, String v)
            throws SignatureException, BadSignatureException {
        String senderAddress = removeHexPrefix(_senderAddr);
        String address = getSenderAddress(message, r, s, v);
        if(!address.equalsIgnoreCase(senderAddress))
            throw new BadSignatureException(senderAddress, address);
    }

    public String getJwt(String address) {
        return Jwts.builder()
                .setSubject(removeHexPrefix(address))
                .signWith(SignatureAlgorithm.HS512, this.privateKey)
                .compact();
    }

    public boolean hasPermissionToTrade(String jwt, TradeEntity tradeEntity) {
        try {
            String address = Jwts.parser().setSigningKey(this.privateKey).parseClaimsJws(jwt).getBody().getSubject();
            if(address.equalsIgnoreCase(removeHexPrefix(tradeEntity.getBuyerAddr())) ||
                    address.equalsIgnoreCase(removeHexPrefix(tradeEntity.getSellerAddr()))) {
                return true;
            }
        } catch(io.jsonwebtoken.SignatureException e) {
            return false;
        }
        return false;
    }

    private String getSenderAddress(String message, String r, String s, String v) throws SignatureException {
        byte[] prefixeMessage = getPrefixedMessage(message);
        byte[] byteR = Numeric.hexStringToByteArray(r);
        byte[] byteS = Numeric.hexStringToByteArray(s);
        byte byteV = Numeric.hexStringToByteArray(v)[0];
        return getSenderAddress(prefixeMessage, byteR, byteS, byteV);
    }

    private String removeHexPrefix(String hexString) {
        return hexString.startsWith("0x") ? hexString.substring(2) : hexString;
    }
}
